package com.bumppo109.firma_compat;

import com.bumppo109.firma_compat.blocks.ModBlocks;
import com.bumppo109.firma_compat.event.ModCompatEvent;
import com.bumppo109.firma_compat.items.ModCreativeTabs;
import com.bumppo109.firma_compat.items.ModItems;
import com.bumppo109.firma_compat.loot_modifiers.ModLootModifiers;
import com.bumppo109.firma_compat.modules.TFCStoneModule;
import com.bumppo109.firma_compat.modules.TFCWoodModule;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FirmaCompatibility.MODID)
public class FirmaCompatibility {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "firma_compat";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    protected void addModules(){
        addIfLoaded("firma_compat", () -> TFCWoodModule::new);
        addIfLoaded("firma_compat", () -> TFCStoneModule::new);
    }

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public FirmaCompatibility(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Data overload
        //modEventBus.addListener(ModCompatEvent::onPackFinder);
        modEventBus.addListener(ModCompatEvent::addToBlockEntities);

        NeoForge.EVENT_BUS.register(this);

        ModCreativeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlocks.registerOres(modEventBus);
        ModItems.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        //this.addModules();

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        ModBlocks.ORES.values().forEach(oreBlock -> {
            ItemBlockRenderTypes.setRenderLayer(oreBlock.get(), RenderType.cutout());
        });
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CASSITERITE_GRAVEL_DEPOSIT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.NATIVE_COPPER_GRAVEL_DEPOSIT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.NATIVE_GOLD_GRAVEL_DEPOSIT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.NATIVE_SILVER_GRAVEL_DEPOSIT.get(), RenderType.cutout());
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == ModCreativeTabs.FIRMA_COMPAT_TAB) {
            // Add all registered blocks (including ores via BLOCKS.getEntries())
            ModBlocks.BLOCKS.getEntries().stream()
                    .map(entry -> entry.get().asItem()) // Get the BlockItem
                    .forEach(event::accept);

        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
