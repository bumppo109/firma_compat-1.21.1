package com.bumppo109.firma_compat.items;

import com.bumppo109.firma_compat.FirmaCompatibility;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FirmaCompatibility.MODID);

    public static final DeferredItem<Item> UNFIRED_DECORATED_POT = ITEMS.register("unfired_decorated_pot", () -> new Item(new Item.Properties()));

//brick
    public static final DeferredItem<Item> STONE_BRICK = ITEMS.register("brick/stone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> POLISHED_BLACKSTONE_BRICK = ITEMS.register("brick/polished_blackstone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TUFF_BRICK = ITEMS.register("brick/tuff", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DEEPSLATE_BRICK = ITEMS.register("brick/deepslate", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DEEPSLATE_TILE = ITEMS.register("deepslate_tile", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> QUARTZ_BRICK = ITEMS.register("brick/quartz", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> END_STONE_BRICK = ITEMS.register("brick/end_stone", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ANDESITE_BRICK = ITEMS.register("brick/andesite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DIORITE_BRICK = ITEMS.register("brick/diorite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GRANITE_BRICK = ITEMS.register("brick/granite", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PRISMARINE_BRICK = ITEMS.register("brick/prismarine", () -> new Item(new Item.Properties()));

//dry mud brick
    public static final DeferredItem<Item> MUD_BRICK = ITEMS.register("mud_brick", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // Fixed: Supplier<Item> (not <? extends Item>) to match DeferredItem<Item> return type precisely
    private static DeferredItem<Item> registerItem(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }
}