package com.bumppo109.firma_compat.item;

import com.bumppo109.firma_compat.FirmaCompatibility;
import com.bumppo109.firma_compat.block.ModBlocks;
import com.bumppo109.firma_compat.block.VanillaWood;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.registry.RegistryHolder;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, FirmaCompatibility.MODID);

    public static final Map<VanillaWood, ItemId> LUMBER = Helpers.mapOf(VanillaWood.class, wood -> register(wood.name() + "_lumber"));

    public static final Map<VanillaWood, ItemId> SUPPORTS = Helpers.mapOf(VanillaWood.class, wood ->
            register(wood.name() + "_support", () -> new StandingAndWallBlockItem(ModBlocks.WOODS.get(wood).get(VanillaWood.BlockType.VERTICAL_SUPPORT).get(), ModBlocks.WOODS.get(wood).get(VanillaWood.BlockType.HORIZONTAL_SUPPORT).get(), new Properties(), Direction.DOWN))
    );

    /*
    public static final Map<VanillaWood, Map<Metal, ItemId>> HANGING_SIGNS = Helpers.mapOf(VanillaWood.class, wood ->
            Helpers.mapOf(Metal.class, Metal::allParts, metal ->
                    register("wood/hanging_sign/" + metal.name() + "/" + wood.name(), () -> new HangingSignItem(ModBlocks.CEILING_HANGING_SIGNS.get(wood).get(metal).get(), ModBlocks.WALL_HANGING_SIGNS.get(wood).get(metal).get(), new Properties()))
            )
    );

     */

    private static ItemId register(String name)
    {
        return register(name, () -> new Item(new Properties()));
    }

    private static ItemId register(String name, Properties properties)
    {
        return new ItemId(ITEMS.register(name.toLowerCase(Locale.ROOT), () -> new Item(properties)));
    }

    private static ItemId register(String name, Supplier<Item> item)
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
