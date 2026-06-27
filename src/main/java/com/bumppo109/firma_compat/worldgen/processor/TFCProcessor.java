package com.bumppo109.firma_compat.worldgen.processor;

import com.bumppo109.firma_compat.FirmaCompat;
import com.bumppo109.firma_compat.worldgen.processor.rock.RockReplacement;
import com.bumppo109.firma_compat.worldgen.processor.rock.TFCRockLookup;
import com.bumppo109.firma_compat.worldgen.processor.soil.SoilReplacement;
import com.bumppo109.firma_compat.worldgen.processor.soil.TFCSoilLookup;
import com.bumppo109.firma_compat.worldgen.processor.soil.TFCSoilResolver;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.dries007.tfc.world.chunkdata.ChunkData;
import net.dries007.tfc.world.settings.RockSettings;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import javax.annotation.Nullable;
import java.util.Map;


public class TFCProcessor extends StructureProcessor
{

    public static final MapCodec<TFCProcessor> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                                    Codec.unboundedMap(
                                                    BuiltInRegistries.BLOCK.byNameCodec(),
                                                    ProcessorReplacementCodec.CODEC
                                            )
                                            .fieldOf("replacements")
                                            .forGetter(TFCProcessor::getReplacements)
                            )
                            .apply(
                                    instance,
                                    TFCProcessor::new
                            )
            );


    private final Map<Block, ProcessorReplacement> replacements;


    public TFCProcessor(
            Map<Block, ProcessorReplacement> replacements)
    {
        this.replacements =
                Map.copyOf(replacements);
    }


    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(
            LevelReader level,
            BlockPos structureOffset,
            BlockPos piecePos,
            StructureTemplate.StructureBlockInfo original,
            StructureTemplate.StructureBlockInfo transformed,
            StructurePlaceSettings settings)
    {

        Block source =
                transformed.state()
                        .getBlock();

        var id =
                BuiltInRegistries.BLOCK.getKey(source);

        /*
        FirmaCompat.LOGGER.debug(
                "seen {}",
                id
        );

         */

        ProcessorReplacement replacement =
                replacements.get(source);

        if (replacement == null)
        {
            replacement =
                    replacements.entrySet()
                            .stream()
                            .filter(e ->
                                    BuiltInRegistries.BLOCK.getKey(e.getKey())
                                            .equals(id))
                            .map(Map.Entry::getValue)
                            .findFirst()
                            .orElse(null);
        }

        /*
        FirmaCompat.LOGGER.debug(
                "lookup {} -> {}",
                id,
                replacement
        );

         */


        Decoration decoration =
                Decoration.of(source);


        BlockState result;


        if (replacement instanceof RockReplacement rockReplacement)
        {
            RockSettings rock =
                    getStructureRock(
                            level,
                            transformed.pos()
                    );


            if (rock == null)
            {
                return transformed;
            }


            result =
                    TFCRockLookup.get(
                            rock,
                            rockReplacement,
                            decoration
                    );
        }

        else if (replacement instanceof SoilReplacement soil)
        {

            SoilBlockType.Variant variant =
                    TFCSoilResolver.determineVariant(
                            level,
                            transformed.pos()
                    );


            result =
                    TFCSoilLookup.get(
                            soil.type(),
                            variant,
                            decoration
                    );

        }

        else
        {
            return transformed;
        }


        result =
                BlockStateUtil.copyCommonProperties(
                        transformed.state(),
                        result
                );


        return new StructureTemplate.StructureBlockInfo(
                transformed.pos(),
                result,
                transformed.nbt()
        );
    }


    private static RockSettings getStructureRock(
            LevelReader level,
            BlockPos pos)
    {
        ChunkAccess chunk =
                level.getChunk(
                        pos.getX() >> 4,
                        pos.getZ() >> 4
                );


        int surfaceY =
                chunk.getHeight(
                        Heightmap.Types.WORLD_SURFACE_WG,
                        pos.getX() & 15,
                        pos.getZ() & 15
                );


        /*
         * Structures can be above terrain.
         *
         * Always sample the terrain column,
         * not the structure block.
         */
        BlockPos terrainPos =
                new BlockPos(
                        pos.getX(),
                        surfaceY,
                        pos.getZ()
                );


        var rockData =
                ChunkData.get(level, terrainPos)
                        .getRockData();


        /*
         * Prefer the exposed surface rock.
         * This is what TFC uses for cliffs/outcrops.
         */
        RockSettings surface =
                rockData.getSurfaceRock(
                        pos.getX(),
                        pos.getZ()
                );


        if (surface != null)
        {
            //FirmaCompat.LOGGER.debug("getStructureRock found surface - {}", surface);
            return surface;
        }


        /*
         * Fall back into the column.
         */
        for (int y = surfaceY; y > surfaceY - 64; y--)
        {
            RockSettings rock =
                    rockData.getRock(
                            new BlockPos(
                                    pos.getX(),
                                    y,
                                    pos.getZ()
                            )
                    );

            if (rock != null)
            {
                //FirmaCompat.LOGGER.debug("getStructureRock no surface, fallback - {}", rock);
                return rock;
            }
        }


        return null;
    }


    public Map<Block, ProcessorReplacement> getReplacements()
    {
        return replacements;
    }


    @Override
    protected StructureProcessorType<?> getType()
    {
        return ModStructureProcessors.TFC_REPLACER.get();
    }
}