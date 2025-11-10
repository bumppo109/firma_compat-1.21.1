package com.bumppo109.firma_compat.blocks.wood;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

import com.bumppo109.firma_compat.FirmaCompatibilityHelpers;
import com.bumppo109.firma_compat.blocks.ModCompatBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.calendar.Calendar;
import net.dries007.tfc.util.registry.RegistryWood;

public enum VanillaWood implements RegistryWood
{
    //Wood color, then bark color
    ACACIA(false, MapColor.STONE, MapColor.COLOR_ORANGE,10, 212, 0.0506f),
    BIRCH(false, MapColor.QUARTZ, MapColor.SAND,10, 212, 0.0506f),
    CHERRY(false, MapColor.TERRACOTTA_GRAY, MapColor.TERRACOTTA_WHITE,10, 212, 0.0506f),
    DARK_OAK(false, MapColor.COLOR_BROWN, MapColor.COLOR_BROWN,10, 212, 0.0506f),
    JUNGLE(false, MapColor.PODZOL, MapColor.DIRT,10, 212, 0.0506f),
    MANGROVE(false, MapColor.PODZOL, MapColor.COLOR_RED,10, 212, 0.0506f),
    OAK(false, MapColor.PODZOL, MapColor.WOOD,10, 212, 0.0506f),
    SPRUCE(false, MapColor.COLOR_BROWN, MapColor.PODZOL,10, 212, 0.0506f),
    CRIMSON(false, MapColor.CRIMSON_HYPHAE, MapColor.CRIMSON_STEM,10, 212, 0.0506f),
    WARPED(false, MapColor.WARPED_HYPHAE, MapColor.WARPED_STEM,10, 212, 0.0506f)
    ;

    public static final VanillaWood[] VALUES = values();

    private final String serializedName;
    private final boolean conifer;
    private final MapColor woodColor;
    private final MapColor barkColor;
    private final TreeGrower tree;
    private final int daysToGrow;
    private final BlockSetType blockSet;
    private final WoodType woodType;
    private final int autumnIndex;
    private final float saplingDropRate;

    VanillaWood(boolean evergreen, MapColor woodColor, MapColor barkColor, int daysToGrow, int autumnIndex, float saplingDropRate) {
        this.serializedName = this.name().toLowerCase(Locale.ROOT);
        this.conifer = evergreen;
        this.woodColor = woodColor;
        this.barkColor = barkColor;
        this.autumnIndex = autumnIndex;
        this.tree = new TreeGrower(
                FirmaCompatibilityHelpers.modIdentifier(this.serializedName).toString(),
                Optional.empty(),
                Optional.of(ResourceKey.create(Registries.CONFIGURED_FEATURE, FirmaCompatibilityHelpers.modIdentifier("tree/" + this.serializedName))),
                Optional.empty()
        );
        this.daysToGrow = daysToGrow;
        this.blockSet = new BlockSetType(serializedName);
        this.woodType = new WoodType(FirmaCompatibilityHelpers.modIdentifier(this.serializedName).toString(), this.blockSet);
        this.saplingDropRate = saplingDropRate;
    }

    @Override
    public String getSerializedName()
    {
        return serializedName;
    }

    public boolean isConifer()
    {
        return conifer;
    }

    @Override
    public MapColor woodColor()
    {
        return woodColor;
    }

    @Override
    public MapColor barkColor()
    {
        return barkColor;
    }
    public Supplier<Block> getBlock(Wood.BlockType type) {
        return ModCompatBlocks.WOODS.get(this).get(type);
    }

    @Override
    public BlockSetType getBlockSet()
    {
        return blockSet;
    }

    @Override
    public WoodType getVanillaWoodType()
    {
        return woodType;
    }

    public TreeGrower tree() {
        return tree;
    }

    public Supplier<Integer> ticksToGrow() {
        return () -> daysToGrow() * Calendar.CALENDAR_TICKS_IN_DAY;
    }

    public int daysToGrow() {
        //return (Integer)((ForgeConfigSpec.IntValue) AFCConfig.SERVER.saplingGrowthDays.get(this)).get();
        return defaultDaysToGrow();
    }

    @Override
    public int autumnIndex()
    {
        return autumnIndex;
    }

    public float getSaplingDropRate()
    {
        return saplingDropRate;
    }

    public int defaultDaysToGrow() {
        return daysToGrow;
    }

    public static void registerBlockSetTypes()
    {
        for (VanillaWood wood : VALUES)
        {
            BlockSetType.register(wood.blockSet);
            WoodType.register(wood.woodType);
        }
    }
}