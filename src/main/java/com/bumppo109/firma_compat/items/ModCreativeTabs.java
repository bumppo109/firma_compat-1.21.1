package com.bumppo109.firma_compat.items;

import com.bumppo109.firma_compat.FirmaCompatibility;
import com.bumppo109.firma_compat.blocks.ModBlocks;
import com.bumppo109.firma_compat.blocks.ModCompatBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirmaCompatibility.MODID);

    public static final Supplier<CreativeModeTab> FIRMA_COMPAT_TAB =
            CREATIVE_MODE_TABS.register("firm_compat_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.firma_compat.title"))
                    .icon(() -> new ItemStack(ModBlocks.STONE_ANVIL.get()))  // .get() on DeferredHolder
                    .displayItems((params, output) -> {
                        // Add all BlockItems from BLOCKS (no .getValue()—direct stream of DeferredHolders)
                        ModBlocks.BLOCKS.getEntries().stream()
                                .map(DeferredHolder::get)  // Block from DeferredHolder
                                .map(block -> block.asItem())  // Item from Block
                                .filter(item -> item != net.minecraft.world.item.Items.AIR)  // Skip invalid
                                .map(item -> item.getDefaultInstance())  // ItemStack (or new ItemStack(item))
                                .forEach(output::accept);

                        // Add all from ORES map (values() are DeferredBlock/DeferredHolder)
                        ModBlocks.ORES.values().stream()
                                .map(DeferredHolder::get)
                                .map(block -> block.asItem())
                                .filter(item -> item != net.minecraft.world.item.Items.AIR)
                                .map(item -> item.getDefaultInstance())
                                .forEach(output::accept);

                        // Add all wood blocks from WOODS map
                        ModCompatBlocks.WOODS.values().stream()
                                .flatMap(innerMap -> innerMap.values().stream())  // Flatten inner maps
                                .map(id -> id.asItem())  // Id implements ItemLike
                                .filter(item -> item != Items.AIR)
                                .map(item -> item.getDefaultInstance())
                                .forEach(output::accept);

                        // Add all ceiling hanging signs from CEILING_HANGING_SIGNS map
                        ModCompatBlocks.CEILING_HANGING_SIGNS.values().stream()
                                .flatMap(innerMap -> innerMap.values().stream())  // Flatten inner maps
                                .map(id -> id.asItem())  // Id implements ItemLike
                                .filter(item -> item != Items.AIR)
                                .map(item -> item.getDefaultInstance())
                                .forEach(output::accept);

                        // Add all wall hanging signs from WALL_HANGING_SIGNS map
                        ModCompatBlocks.WALL_HANGING_SIGNS.values().stream()
                                .flatMap(innerMap -> innerMap.values().stream())  // Flatten inner maps
                                .map(id -> id.asItem())  // Id implements ItemLike
                                .filter(item -> item != Items.AIR)
                                .map(item -> item.getDefaultInstance())
                                .forEach(output::accept);

                        // Add all lumber items from LUMBER map
                        ModCompatItems.LUMBER.values().stream()
                                .map(id -> id.asItem())
                                .filter(item -> item != Items.AIR)
                                .map(item -> item.getDefaultInstance())
                                .forEach(output::accept);

                        // Add all support items from SUPPORTS map
                        ModCompatItems.SUPPORTS.values().stream()
                                .map(id -> id.asItem())
                                .filter(item -> item != Items.AIR)
                                .map(item -> item.getDefaultInstance())
                                .forEach(output::accept);

                        // Add all chest minecart items from CHEST_MINECARTS map
                        ModCompatItems.CHEST_MINECARTS.values().stream()
                                .map(id -> id.asItem())
                                .filter(item -> item != Items.AIR)
                                .map(item -> item.getDefaultInstance())
                                .forEach(output::accept);

                        // Add all hanging sign items from HANGING_SIGNS map
                        ModCompatItems.HANGING_SIGNS.values().stream()
                                .flatMap(innerMap -> innerMap.values().stream())  // Flatten inner maps
                                .map(id -> id.asItem())
                                .filter(item -> item != Items.AIR)
                                .map(item -> item.getDefaultInstance())
                                .forEach(output::accept);

                        })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
