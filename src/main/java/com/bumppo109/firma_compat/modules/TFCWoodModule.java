package com.bumppo109.firma_compat.modules;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blockentities.*;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.GroundcoverBlock;
import net.dries007.tfc.common.blocks.GroundcoverBlockType;
import net.dries007.tfc.common.blocks.wood.ToolRackBlock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.dries007.tfc.common.blocks.devices.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class TFCWoodModule extends SimpleModule {
    public final ItemOnlyEntrySet<WoodType, Item> LUMBER;
    public final SimpleEntrySet<WoodType, Block> TWIG;
    public final SimpleEntrySet<WoodType, ToolRackBlock> TOOL_RACK;
    public final SimpleEntrySet<WoodType, BarrelBlock> BARREL;


    public TFCWoodModule(String modId) {
        super(modId, "firma_compat");

        //ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        ResourceLocation tab = modRes(TerraFirmaCraft.MOD_ID);

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
                //.addTexture(modRes("block/oak_log"), PaletteStrategies.LOG_SIDE_STANDARD)
                //.addTexture(modRes("block/oak_log_top"), PaletteStrategies.STRIPPED_LOG_TOP_STANDARD)
                .addTag(modRes("twigs"), Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("twigs"), Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(TWIG);

        TOOL_RACK = SimpleEntrySet.builder(WoodType.class, "tool_rack",
                getModBlock("wood/tool_rack/oak", ToolRackBlock.class), () -> VanillaWoodTypes.OAK,
                woodType -> new ToolRackBlock(ExtendedProperties.of())
                )
                //.addTile("tool_rack") - startup errors
                .addTile(ToolRackBlockEntity::new)
                .setTabKey(tab)
                .build();
        this.addEntry(TOOL_RACK);

        BARREL = SimpleEntrySet.builder(WoodType.class, "barrel",
                        getModBlock("wood/barrel/oak", BarrelBlock.class), () -> VanillaWoodTypes.OAK,
                        woodType -> new BarrelBlock(ExtendedProperties.of())
                )
                //.addTile("tool_rack") - startup errors
                .addTile(BarrelBlockEntity::new)
                .setTabKey(tab)
                .build();
        this.addEntry(BARREL);

    }
}
