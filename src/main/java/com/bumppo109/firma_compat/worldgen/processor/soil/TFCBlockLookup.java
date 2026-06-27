package com.bumppo109.firma_compat.worldgen.processor.soil;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.minecraft.world.level.block.state.BlockState;


public final class TFCBlockLookup
{
    private TFCBlockLookup()
    {
    }


    public static BlockState get(
            SoilBlockType type,
            SoilBlockType.Variant variant,
            Decoration decoration)
    {

        if (type == SoilBlockType.MUD_BRICKS)
        {
            var decorations =
                    TFCBlocks.MUD_BRICK_DECORATIONS.get(variant);


            return switch (decoration)
            {
                case STAIRS ->
                        decorations.stair()
                                .get()
                                .defaultBlockState();


                case SLAB ->
                        decorations.slab()
                                .get()
                                .defaultBlockState();


                case BLOCK ->
                        variant.getBlock(type)
                                .get()
                                .defaultBlockState();
            };
        }


        return variant.getBlock(type)
                .get()
                .defaultBlockState();
    }
}