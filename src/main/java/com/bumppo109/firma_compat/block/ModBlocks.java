package com.bumppo109.firma_compat.block;

import com.bumppo109.firma_compat.FirmaCompatibility;
import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blockentities.TFCBlockEntity;
import net.dries007.tfc.common.blockentities.ToolRackBlockEntity;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.GroundcoverBlock;
import net.dries007.tfc.common.blocks.GroundcoverBlockType;
import net.dries007.tfc.common.blocks.rock.AqueductBlock;
import net.dries007.tfc.common.blocks.rock.LooseRockBlock;
import net.dries007.tfc.common.blocks.wood.ToolRackBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.attachment.AttachmentHolder;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(BuiltInRegistries.BLOCK, FirmaCompatibility.MODID);
//Twigs
    public static final Supplier<Block> ACACIA_TWIG = BLOCKS.register("acacia_twig",
            () -> new GroundcoverBlock(GroundcoverBlockType.STICK));
    public static final Supplier<Block> BIRCH_TWIG = BLOCKS.register("birch_twig",
            () -> new GroundcoverBlock(GroundcoverBlockType.STICK));
    public static final Supplier<Block> CHERRY_TWIG = BLOCKS.register("cherry_twig",
            () -> new GroundcoverBlock(GroundcoverBlockType.STICK));
    public static final Supplier<Block> DARK_OAK_TWIG = BLOCKS.register("dark_oak_twig",
            () -> new GroundcoverBlock(GroundcoverBlockType.STICK));
    public static final Supplier<Block> JUNGLE_TWIG = BLOCKS.register("jungle_twig",
            () -> new GroundcoverBlock(GroundcoverBlockType.STICK));
    public static final Supplier<Block> MANGROVE_TWIG = BLOCKS.register("mangrove_twig",
            () -> new GroundcoverBlock(GroundcoverBlockType.STICK));
    public static final Supplier<Block> OAK_TWIG = BLOCKS.register("oak_twig",
            () -> new GroundcoverBlock(GroundcoverBlockType.STICK));
    public static final Supplier<Block> PALE_OAK_TWIG = BLOCKS.register("pale_oak_twig",
            () -> new GroundcoverBlock(GroundcoverBlockType.STICK));
    public static final Supplier<Block> SPRUCE_TWIG = BLOCKS.register("spruce_twig",
            () -> new GroundcoverBlock(GroundcoverBlockType.STICK));

//Cobbled
    public static final Supplier<Block> COBBLED_ANDESITE = BLOCKS.register("cobbled_andesite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final Supplier<Block> COBBLED_DIORITE = BLOCKS.register("cobbled_diorite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final Supplier<Block> COBBLED_GRANITE = BLOCKS.register("cobbled_granite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final Supplier<Block> COBBLED_TUFF = BLOCKS.register("cobbled_tuff",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final Supplier<Block> COBBLED_CALCITE = BLOCKS.register("cobbled_calcite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final Supplier<Block> COBBLED_DRIPSTONE = BLOCKS.register("cobbled_dripstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final Supplier<Block> COBBLED_BASALT = BLOCKS.register("cobbled_basalt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final Supplier<Block> COBBLED_BLACKSTONE = BLOCKS.register("cobbled_blackstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final Supplier<Block> COBBLED_END_STONE = BLOCKS.register("cobbled_end_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));

//Bricks
    public static final Supplier<Block> ANDESITE_BRICKS = BLOCKS.register("andesite_bricks",
        () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE)));
    public static final Supplier<Block> DIORITE_BRICKS = BLOCKS.register("diorite_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE)));
    public static final Supplier<Block> GRANITE_BRICKS = BLOCKS.register("granite_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE)));
    public static final Supplier<Block> CALCITE_BRICKS = BLOCKS.register("calcite_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).sound(SoundType.CALCITE)));
    public static final Supplier<Block> DRIPSTONE_BRICKS = BLOCKS.register("dripstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).sound(SoundType.DRIPSTONE_BLOCK)));
    public static final Supplier<Block> BASALT_BRICKS = BLOCKS.register("basalt_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).sound(SoundType.BASALT)));

//Aqueduct
    public static final Supplier<Block> STONE_BRICK_AQUEDUCT = BLOCKS.register("stone_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final Supplier<Block> DEEPSLATE_BRICK_AQUEDUCT = BLOCKS.register("deepslate_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)));
    public static final Supplier<Block> ANDESITE_BRICK_AQUEDUCT = BLOCKS.register("andesite_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE)));
    public static final Supplier<Block> DIORITE_BRICK_AQUEDUCT = BLOCKS.register("diorite_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE)));
    public static final Supplier<Block> GRANITE_BRICK_AQUEDUCT = BLOCKS.register("granite_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE)));
    public static final Supplier<Block> TUFF_BRICK_AQUEDUCT = BLOCKS.register("tuff_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF_BRICKS)));
    public static final Supplier<Block> CALCITE_BRICK_AQUEDUCT = BLOCKS.register("calcite_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).sound(SoundType.CALCITE)));
    public static final Supplier<Block> DRIPSTONE_BRICK_AQUEDUCT = BLOCKS.register("dripstone_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).sound(SoundType.DRIPSTONE_BLOCK)));
    public static final Supplier<Block> BASALT_BRICK_AQUEDUCT = BLOCKS.register("basalt_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).sound(SoundType.BASALT)));
    public static final Supplier<Block> POLISHED_BLACKSTONE_BRICK_AQUEDUCT = BLOCKS.register("polished_blackstone_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)));
    public static final Supplier<Block> END_STONE_BRICK_AQUEDUCT = BLOCKS.register("end_stone_brick_aqueduct",
            () -> new AqueductBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE_BRICKS)));

//Loose Rock
    public static final Supplier<Block> LOOSE_STONE = BLOCKS.register("loose_stone",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> LOOSE_DEEPSLATE = BLOCKS.register("loose_deepslate",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)));
    public static final Supplier<Block> LOOSE_ANDESITE = BLOCKS.register("loose_andesite",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> LOOSE_DIORITE = BLOCKS.register("loose_diorite",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> LOOSE_GRANITE = BLOCKS.register("loose_granite",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Supplier<Block> LOOSE_TUFF = BLOCKS.register("loose_tuff",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF)));
    public static final Supplier<Block> LOOSE_CALCITE = BLOCKS.register("loose_calcite",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final Supplier<Block> LOOSE_DRIPSTONE = BLOCKS.register("loose_dripstone",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK)));
    public static final Supplier<Block> LOOSE_BASALT = BLOCKS.register("loose_basalt",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT)));
    public static final Supplier<Block> LOOSE_BLACKSTONE = BLOCKS.register("loose_blackstone",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)));
    public static final Supplier<Block> LOOSE_END_STONE = BLOCKS.register("loose_end_stone",
            () -> new LooseRockBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)));

}
