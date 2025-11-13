package com.bumppo109.firma_compat.items;

import com.bumppo109.firma_compat.FirmaCompatibility;
import com.bumppo109.firma_compat.blocks.ModBlocks;
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
                        })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
