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
    //public final SimpleEntrySet<WoodType, BarrelBlock> BOOKSHELF;
    //public final SimpleEntrySet<WoodType, BarrelBlock> LOG_FENCE;
    //public final SimpleEntrySet<WoodType, ToolRackBlock> TOOL_RACK;
    //public static BlockEntityType<ToolRackBlockEntity> TOOL_RACK_ENTITY;
    //public final SimpleEntrySet<WoodType, BarrelBlock> CRAFTING_TABLE;
    //public final SimpleEntrySet<WoodType, BarrelBlock> CHEST;
    //public final SimpleEntrySet<WoodType, BarrelBlock> TRAPPED_CHEST;
    //public final SimpleEntrySet<WoodType, TFCLoomBlock> LOOM;
    //public final SimpleEntrySet<WoodType, BarrelBlock> SLUICE;
    //public final SimpleEntrySet<WoodType, BarrelBlock> BARREL;
    //public final SimpleEntrySet<WoodType, BarrelBlock> LECTERN;
    //public final SimpleEntrySet<WoodType, BarrelBlock> SCRIBING_TABLE;
    //public final SimpleEntrySet<WoodType, BarrelBlock> SEWING_TABLE;
    //public final SimpleEntrySet<WoodType, BarrelBlock> SHELF;
    //public final SimpleEntrySet<WoodType, BarrelBlock> BOAT;
    //public final SimpleEntrySet<WoodType, BarrelBlock> SUPPORT;
    //public final SimpleEntrySet<WoodType, BarrelBlock> CHEST_MINECART;

    //public final SimpleEntrySet<WoodType, BarrelBlock> AXLE;
    //public final SimpleEntrySet<WoodType, BarrelBlock> BLADED_AXLE;
    //public final SimpleEntrySet<WoodType, BarrelBlock> ENCASED_AXLE;
    //public final SimpleEntrySet<WoodType, BarrelBlock> CLUTCH;
    //public final SimpleEntrySet<WoodType, BarrelBlock> GEAR_BOX;
    //public final SimpleEntrySet<WoodType, BarrelBlock> WATER_WHEEL;

    //public final SimpleEntrySet<WoodType, BarrelBlock> BISMUTH_BRONZE_HANGING_SIGN;
    //public final SimpleEntrySet<WoodType, BarrelBlock> BLACK_BRONZE_HANGING_SIGN;
    //public final SimpleEntrySet<WoodType, BarrelBlock> BRONZE_HANGING_SIGN;
    //public final SimpleEntrySet<WoodType, BarrelBlock> COPPER_HANGING_SIGN;
    //public final SimpleEntrySet<WoodType, BarrelBlock> WROUGHT_IRON_HANGING_SIGN;
    //public final SimpleEntrySet<WoodType, BarrelBlock> STEEL_HANGING_SIGN;
    //public final SimpleEntrySet<WoodType, BarrelBlock> BLACK_STEEL_HANGING_SIGN;
    //public final SimpleEntrySet<WoodType, BarrelBlock> BLUE_STEEL_HANGING_SIGN;
    //public final SimpleEntrySet<WoodType, BarrelBlock> RED_STEEL_HANGING_SIGN;

    public TFCWoodModule(String modId) {
        super(modId, "tfc");

        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        LUMBER = ItemOnlyEntrySet.builder(WoodType.class, "lumber",
                        getModItem("wood/lumber/oak"), () -> VanillaWoodTypes.OAK,
                        w -> new Item(new Item.Properties())
                )
                .setTabKey(tab)
                .addTexture(modRes("item/oak_lumber"), PaletteStrategies.MAIN_CHILD)
                .addTag(modRes("lumber"), Registries.ITEM)
                .build();
        this.addEntry(LUMBER);

        TWIG = SimpleEntrySet.builder(WoodType.class, "twig",
                        getModBlock("wood/twig/oak"), () -> VanillaWoodTypes.OAK,
                        w -> new GroundcoverBlock(GroundcoverBlockType.STICK)
                )
                .addTexture(modRes("item/wood/twig"), PaletteStrategies.MAIN_CHILD)
                .addTexture(modRes("block/wood/log/oak"), PaletteStrategies.LOG_SIDE_STANDARD)
                .addTexture(modRes("block/wood/log_top/oak"), PaletteStrategies.STRIPPED_LOG_TOP_STANDARD)
                .addTag(modRes("twigs"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("twigs"), Registries.ITEM)
                .addRecipe(modRes("crafting/wood/tool_rack/oak"))
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

    /*
    public void createLogTag(String blockType, SimpleEntrySet<?,?> firstBlock, SimpleEntrySet<?,?> secondBlock, ResourceSink handler) {
        for (var w : firstBlock.blocks.keySet()) {
            boolean isTagFull = false;
            SimpleTagBuilder tag = SimpleTagBuilder.of(EveryCompat.res(w.getAppendableId() + "_logs"));
            Block firstB = firstBlock.blocks.get(w);
            Block secondB = secondBlock.blocks.get(w);

            if (firstB != null) {
                isTagFull = true;
                tag.addEntry(firstB);
            }
            if (secondB != null) {
                isTagFull = true;
                tag.addEntry(secondB);
            }
            if (isTagFull) {
                handler.addTag(tag, Registries.ITEM);
                handler.addTag(tag, Registries.BLOCK);
            }
        }
    }
     */

}
