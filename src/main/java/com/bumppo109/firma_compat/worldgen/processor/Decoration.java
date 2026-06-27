package com.bumppo109.firma_compat.worldgen.processor;


import net.minecraft.world.level.block.*;


public enum Decoration
{
    BLOCK,
    SLAB,
    STAIRS,
    WALL;


    public static Decoration of(Block block)
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