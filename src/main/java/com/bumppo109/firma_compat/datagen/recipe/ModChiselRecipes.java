package com.bumppo109.firma_compat.datagen.recipe;

import com.bumppo109.firma_compat.block.CompatRock;
import com.bumppo109.firma_compat.block.CompatWood;
import com.bumppo109.firma_compat.block.ModBlocks;
import com.bumppo109.firma_compat.item.ModItems;
import net.dries007.tfc.common.blocks.DecorationBlockHolder;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.player.ChiselMode;
import net.dries007.tfc.common.recipes.ChiselRecipe;
import net.dries007.tfc.common.recipes.ingredients.BlockIngredient;
import net.dries007.tfc.common.recipes.outputs.ItemStackProvider;
import net.dries007.tfc.util.Metal;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Supplier;

public interface ModChiselRecipes extends ModRecipes
{
    default void chiselRecipes()
    {
        //Rock
        stairSlab(
                () -> Blocks.STONE,
                () -> Blocks.STONE_STAIRS,
                () -> Blocks.STONE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.STONE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.COBBLESTONE,
                () -> Blocks.COBBLESTONE_STAIRS,
                () -> Blocks.COBBLESTONE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.STONE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.MOSSY_COBBLESTONE,
                () -> Blocks.MOSSY_COBBLESTONE_STAIRS,
                () -> Blocks.MOSSY_COBBLESTONE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.STONE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.STONE_BRICKS,
                () -> Blocks.STONE_BRICK_STAIRS,
                () -> Blocks.STONE_BRICK_SLAB,
                ModItems.STONE_BRICK.get()
        );
        stairSlab(
                () -> Blocks.MOSSY_STONE_BRICKS,
                () -> Blocks.MOSSY_STONE_BRICK_STAIRS,
                () -> Blocks.MOSSY_STONE_BRICK_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.STONE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.GRANITE,
                () -> Blocks.GRANITE_STAIRS,
                () -> Blocks.GRANITE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.GRANITE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.POLISHED_GRANITE,
                () -> Blocks.POLISHED_GRANITE_STAIRS,
                () -> Blocks.POLISHED_GRANITE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.GRANITE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.ANDESITE,
                () -> Blocks.ANDESITE_STAIRS,
                () -> Blocks.ANDESITE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.ANDESITE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.POLISHED_ANDESITE,
                () -> Blocks.POLISHED_ANDESITE_STAIRS,
                () -> Blocks.POLISHED_ANDESITE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.ANDESITE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.DIORITE,
                () -> Blocks.DIORITE_STAIRS,
                () -> Blocks.DIORITE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.DIORITE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.POLISHED_DIORITE,
                () -> Blocks.POLISHED_DIORITE_STAIRS,
                () -> Blocks.POLISHED_DIORITE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.DIORITE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.COBBLED_DEEPSLATE,
                () -> Blocks.COBBLED_DEEPSLATE_STAIRS,
                () -> Blocks.COBBLED_DEEPSLATE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.DEEPSLATE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.POLISHED_DEEPSLATE,
                () -> Blocks.POLISHED_DEEPSLATE_STAIRS,
                () -> Blocks.POLISHED_DEEPSLATE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.DEEPSLATE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.DEEPSLATE_TILES,
                () -> Blocks.DEEPSLATE_TILE_STAIRS,
                () -> Blocks.DEEPSLATE_TILE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.DEEPSLATE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.TUFF,
                () -> Blocks.TUFF_STAIRS,
                () -> Blocks.TUFF_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.TUFF).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.POLISHED_TUFF,
                () -> Blocks.POLISHED_TUFF_STAIRS,
                () -> Blocks.POLISHED_TUFF_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.TUFF).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.TUFF_BRICKS,
                () -> Blocks.TUFF_BRICK_STAIRS,
                () -> Blocks.TUFF_BRICK_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.TUFF).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.BRICKS,
                () -> Blocks.BRICK_STAIRS,
                () -> Blocks.BRICK_SLAB,
                Items.BRICK
        );
        stairSlabNoExtra(
                () -> Blocks.MUD_BRICKS,
                () -> Blocks.MUD_BRICK_STAIRS,
                () -> Blocks.MUD_BRICK_SLAB
        );
        stairSlabNoExtra(
                () -> Blocks.SANDSTONE,
                () -> Blocks.SANDSTONE_STAIRS,
                () -> Blocks.SANDSTONE_SLAB
        );
        stairSlabNoExtra(
                () -> Blocks.SMOOTH_SANDSTONE,
                () -> Blocks.SMOOTH_SANDSTONE_STAIRS,
                () -> Blocks.SMOOTH_SANDSTONE_SLAB
        );
        stairSlabNoExtra(
                () -> Blocks.RED_SANDSTONE,
                () -> Blocks.RED_SANDSTONE_STAIRS,
                () -> Blocks.RED_SANDSTONE_SLAB
        );
        stairSlabNoExtra(
                () -> Blocks.SMOOTH_RED_SANDSTONE,
                () -> Blocks.SMOOTH_RED_SANDSTONE_STAIRS,
                () -> Blocks.SMOOTH_RED_SANDSTONE_SLAB
        );
        stairSlab(
                () -> Blocks.PRISMARINE,
                () -> Blocks.PRISMARINE_STAIRS,
                () -> Blocks.PRISMARINE_SLAB,
                Items.PRISMARINE_SHARD
        );
        stairSlab(
                () -> Blocks.PRISMARINE_BRICKS,
                () -> Blocks.PRISMARINE_BRICK_STAIRS,
                () -> Blocks.PRISMARINE_BRICK_SLAB,
                Items.PRISMARINE_SHARD
        );
        stairSlab(
                () -> Blocks.DARK_PRISMARINE,
                () -> Blocks.DARK_PRISMARINE_STAIRS,
                () -> Blocks.DARK_PRISMARINE_SLAB,
                Items.PRISMARINE_SHARD
        );
        stairSlab(
                () -> Blocks.NETHER_BRICKS,
                () -> Blocks.NETHER_BRICK_STAIRS,
                () -> Blocks.NETHER_BRICK_SLAB,
                Items.NETHER_BRICK
        );
        stairSlab(
                () -> Blocks.RED_NETHER_BRICKS,
                () -> Blocks.RED_NETHER_BRICK_STAIRS,
                () -> Blocks.RED_NETHER_BRICK_SLAB,
                Items.NETHER_BRICK
        );
        stairSlab(
                () -> Blocks.BLACKSTONE,
                () -> Blocks.BLACKSTONE_STAIRS,
                () -> Blocks.BLACKSTONE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.BLACKSTONE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.POLISHED_BLACKSTONE,
                () -> Blocks.POLISHED_BLACKSTONE_STAIRS,
                () -> Blocks.POLISHED_BLACKSTONE_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.BLACKSTONE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.POLISHED_BLACKSTONE_BRICKS,
                () -> Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS,
                () -> Blocks.POLISHED_BLACKSTONE_BRICK_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.BLACKSTONE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlab(
                () -> Blocks.END_STONE_BRICKS,
                () -> Blocks.END_STONE_BRICK_STAIRS,
                () -> Blocks.END_STONE_BRICK_SLAB,
                ModBlocks.ROCK_BLOCKS.get(CompatRock.END_STONE).get(CompatRock.BlockType.LOOSE).get().asItem()
        );
        stairSlabNoExtra(
                () -> Blocks.PURPUR_BLOCK,
                () -> Blocks.PURPUR_STAIRS,
                () -> Blocks.PURPUR_SLAB
        );
        stairSlab(
                () -> Blocks.QUARTZ_BLOCK,
                () -> Blocks.QUARTZ_STAIRS,
                () -> Blocks.QUARTZ_SLAB,
                Items.QUARTZ
        );
        stairSlab(
                () -> Blocks.SMOOTH_QUARTZ,
                () -> Blocks.SMOOTH_QUARTZ_STAIRS,
                () -> Blocks.SMOOTH_QUARTZ_SLAB,
                Items.QUARTZ
        );
        chisel(
                List.of(() -> Blocks.SMOOTH_STONE),
                () -> Blocks.SMOOTH_STONE_SLAB,
                ChiselMode.SLAB,
                ItemStackProvider.of(ModBlocks.ROCK_BLOCKS.get(CompatRock.STONE).get(CompatRock.BlockType.LOOSE).get().asItem())
        );
        chisel(
                List.of(() -> Blocks.CUT_SANDSTONE),
                () -> Blocks.CUT_SANDSTONE_SLAB,
                ChiselMode.SLAB,
                ItemStackProvider.empty()
        );
        chisel(
                List.of(() -> Blocks.CUT_RED_SANDSTONE),
                () -> Blocks.CUT_RED_SANDSTONE_SLAB,
                ChiselMode.SLAB,
                ItemStackProvider.empty()
        );
        chisel(
                List.of(() -> Blocks.BASALT),
                () -> Blocks.SMOOTH_BASALT,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(ModBlocks.ROCK_BLOCKS.get(CompatRock.BASALT).get(CompatRock.BlockType.LOOSE).get().asItem())
        );
        chisel(
                List.of(() -> Blocks.SMOOTH_BASALT),
                () -> Blocks.POLISHED_BASALT,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(ModBlocks.ROCK_BLOCKS.get(CompatRock.BASALT).get(CompatRock.BlockType.LOOSE).get().asItem())
        );
        chisel(
                List.of(() -> Blocks.STONE_BRICKS),
                () -> Blocks.CHISELED_STONE_BRICKS,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(ModItems.STONE_BRICK)
        );
        chisel(
                List.of(() -> Blocks.DEEPSLATE_BRICKS),
                () -> Blocks.CHISELED_DEEPSLATE,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(ModItems.DEEPSLATE_BRICK)
        );
        chisel(
                List.of(() -> Blocks.POLISHED_TUFF),
                () -> Blocks.CHISELED_TUFF,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(ModBlocks.ROCK_BLOCKS.get(CompatRock.TUFF).get(CompatRock.BlockType.LOOSE).get().asItem())
        );
        chisel(
                List.of(() -> Blocks.TUFF_BRICKS),
                () -> Blocks.CHISELED_TUFF_BRICKS,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(ModItems.TUFF_BRICK)
        );
        chisel(
                List.of(() -> Blocks.CUT_SANDSTONE),
                () -> Blocks.CHISELED_SANDSTONE,
                ChiselMode.SMOOTH,
                ItemStackProvider.empty()
        );
        chisel(
                List.of(() -> Blocks.CUT_RED_SANDSTONE),
                () -> Blocks.CHISELED_RED_SANDSTONE,
                ChiselMode.SMOOTH,
                ItemStackProvider.empty()
        );
        chisel(
                List.of(() -> Blocks.NETHER_BRICKS),
                () -> Blocks.CHISELED_NETHER_BRICKS,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(Items.NETHER_BRICK)
        );
        chisel(
                List.of(() -> Blocks.POLISHED_BLACKSTONE_BRICKS),
                () -> Blocks.CHISELED_POLISHED_BLACKSTONE,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(ModItems.POLISHED_BLACKSTONE_BRICK.get())
        );
        chisel(
                List.of(() -> Blocks.QUARTZ_BRICKS),
                () -> Blocks.CHISELED_QUARTZ_BLOCK,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(ModItems.QUARTZ_BRICK.get())
        );

        //Wood
        stairSlab(
                () -> Blocks.ACACIA_PLANKS,
                () -> Blocks.ACACIA_STAIRS,
                () -> Blocks.ACACIA_SLAB,
                ModItems.LUMBER.get(CompatWood.ACACIA).get()
        );
        stairSlab(
                () -> Blocks.BIRCH_PLANKS,
                () -> Blocks.BIRCH_STAIRS,
                () -> Blocks.BIRCH_SLAB,
                ModItems.LUMBER.get(CompatWood.BIRCH).get()
        );
        stairSlab(
                () -> Blocks.CHERRY_PLANKS,
                () -> Blocks.CHERRY_STAIRS,
                () -> Blocks.CHERRY_SLAB,
                ModItems.LUMBER.get(CompatWood.CHERRY).get()
        );
        stairSlab(
                () -> Blocks.DARK_OAK_PLANKS,
                () -> Blocks.DARK_OAK_STAIRS,
                () -> Blocks.DARK_OAK_SLAB,
                ModItems.LUMBER.get(CompatWood.DARK_OAK).get()
        );
        stairSlab(
                () -> Blocks.JUNGLE_PLANKS,
                () -> Blocks.JUNGLE_STAIRS,
                () -> Blocks.JUNGLE_SLAB,
                ModItems.LUMBER.get(CompatWood.JUNGLE).get()
        );
        stairSlab(
                () -> Blocks.MANGROVE_PLANKS,
                () -> Blocks.MANGROVE_STAIRS,
                () -> Blocks.MANGROVE_SLAB,
                ModItems.LUMBER.get(CompatWood.MANGROVE).get()
        );
        stairSlab(
                () -> Blocks.OAK_PLANKS,
                () -> Blocks.OAK_STAIRS,
                () -> Blocks.OAK_SLAB,
                ModItems.LUMBER.get(CompatWood.OAK).get()
        );
        stairSlab(
                () -> Blocks.SPRUCE_PLANKS,
                () -> Blocks.SPRUCE_STAIRS,
                () -> Blocks.SPRUCE_SLAB,
                ModItems.LUMBER.get(CompatWood.SPRUCE).get()
        );
        stairSlab(
                () -> Blocks.CRIMSON_PLANKS,
                () -> Blocks.CRIMSON_STAIRS,
                () -> Blocks.CRIMSON_SLAB,
                ModItems.LUMBER.get(CompatWood.CRIMSON).get()
        );
        stairSlab(
                () -> Blocks.WARPED_PLANKS,
                () -> Blocks.WARPED_STAIRS,
                () -> Blocks.WARPED_SLAB,
                ModItems.LUMBER.get(CompatWood.WARPED).get()
        );

        //Copper
        chisel(
                List.of(() -> TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.BLOCK).get()),
                () -> Blocks.CHISELED_COPPER,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(Blocks.CHISELED_COPPER.asItem())
        );
        chisel(
                List.of(() -> TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.EXPOSED_BLOCK).get()),
                () -> Blocks.EXPOSED_CHISELED_COPPER,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(Blocks.EXPOSED_CHISELED_COPPER.asItem())
        );
        chisel(
                List.of(() -> TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.WEATHERED_BLOCK).get()),
                () -> Blocks.WEATHERED_CHISELED_COPPER,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(Blocks.WEATHERED_CHISELED_COPPER.asItem())
        );
        chisel(
                List.of(() -> TFCBlocks.METALS.get(Metal.COPPER).get(Metal.BlockType.OXIDIZED_BLOCK).get()),
                () -> Blocks.OXIDIZED_CHISELED_COPPER,
                ChiselMode.SMOOTH,
                ItemStackProvider.of(Blocks.OXIDIZED_CHISELED_COPPER.asItem())
        );
    }

    private void stairSlabNoExtra(Supplier<? extends Block> input, Supplier<? extends Block> stair, Supplier<? extends Block> slab) {
        chisel(List.of(input), stair, ChiselMode.STAIR, ItemStackProvider.empty());
        chisel(List.of(input), slab, ChiselMode.SLAB, ItemStackProvider.empty());
    }

    private void stairSlab(Supplier<? extends Block> input, Supplier<? extends Block> stair, Supplier<? extends Block> slab, Item outputItem)
    {
        chisel(List.of(input), stair, ChiselMode.STAIR, ItemStackProvider.empty());
        chisel(List.of(input), slab, ChiselMode.SLAB, ItemStackProvider.of(outputItem));
    }

    private void chisel(Supplier<? extends Block> input, Supplier<? extends Block> output)
    {
        chisel(List.of(input), output);
    }

    private void chisel(List<? extends Supplier<? extends Block>> input, Supplier<? extends Block> output)
    {
        chisel(input, output, ChiselMode.SMOOTH, ItemStackProvider.empty());
    }

    private void chisel(List<? extends Supplier<? extends Block>> input, Supplier<? extends Block> output, Holder<ChiselMode> mode, ItemStackProvider outputItem)
    {
        add(new ChiselRecipe(
                BlockIngredient.of(input.stream().map(Supplier::get)),
                output.get().defaultBlockState(),
                mode.value(),
                outputItem
        ));
    }
}