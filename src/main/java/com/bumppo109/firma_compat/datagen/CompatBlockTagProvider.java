package com.bumppo109.firma_compat.datagen;

import com.bumppo109.firma_compat.blocks.ModCompatBlocks;
import com.bumppo109.firma_compat.blocks.wood.VanillaWood;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CompatBlockTagProvider extends BlockTagsProvider {
    public CompatBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, modId, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        addAllCompatWoods(Wood.BlockType.BOOKSHELF, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.TOOL_RACK, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.TWIG, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.LOOM, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.SLUICE, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.BARREL, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.LECTERN, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.SCRIBING_TABLE, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.SEWING_TABLE, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.SHELF, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.AXLE, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.BLADED_AXLE, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.ENCASED_AXLE, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.CLUTCH, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.GEAR_BOX, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.HORIZONTAL_SUPPORT, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.VERTICAL_SUPPORT, BlockTags.MINEABLE_WITH_AXE);
        addAllCompatWoods(Wood.BlockType.WATER_WHEEL, BlockTags.MINEABLE_WITH_AXE);

        addAllCompatWoods(Wood.BlockType.LOG_FENCE, Tags.Blocks.FENCES_WOODEN);
        addAllCompatWoods(Wood.BlockType.LOG_FENCE, BlockTags.WOODEN_FENCES);
        addAllCompatWoods(Wood.BlockType.WORKBENCH, TFCTags.Blocks.WORKBENCHES);
        addAllCompatWoods(Wood.BlockType.CHEST, Tags.Blocks.CHESTS_WOODEN);
        addAllCompatWoods(Wood.BlockType.TRAPPED_CHEST, Tags.Blocks.CHESTS_WOODEN);
        addAllCompatWoods(Wood.BlockType.TRAPPED_CHEST, Tags.Blocks.CHESTS_TRAPPED);
        addAllCompatWoods(Wood.BlockType.HORIZONTAL_SUPPORT, TFCTags.Blocks.SUPPORT_BEAMS);
        addAllCompatWoods(Wood.BlockType.VERTICAL_SUPPORT, TFCTags.Blocks.SUPPORT_BEAMS);
        //addAllCompatWoods(Wood.BlockType.SIGN, BlockTags.STANDING_SIGNS);
        //addAllCompatWoods(Wood.BlockType.WALL_SIGN, BlockTags.WALL_SIGNS);
    }

    private void addAllCompatWoods(Wood.BlockType type, TagKey<Block> tagKey)
    {
        ModCompatBlocks.WOODS.forEach(
                (s, m) -> tag(tagKey).add(s.getBlock(type).get())
        );
    }
}
