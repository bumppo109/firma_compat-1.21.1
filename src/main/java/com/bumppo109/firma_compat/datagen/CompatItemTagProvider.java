package com.bumppo109.firma_compat.datagen;

import com.bumppo109.firma_compat.blocks.ModCompatBlocks;
import com.bumppo109.firma_compat.blocks.wood.VanillaWood;
import com.bumppo109.firma_compat.items.ModCompatItems;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.dries007.tfc.common.TFCTags.Items.*;

public class CompatItemTagProvider extends ItemTagsProvider {
    public CompatItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, String modId, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTags, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        ModCompatItems.SUPPORTS.forEach(
                (w, i) -> tag(TFCTags.Items.SUPPORT_BEAMS).add(i.asItem())
        );
        //ModCompatItems.SIGNS.forEach(
        //        (w, i) -> tag(ItemTags.SIGNS).add(i.asItem())
        //);
        ModCompatItems.LUMBER.forEach(
                (w, i) -> tag(TFCTags.Items.LUMBER).add(i.asItem())
        );
        ModCompatItems.CHEST_MINECARTS.forEach(
                (w, i) -> tag(TFCTags.Items.MINECARTS).add(i.asItem())
        );

        ModCompatBlocks.WOODS.forEach(
                (w, m) -> tag(Tags.Items.BARRELS_WOODEN).add(w.getBlock(Wood.BlockType.BARREL).get().asItem())
        );

        ModCompatBlocks.WOODS.forEach(
                (w, i) -> tag(CARRIED_BY_HORSE)
                        .add(w.getBlock(Wood.BlockType.BARREL).get().asItem())
                        .add(w.getBlock(Wood.BlockType.CHEST).get().asItem())
                        .add(w.getBlock(Wood.BlockType.TRAPPED_CHEST).get().asItem())
        );
    }
}
