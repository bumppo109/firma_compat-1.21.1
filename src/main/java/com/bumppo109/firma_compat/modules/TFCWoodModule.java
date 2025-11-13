package com.bumppo109.firma_compat.modules;

import com.google.gson.JsonObject;
import net.dries007.tfc.client.render.blockentity.ToolRackBlockEntityRenderer;
import net.dries007.tfc.common.blockentities.ToolRackBlockEntity;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.GroundcoverBlock;
import net.dries007.tfc.common.blocks.GroundcoverBlockType;
import net.dries007.tfc.common.blocks.rotation.AxleBlock;
import net.dries007.tfc.common.blocks.rotation.WindmillBlock;
import net.dries007.tfc.common.blocks.wood.TFCLoomBlock;
import net.dries007.tfc.common.blocks.wood.ToolRackBlock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.dries007.tfc.common.blocks.devices.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LoomBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.getATagOrCreateANew;

public class TFCWoodModule extends SimpleModule {
    public final ItemOnlyEntrySet<WoodType, Item> LUMBER;
    public final SimpleEntrySet<WoodType, Block> TWIG;

    public TFCWoodModule(String modId) {
        super(modId, "firma_compat");

        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        LUMBER = ItemOnlyEntrySet.builder(WoodType.class, "lumber",
                        getModItem("oak_lumber"), () -> VanillaWoodTypes.OAK,
                        w -> new Item(new Item.Properties())
                )
                .setTabKey(tab)
                .addTexture(modRes("item/oak_lumber"), PaletteStrategies.MAIN_CHILD)
                .addTag(modRes("lumber"), Registries.ITEM)
                .build();
        this.addEntry(LUMBER);

        TWIG = SimpleEntrySet.builder(WoodType.class, "twig",
                        getModBlock("oak_twig"), () -> VanillaWoodTypes.OAK,
                        w -> new GroundcoverBlock(GroundcoverBlockType.STICK)
                )
                .addTexture(modRes("item/oak_twig"), PaletteStrategies.MAIN_CHILD)
                .addTexture(ResourceLocation.parse("minecraft:block/oak_log"), PaletteStrategies.LOG_SIDE_STANDARD)
                .addTexture(ResourceLocation.parse("minecraft:block/oak_log_top"), PaletteStrategies.STRIPPED_LOG_TOP_STANDARD)
                .addTag(modRes("twigs"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("twigs"), Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(TWIG);

    }

    @Override
    // RECIPES, TAGS
    //everycomp log tags formatted -> everycomp:[modid]/[woodType]_logs
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) -> {
            for(var woodType : WoodTypeRegistry.INSTANCE){
                //tfc fuel data
                fuelData(woodType, sink, manager);
            }
        });
    }

    public void fuelData(WoodType woodType, ResourceSink sink, ResourceManager manager) {

        ResourceLocation dataLoc = modRes("tfc/fuel/oak_logs.json");

        try (InputStream dataStream = manager.getResource(dataLoc)
                .orElseThrow(() -> new FileNotFoundException("File not found @ " + dataLoc)).open()) {

            JsonObject fuelData = RPUtils.deserializeJson(dataStream);

            // Editing the recipe
            fuelData.getAsJsonObject("ingredient")
                    .addProperty("tag", getATagOrCreateANew("logs", "caps", woodType, sink, manager).toString());

            // Adding to resources
            sink.addJson(
                    modRes("tfc/fuel/" + woodType.getTypeName() + "_logs.json"),
                    fuelData,
                    ResType.GENERIC
            );

        }
        catch (IOException e) {
            EveryCompat.LOGGER.error("Failed to generate the tfc data - fuel for {} : {}", woodType.getId(), e);
        }
    }
}
