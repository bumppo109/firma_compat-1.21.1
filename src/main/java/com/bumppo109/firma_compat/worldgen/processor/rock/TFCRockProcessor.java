package com.bumppo109.firma_compat.worldgen.processor.rock;


import com.bumppo109.firma_compat.FirmaCompat;
import com.bumppo109.firma_compat.util.TFCCodecs;
import com.bumppo109.firma_compat.worldgen.processor.ModStructureProcessors;

import com.bumppo109.firma_compat.worldgen.processor.RockReplacement;
import com.mojang.serialization.MapCodec;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.world.chunkdata.ChunkData;
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


    private final Map<Block, RockReplacement> replacements;


    public TFCRockProcessor(
            Map<Block, RockReplacement> replacements)
    {
        this.replacements =
                Map.copyOf(replacements);
    }


    public Map<Block, RockReplacement> getReplacements()
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


        RockReplacement replacement =
                replacements.get(
                        state.getBlock()
                );


        if (replacement == null)
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


        BlockState result =
                TFCRockLookup.get(
                        rock,
                        replacement,
                        decoration
                );


        FirmaCompat.LOGGER.debug(
                "Rock swap {} -> {}",
                BuiltInRegistries.BLOCK.getKey(
                        state.getBlock()
                ),
                BuiltInRegistries.BLOCK.getKey(
                        result.getBlock()
                )
        );


        return new StructureTemplate.StructureBlockInfo(
                transformed.pos(),
                result,
                transformed.nbt()
        );
    }


    @Override
    protected StructureProcessorType<?> getType()
    {
        return ModStructureProcessors.TFC_ROCK.get();
    }
}