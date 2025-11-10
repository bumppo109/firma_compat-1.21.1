package com.bumppo109.firma_compat.items;

import com.bumppo109.firma_compat.FirmaCompatibility;
import com.bumppo109.firma_compat.blocks.ModCompatBlocks;
import com.bumppo109.firma_compat.blocks.wood.VanillaWood;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.entities.TFCEntities;
import net.dries007.tfc.common.items.TFCMinecartItem;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.registry.RegistryHolder;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class ModCompatItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, FirmaCompatibility.MODID);

// Wood
    public static final Map<VanillaWood, ItemId> LUMBER = Helpers.mapOf(VanillaWood.class, wood -> register("wood/lumber/" + wood.name()));

    public static final Map<VanillaWood, ItemId> SUPPORTS = Helpers.mapOf(VanillaWood.class, wood ->
            register("wood/support/" + wood.name(), () -> new StandingAndWallBlockItem(ModCompatBlocks.WOODS.get(wood).get(Wood.BlockType.VERTICAL_SUPPORT).get(), ModCompatBlocks.WOODS.get(wood).get(Wood.BlockType.HORIZONTAL_SUPPORT).get(), new Item.Properties(), Direction.DOWN))
    );

    //public static final Map<VanillaWood, ItemId> BOATS = Helpers.mapOf(VanillaWood.class, wood -> register("wood/boat/" + wood.name(), () -> new TFCBoatItem(AFCEntities.BOATS.get(wood), new Item.Properties())));

    public static final Map<VanillaWood, ItemId> CHEST_MINECARTS = Helpers.mapOf(VanillaWood.class, wood -> register("wood/chest_minecart/" + wood.name(), () -> new TFCMinecartItem(new Item.Properties(), TFCEntities.CHEST_MINECART, () -> ModCompatBlocks.WOODS.get(wood).get(Wood.BlockType.CHEST).get().asItem())));

    //public static final Map<VanillaWood, ItemId> SIGNS = Helpers.mapOf(VanillaWood.class, wood -> register("wood/sign/" + wood.name(), () -> new SignItem(new Item.Properties(), ModCompatBlocks.WOODS.get(wood).get(Wood.BlockType.SIGN).get(), ModCompatBlocks.WOODS.get(wood).get(Wood.BlockType.WALL_SIGN).get())));

    public static final Map<VanillaWood, Map<Metal, ItemId>> HANGING_SIGNS = Helpers.mapOf(VanillaWood.class, wood ->
            Helpers.mapOf(Metal.class, Metal::allParts, metal ->
                    register("wood/hanging_sign/" + metal.name() + "/" + wood.name(), () -> new HangingSignItem(ModCompatBlocks.CEILING_HANGING_SIGNS.get(wood).get(metal).get(), ModCompatBlocks.WALL_HANGING_SIGNS.get(wood).get(metal).get(), new Item.Properties()))
            )
    );

    private static ItemId register(String name)
    {
        return register(name, () -> new Item(new Item.Properties()));
    }

    private static <T extends Item> ItemId register(String name, Supplier<T> item)
    {
        return new ItemId(ITEMS.register(name.toLowerCase(Locale.ROOT), item));
    }

    public record ItemId(DeferredHolder<Item, Item> holder) implements RegistryHolder<Item, Item>, ItemLike
    {
        @Override
        public Item asItem()
        {
            return get();
        }
    }
}
