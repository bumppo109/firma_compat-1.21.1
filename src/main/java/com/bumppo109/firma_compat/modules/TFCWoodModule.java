package com.bumppo109.firma_compat.modules;

import com.google.gson.JsonObject;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.GroundcoverBlock;
import net.dries007.tfc.common.blocks.GroundcoverBlockType;
import net.dries007.tfc.common.blocks.wood.ToolRackBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
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
    public final SimpleEntrySet<WoodType, ToolRackBlock> TOOL_RACK;
    //public final SimpleEntrySet<WoodType, BarrelBlock> CRAFTING_TABLE;
    //public final SimpleEntrySet<WoodType, BarrelBlock> CHEST;
    //public final SimpleEntrySet<WoodType, BarrelBlock> TRAPPED_CHEST;
    //public final SimpleEntrySet<WoodType, BarrelBlock> LOOM;
    //public final SimpleEntrySet<WoodType, BarrelBlock> SLUICE;
    public final SimpleEntrySet<WoodType, BarrelBlock> BARREL;
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
        super(modId, "firma_compat");

        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        LUMBER = ItemOnlyEntrySet.builder(WoodType.class, "lumber",
                        getModItem("wood/lumber/oak"), () -> VanillaWoodTypes.OAK,
                        w -> new Item(new Item.Properties())
                )
                .setTabKey(tab)
                //.addTexture(modRes("item/oak_lumber"), PaletteStrategies.MAIN_CHILD)
                .addTag(modRes("lumber"), Registries.ITEM)
                .build();
        this.addEntry(LUMBER);

        TWIG = SimpleEntrySet.builder(WoodType.class, "twig",
                        getModBlock("wood/twig/oak"), () -> VanillaWoodTypes.OAK,
                        w -> new GroundcoverBlock(GroundcoverBlockType.STICK)
                )
                //.addTexture(modRes("item/oak_twig"), PaletteStrategies.MAIN_CHILD)
                .addTexture(modRes("block/wood/log/oak"), PaletteStrategies.LOG_SIDE_STANDARD)
                .addTexture(modRes("block/wood/log_top/oak"), PaletteStrategies.STRIPPED_LOG_TOP_STANDARD)
                .addTag(modRes("twigs"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("twigs"), Registries.ITEM)
                .addRecipe(modRes("crafting/wood/tool_rack/oak"))
                .setTabKey(tab)
                .build();
        this.addEntry(TWIG);

        TOOL_RACK = SimpleEntrySet.builder(WoodType.class, "tool_rack",
                        getModBlock("wood/tool_rack/oak", ToolRackBlock.class), () -> VanillaWoodTypes.OAK,
                        woodType -> new ToolRackBlock(ExtendedProperties.of())
                )
                .addTile(getModTile("tool_rack"))
                //.addTile(getModTile("wood/tool_rack/oak"))
                //.addTile(ToolRackBlockEntity::new)
                .addRecipe(modRes("crafting/wood/barrel/oak"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("tool_racks"), Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(TOOL_RACK);

        BARREL = SimpleEntrySet.builder(WoodType.class, "barrel",
                        getModBlock("wood/barrel/oak", BarrelBlock.class), () -> VanillaWoodTypes.OAK,
                        woodType -> new BarrelBlock(ExtendedProperties.of())
                )
                .addTexture(modRes("block/wood/planks/oak"), PaletteStrategies.PLANKS_STANDARD)
                .addTexture(modRes("block/wood/sheet/oak"), PaletteStrategies.STRIPPED_LOG_SIDE_STANDARD)
                .addTile(getModTile("barrel"))
                //.addTile(BarrelBlockEntity::new)
                .defaultRecipe()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("barrels"), Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(BARREL);
    }

        @Override
        // RECIPES, TAGS
        public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
            super.addDynamicServerResources(executor);
            executor.accept((manager, sink) -> {
                for(var woodType : WoodTypeRegistry.INSTANCE){

                    //creates missing log tags as everycomp:[modid]/[woodType]_logs
                    fuelData(woodType, sink, manager);
                    //add horizontal_support block to tfc/support/horizontal_support_beam.json tag
                }
            });
        }

    public void fuelData(WoodType woodType, ResourceSink sink, ResourceManager manager) {

        ResourceLocation dataLoc = modRes("tfc/fuel/oak_logs.json"); //TODO - do I need to specify data/?

        try (InputStream dataStream = manager.getResource(dataLoc)
                .orElseThrow(() -> new FileNotFoundException("File not found @ " + dataLoc)).open()) {

            JsonObject fuelData = RPUtils.deserializeJson(dataStream);

            // Editing the recipe
            fuelData.getAsJsonObject("ingredient")
                    .addProperty("tag", getATagOrCreateANew("logs", "caps", woodType, sink, manager).toString());

            // Adding to resources
            sink.addJson(
                    modRes("tfc/fuel/" + woodType.getTypeName() + "_logs"),
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
