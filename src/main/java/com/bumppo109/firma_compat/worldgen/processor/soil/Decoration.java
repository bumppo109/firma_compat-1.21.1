package com.bumppo109.firma_compat.worldgen.processor.soil;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;

public enum Decoration
{
    BLOCK,
    SLAB,
    STAIRS;

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

        return BLOCK;
    }
}