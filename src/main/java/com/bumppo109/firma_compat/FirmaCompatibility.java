package com.bumppo109.firma_compat;

import com.bumppo109.firma_compat.blockentities.ModCompatBlockEntities;
import com.bumppo109.firma_compat.blocks.ModBlocks;
import com.bumppo109.firma_compat.blocks.ModCompatBlocks;
import com.bumppo109.firma_compat.blocks.wood.VanillaWood;
import com.bumppo109.firma_compat.event.ModEventClientBusEvents;
import com.bumppo109.firma_compat.items.ModCompatItems;
import com.bumppo109.firma_compat.items.ModItems;
import com.bumppo109.firma_compat.loot_modifiers.ModLootModifiers;
import com.bumppo109.firma_compat.mixin.BlockEntityTypeAccessorMixin;
import com.bumppo109.firma_compat.modules.TFCStoneModule;
import com.bumppo109.firma_compat.modules.TFCWoodModule;
import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static net.mehvahdjukaar.every_compat.EveryCompat.addIfLoaded;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FirmaCompatibility.MODID)
public class FirmaCompatibility {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "firma_compat";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    protected void addModules(){
        addIfLoaded("tfc", () -> TFCWoodModule::new);
        addIfLoaded("tfc", () -> TFCStoneModule::new);
    }

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public FirmaCompatibility(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModBlocks.register(modEventBus);
        ModBlocks.registerOres(modEventBus);
        ModItems.register(modEventBus);

        ModCompatBlocks.BLOCKS.register(modEventBus);
        ModCompatItems.ITEMS.register(modEventBus);
        ModCompatBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        //this.addModules();

        modEventBus.addListener(this::addCreative);

        //afc compat
        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            modEventBus.addListener(ModEventClientBusEvents::clientSetup);
            //modEventBus.addListener(ModEventClientBusEvents::registerClientReloadListeners);
            modEventBus.addListener(ModEventClientBusEvents::registerEntityLayers);
            //modEventBus.addListener(ModEventClientBusEvents::registerColorHandlerBlocks);
            modEventBus.addListener(ModEventClientBusEvents::registerColorHandlerItems);
            modEventBus.addListener(ModEventClientBusEvents::onLayers);
            modEventBus.addListener(ModEventClientBusEvents::registerExtensions);
        }

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("AFC COMMON SETUP");
        event.enqueueWork(VanillaWood::registerBlockSetTypes);
        event.enqueueWork(() -> {
            ModCompatBlocks.registerFlowerPotFlowers();
            modifyBlockEntityTypes();
        });
    }

    private static void modifyBlockEntityTypes()
    {
        modifyWood(TFCBlockEntities.AXLE.get(), Wood.BlockType.AXLE);
        modifyWood(TFCBlockEntities.BARREL.get(), Wood.BlockType.BARREL);
        modifyWood(TFCBlockEntities.BLADED_AXLE.get(), Wood.BlockType.BLADED_AXLE);
        modifyWood(TFCBlockEntities.BOOKSHELF.get(), Wood.BlockType.BOOKSHELF);
        modifyWood(TFCBlockEntities.CHEST.get(), Wood.BlockType.CHEST);
        modifyWood(TFCBlockEntities.CLUTCH.get(), Wood.BlockType.CLUTCH);
        modifyWood(TFCBlockEntities.ENCASED_AXLE.get(), Wood.BlockType.ENCASED_AXLE);
        modifyWood(TFCBlockEntities.GEAR_BOX.get(), Wood.BlockType.GEAR_BOX);
        //hanging sign
        modifyWood(TFCBlockEntities.LECTERN.get(), Wood.BlockType.LECTERN);
        modifyWood(TFCBlockEntities.LOOM.get(), Wood.BlockType.LOOM);
        modifyWood(TFCBlockEntities.SHELF.get(), Wood.BlockType.SHELF);
        modifyWood(TFCBlockEntities.SLUICE.get(), Wood.BlockType.SLUICE);
        modifyWood(TFCBlockEntities.TOOL_RACK.get(), Wood.BlockType.TOOL_RACK);
        modifyWood(TFCBlockEntities.TRAPPED_CHEST.get(), Wood.BlockType.TRAPPED_CHEST);
        modifyWood(TFCBlockEntities.WATER_WHEEL.get(), Wood.BlockType.WATER_WHEEL);
        modifyWood(TFCBlockEntities.WINDMILL.get(), Wood.BlockType.WINDMILL);

        modifyBlockEntityType(TFCBlockEntities.TICK_COUNTER.get(), Stream.of(ModBlocks.DRYING_MUD_BRICK.get()));
        modifyBlockEntityType(TFCBlockEntities.ANVIL.get(), Stream.of(ModBlocks.STONE_ANVIL.get()));

        for (Metal metal : Metal.values())
        {
            if (metal.allParts())
            {
                for (VanillaWood wood : VanillaWood.values())
                {
                    modifyBlockEntityType(TFCBlockEntities.HANGING_SIGN.get(), Stream.of(ModCompatBlocks.CEILING_HANGING_SIGNS.get(wood).get(metal).get()));
                    modifyBlockEntityType(TFCBlockEntities.HANGING_SIGN.get(), Stream.of(ModCompatBlocks.WALL_HANGING_SIGNS.get(wood).get(metal).get()));
                }
            }
        }
    }

    private static void modifyWood(BlockEntityType<?> type, Wood.BlockType blockType)
    {
        modifyBlockEntityType(type, ModCompatBlocks.WOODS.values().stream().map(map -> map.get(blockType).get()));
    }

    private static void modifyBlockEntityType(BlockEntityType<?> type, Stream<Block> extraBlocks)
    {
        Set<Block> blocks = type.getValidBlocks();
        blocks = new HashSet<>(blocks);

        blocks.addAll(extraBlocks.toList());
        ((BlockEntityTypeAccessorMixin) type).accessor$setValidBlocks(blocks);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
