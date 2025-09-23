package com.bumppo109.firma_compat.modules;

import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.items.TFCItems;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class TFCWoodModule extends SimpleModule {
    public final ItemOnlyEntrySet<WoodType, Item> LUMBER;

    public TFCWoodModule(String modId) {
        super(modId, "tfc");

        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        LUMBER = ItemOnlyEntrySet.builder(WoodType.class, "lumber",
                        TFCItems.LUMBER.get(Wood.OAK), () -> VanillaWoodTypes.OAK,
                        w -> new Item(new Item.Properties())
                )
                .setTabKey(tab)
                .addTexture(modRes("item/wood/lumber_oak"), PaletteStrategies.MAIN_CHILD)
                .addTag(modRes("lumber"), Registries.ITEM)
                .build();
        this.addEntry(LUMBER);
    }
}
