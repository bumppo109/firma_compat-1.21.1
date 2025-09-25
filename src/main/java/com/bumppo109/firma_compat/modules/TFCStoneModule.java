package com.bumppo109.firma_compat.modules;

import net.dries007.tfc.common.blocks.rock.LooseRockBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class TFCStoneModule extends StoneZoneModule {
    //ore - graded
    //ore

    //hardened rock
    //cobbled + stairs, slab, wall
    //mossy_cobbled + stairs, slab, wall
    //hardened_cobbled + stairs, slab, wall
    //mossy_hardened_cobbled + stairs, slab, wall
    //bricks + stairs, slab, wall
    //mossy_bricks + stairs, slab, wall
    //cracked_bricks + stairs, slab, wall

    //brick
    //aqueduct
    //loose_rock
    //mossy_loose_rock

    //drying mud brick block

    public final SimpleEntrySet<StoneType, Block> LOOSE_ROCK;

    public TFCStoneModule(String modId) {
        super(modId, "firma_compat");

        ResourceKey<CreativeModeTab> tab = CreativeModeTabs.BUILDING_BLOCKS;

        // LOOSE_ROCK = StoneZoneEntrySet.of(StoneType.class,null,"rock/loose/", //TODO - seems following the TFC prefix path crashes EC
        LOOSE_ROCK = StoneZoneEntrySet.of(StoneType.class,"loose_rock",
                getModBlock("rock/loose/andesite"), () -> VanillaStoneTypes.ANDESITE,
                stoneType -> new LooseRockBlock(Utils.copyPropertySafe(stoneType.stone))
                )
                .createPaletteFromStone()
                .addTexture(modRes("item/loose_rock/andesite"))
                .addTag(modRes("loose_rocks"), Registries.BLOCK)
                .addTag(modRes("rock_knapping"), Registries.ITEM)
                .addTag(modRes("igneous_extrusive_items"), Registries.ITEM)
                .addTag(modRes("igneous_extrusive_rock"), Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(LOOSE_ROCK);



    }

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);

        //place stoneType mainChild texture in tfc/textures/gui/knapping/rock/loose
        //place stoneType mainChild texture with mossy mask in tfc/textures/gui/knapping/rock/mossy_loose
        //place stoneType mainChild texture in loose rock location

    }

}
