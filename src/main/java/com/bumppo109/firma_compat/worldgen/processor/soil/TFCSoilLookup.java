package com.bumppo109.firma_compat.worldgen.processor.soil;


import com.bumppo109.firma_compat.worldgen.processor.Decoration;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;

import net.minecraft.world.level.block.state.BlockState;



public final class TFCSoilLookup
{


    public static BlockState get(
            SoilBlockType type,
            SoilBlockType.Variant variant,
            Decoration decoration)
    {

        if(type == SoilBlockType.MUD_BRICKS)
        {

            var holder =
                    TFCBlocks.MUD_BRICK_DECORATIONS
                            .get(variant);


            return switch(decoration)
            {
                case STAIRS ->
                        holder.stair()
                                .get()
                                .defaultBlockState();


                case SLAB ->
                        holder.slab()
                                .get()
                                .defaultBlockState();


                default ->
                        variant.getBlock(type)
                                .get()
                                .defaultBlockState();
            };

        }


        return variant
                .getBlock(type)
                .get()
                .defaultBlockState();

    }

}