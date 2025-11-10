package com.bumppo109.firma_compat.blocks;

import com.bumppo109.firma_compat.blocks.wood.VanillaWood;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public interface CompatRegistryWood extends StringRepresentable {
    MapColor woodColor();

    MapColor barkColor();

    TreeGrower tree();

    Supplier<Integer> ticksToGrow();

    int autumnIndex();

    Supplier<Block> getBlock(Wood.BlockType var1);

    BlockSetType getBlockSet();

    WoodType getVanillaWoodType();
}
