package com.bumppo109.firma_compat.worldgen.processor;


import com.bumppo109.firma_compat.worldgen.processor.rock.*;
import com.bumppo109.firma_compat.worldgen.processor.soil.*;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.dries007.tfc.world.chunkdata.ChunkData;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import javax.annotation.Nullable;
import java.util.Map;


public class TFCProcessor
        extends StructureProcessor
{

    public static final MapCodec<TFCProcessor> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(

                                    Codec.unboundedMap(
                                                    BuiltInRegistries.BLOCK.byNameCodec(),
                                                    ProcessorReplacementCodec.CODEC
                                            )
                                            .fieldOf("replacements")
                                            .forGetter(
                                                    TFCProcessor::getReplacements
                                            )

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


        ProcessorReplacement replacement =
                replacements.get(source);


        if (replacement == null)
        {
            return transformed;
        }



        Decoration decoration =
                Decoration.of(source);



        BlockState result;



        if (replacement instanceof RockReplacement rock)
        {
            var data =
                    ChunkData.get(
                            level,
                            transformed.pos()
                    );


            result =
                    TFCRockLookup.get(
                            data.getRockData()
                                    .getRock(
                                            transformed.pos()
                                    ),
                            rock,
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