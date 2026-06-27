package com.bumppo109.firma_compat.worldgen.processor.rock;

import com.bumppo109.firma_compat.FirmaCompat;
import com.bumppo109.firma_compat.util.TFCCodecs;
import com.bumppo109.firma_compat.worldgen.processor.ModStructureProcessors;
import com.mojang.serialization.MapCodec;

import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.rock.Rock.BlockType;
import net.dries007.tfc.world.chunkdata.ChunkData;
import net.dries007.tfc.world.chunkdata.RockData;
import net.dries007.tfc.world.settings.RockSettings;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import javax.annotation.Nullable;
import java.util.Map;


public class TFCRockProcessor extends StructureProcessor
{
    public static final MapCodec<TFCRockProcessor> CODEC =
            TFCCodecs.TFC_ROCK;


    private final Map<Block, BlockType> replacements;


    public TFCRockProcessor(
            Map<Block, BlockType> replacements)
    {
        this.replacements = Map.copyOf(replacements);
    }


    public Map<Block, BlockType> getReplacements()
    {
        return replacements;
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
        BlockState state =
                transformed.state();


        BlockType type =
                replacements.get(
                        state.getBlock()
                );


        if (type == null)
        {
            return transformed;
        }


        ChunkData data =
                ChunkData.get(
                        level,
                        transformed.pos()
                );


        RockSettings rock =
                data.getRockData()
                        .getRock(
                                transformed.pos()
                        );


        RockDecoration decoration =
                RockDecoration.of(
                        state.getBlock()
                );


        BlockState replacement =
                TFCRockLookup.get(
                        rock,
                        type
                );


        FirmaCompat.LOGGER.debug(
                "Rock swap {} -> {}",
                BuiltInRegistries.BLOCK.getKey(
                        state.getBlock()
                ),
                BuiltInRegistries.BLOCK.getKey(
                        replacement.getBlock()
                )
        );


        return new StructureTemplate.StructureBlockInfo(
                transformed.pos(),
                replacement,
                transformed.nbt()
        );
    }


    @Override
    protected StructureProcessorType<?> getType()
    {
        return ModStructureProcessors.TFC_ROCK.get();
    }
}