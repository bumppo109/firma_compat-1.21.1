package com.bumppo109.firma_compat.datagen;

import com.bumppo109.firma_compat.blocks.ModCompatBlocks;
import com.bumppo109.firma_compat.blocks.wood.VanillaWood;
import com.bumppo109.firma_compat.items.ModCompatItems;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Metal;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static net.minecraft.world.level.storage.loot.LootPool.lootPool;
import static net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem;
import static net.minecraft.world.level.storage.loot.predicates.ExplosionCondition.survivesExplosion;
import static net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition.randomChance;

public class CompatBlockLootProvider extends BlockLootSubProvider {
    protected CompatBlockLootProvider(HolderLookup.Provider registries)
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate()
    {
        // Blocks in the Wood BlockType registry or a similar registry
        for (Wood.BlockType type : Wood.BlockType.values())
        {
            ModCompatBlocks.WOODS.forEach(
                    (species, map) ->
                    {
                        createWood(species, type);
                    }
            );
        }

        // Hanging Signs
        for (Metal metal : Metal.values())
        {
            if (metal.allParts())
            {
                for (VanillaWood wood : VanillaWood.values())
                {
                    dropSelf(ModCompatBlocks.CEILING_HANGING_SIGNS.get(wood).get(metal).get());
                    dropSelf(ModCompatBlocks.WALL_HANGING_SIGNS.get(wood).get(metal).get());
                }
            }
        }
    }

    protected void createWood(VanillaWood species, Wood.BlockType blockType)
    {
        switch (blockType)
        {
            case VERTICAL_SUPPORT, HORIZONTAL_SUPPORT:
                dropOther(species.getBlock(blockType).get(), ModCompatItems.SUPPORTS.get(species));
                break;
            default:
                dropSelf(species.getBlock(blockType).get());
        }
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks()
    {
        return ModCompatBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    private LootItemCondition.Builder hasShearsOrSilkTouch()
    {
        return MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.TOOLS_SHEAR)).or(this.hasSilkTouch());
    }

    private LootItemCondition.Builder isHammer()
    {
        return MatchTool.toolMatches(ItemPredicate.Builder.item().of(TFCTags.Items.TOOLS_HAMMER));
    }
}
