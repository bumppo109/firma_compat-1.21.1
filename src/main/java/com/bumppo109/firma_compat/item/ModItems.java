package com.bumppo109.firma_compat.item;

import com.bumppo109.firma_compat.FirmaCompatibility;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, FirmaCompatibility.MODID);

//Lumber
    public static final Supplier<Item> ACACIA_LUMBER = ITEMS.register("acacia_lumber",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> BIRCH_LUMBER = ITEMS.register("birch_lumber",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> CHERRY_LUMBER = ITEMS.register("cherry_lumber",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> DARK_OAK_LUMBER = ITEMS.register("dark_oak_lumber",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> JUNGLE_LUMBER = ITEMS.register("jungle_lumber",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> MANGROVE_LUMBER = ITEMS.register("mangrove_lumber",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> OAK_LUMBER = ITEMS.register("oak_lumber",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> PALE_OAK_LUMBER = ITEMS.register("pale_oak_lumber",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> SPRUCE_LUMBER = ITEMS.register("spruce_lumber",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> CRIMSON_LUMBER = ITEMS.register("crimson_lumber",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> WARPED_LUMBER = ITEMS.register("warped_lumber",
            () -> new Item(new Item.Properties()));

//Rock Bricks
    public static final Supplier<Item> STONE_BRICK = ITEMS.register("stone_brick",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> DEEPSLATE_BRICK = ITEMS.register("deepslate_brick",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> ANDESITE_BRICK = ITEMS.register("andesite_brick",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> DIORITE_BRICK = ITEMS.register("diorite_brick",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> GRANITE_BRICK = ITEMS.register("granite_brick",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> TUFF_BRICK = ITEMS.register("tuff_brick",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> CALCITE_BRICK = ITEMS.register("calcite_brick",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> DRIPSTONE_BRICK = ITEMS.register("dripstone_brick",
            () -> new Item(new Item.Properties()));
//Nether Bricks
    public static final Supplier<Item> POLISHED_BLACKSTONE_BRICK = ITEMS.register("polished_blackstone_brick",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> BASALT_BRICK = ITEMS.register("basalt_brick",
            () -> new Item(new Item.Properties()));
//End Bricks
    public static final Supplier<Item> END_STONE_BRICK = ITEMS.register("end_stone_brick",
            () -> new Item(new Item.Properties()));
//Misc Bricks
    public static final Supplier<Item> PRISMARINE_BRICK = ITEMS.register("prismarine_brick",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> RED_NETHER_BRICK = ITEMS.register("red_nether_brick",
            () -> new Item(new Item.Properties()));
    public static final Supplier<Item> QUARTZ_BRICK = ITEMS.register("quartz_brick",
            () -> new Item(new Item.Properties()));

}
