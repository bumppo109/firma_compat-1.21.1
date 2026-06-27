package com.bumppo109.firma_compat.worldgen.processor.rock;

import com.bumppo109.firma_compat.block.ModBlocks;
import com.bumppo109.firma_compat.worldgen.processor.Decoration;

import net.dries007.tfc.common.blocks.DecorationBlockHolder;
import net.dries007.tfc.common.blocks.TFCBlocks;
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

            for (BlockType type : BlockType.values())
            {
                var holder =
                        TFCBlocks.ROCK_BLOCKS
                                .get(rock)
                                .get(type);


                if (holder != null)
                {
                    ROCK_LOOKUP.put(
                            holder.get(),
                            rock
                    );
                }
            }


            var decorations =
                    TFCBlocks.ROCK_DECORATIONS
                            .get(rock);


            for (BlockType type : BlockType.values())
            {
                DecorationBlockHolder holder =
                        decorations.get(type);


                if (holder != null)
                {
                    ROCK_LOOKUP.put(
                            holder.stair().get(),
                            rock
                    );

                    ROCK_LOOKUP.put(
                            holder.slab().get(),
                            rock
                    );

                    ROCK_LOOKUP.put(
                            holder.wall().get(),
                            rock
                    );
                }
            }
        }
    }



    public static BlockState get(
            RockSettings settings,
            RockReplacement replacement,
            Decoration decoration)
    {

        return getBlock(
                settings,
                replacement,
                decoration
        )
                .defaultBlockState();
    }



    private static Block getBlock(
            RockSettings settings,
            RockReplacement replacement,
            Decoration decoration)
    {

        Rock rock =
                ROCK_LOOKUP.get(
                        settings.raw()
                );


        return switch (replacement)
        {

            case RAW ->
                    settings.raw();


            case HARDENED ->
                    settings.hardened();


            case GRAVEL ->
                    settings.gravel();


            case SAND ->
                    settings.sand();


            case SANDSTONE ->
                    settings.sandstone();


            case LOOSE_COBBLE -> TFCBlocks.ROCK_BLOCKS.get(rock).get(BlockType.COBBLE).get();


            case HARDENED_COBBLE ->
                    ModBlocks.COMPAT_HARDENED_COBBLE
                            .get(rock)
                            .get();


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


            default ->
                    settings.raw();
        };
    }



    private static Block decorated(
            Rock rock,
            BlockType type,
            Decoration decoration)
    {

        if (rock == null)
        {
            throw new IllegalStateException(
                    "No TFC rock for replacement"
            );
        }


        if (decoration == Decoration.BLOCK)
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
                    holder.stair().get();

            case SLAB ->
                    holder.slab().get();

            case WALL ->
                    holder.wall().get();

            default ->
                    throw new IllegalStateException();
        };
    }
}