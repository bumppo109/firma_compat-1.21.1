package com.bumppo109.firma_compat.worldgen.processor.rock;


import com.bumppo109.firma_compat.block.ModBlocks;
import com.bumppo109.firma_compat.worldgen.processor.RockReplacement;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.DecorationBlockHolder;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.rock.Rock.BlockType;
import net.dries007.tfc.world.settings.RockSettings;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.IdentityHashMap;
import java.util.Map;


public final class TFCRockLookup
{
    private TFCRockLookup()
    {
    }


    private static final Map<Block, Rock> ROCK_LOOKUP =
            new IdentityHashMap<>();


    static
    {
        for (Rock rock : Rock.values())
        {
            ROCK_LOOKUP.put(
                    TFCBlocks.ROCK_BLOCKS
                            .get(rock)
                            .get(BlockType.RAW)
                            .get(),

                    rock
            );
        }
    }



    public static BlockState get(
            RockSettings rock,
            RockReplacement replacement,
            RockDecoration decoration)
    {
        return getBlock(
                rock,
                replacement,
                decoration
        ).defaultBlockState();
    }



    private static Block getBlock(
            RockSettings rock,
            RockReplacement replacement,
            RockDecoration decoration)
    {
        return switch (replacement)
        {
            case RAW ->
                    rock.raw();


            case HARDENED ->
                    rock.hardened();


            case COBBLE ->
                    rock.cobble();


            case GRAVEL ->
                    rock.gravel();


            case SUSPICIOUS_GRAVEL ->
                    suspiciousGravel(
                            rock
                    );


            case BRICKS ->
                    decorated(
                            rock,
                            BlockType.BRICKS,
                            decoration
                    );


            case CRACKED_BRICKS ->
                    decorated(
                            rock,
                            BlockType.CRACKED_BRICKS,
                            decoration
                    );


            case MOSSY_BRICKS ->
                    decorated(
                            rock,
                            BlockType.MOSSY_BRICKS,
                            decoration
                    );
        };
    }



    private static Block decorated(
            RockSettings settings,
            BlockType type,
            RockDecoration decoration)
    {
        Rock rock =
                ROCK_LOOKUP.get(settings.raw());


        if (rock == null)
        {
            return settings.raw();
        }


        if (decoration == RockDecoration.BLOCK)
        {
            return TFCBlocks.ROCK_BLOCKS
                    .get(rock)
                    .get(type)
                    .get();
        }


        DecorationBlockHolder holder =
                TFCBlocks.ROCK_DECORATIONS
                        .get(rock)
                        .get(type);



        return switch (decoration)
        {
            case STAIRS ->
                    holder.stair()
                            .get();

            case SLAB ->
                    holder.slab()
                            .get();

            case WALL ->
                    holder.wall()
                            .get();

            case BLOCK ->
                    throw new IllegalStateException();
        };
    }



    private static Block suspiciousGravel(
            RockSettings settings)
    {
        Rock rock =
                ROCK_LOOKUP.get(
                        settings.raw()
                );


        if (rock == null)
        {
            return settings.gravel();
        }


        return ModBlocks.SUSPICIOUS_GRAVEL
                .get(rock)
                .get();
    }
}