package com.bumppo109.firma_compat.worldgen.processor.rock;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.Block;


public enum RockDecoration
{
    BLOCK,
    SLAB,
    STAIRS,
    WALL;


    public static RockDecoration of(Block block)
    {
        if (block instanceof SlabBlock)
        {
            return SLAB;
        }

        if (block instanceof StairBlock)
        {
            return STAIRS;
        }

        if (block instanceof WallBlock)
        {
            return WALL;
        }

        return BLOCK;
    }
}