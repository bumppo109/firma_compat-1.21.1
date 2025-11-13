package com.bumppo109.firma_compat.blocks;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.bumppo109.firma_compat.FirmaCompatibility;
import com.bumppo109.firma_compat.blocks.wood.VanillaWood;
import com.bumppo109.firma_compat.items.ModCompatItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.wood.TFCCeilingHangingSignBlock;
import net.dries007.tfc.common.blocks.wood.TFCWallHangingSignBlock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.dries007.tfc.util.registry.RegistryHolder;

public class ModCompatBlocks
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FirmaCompatibility.MODID);

    // These are separate because they make the BlockLootProvider very angry when I try to pass them in
    public static final DeferredRegister<Block> FLUID_BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FirmaCompatibility.MODID);

    public static final Map<VanillaWood, Map<Metal, Id<TFCCeilingHangingSignBlock>>> CEILING_HANGING_SIGNS = registerHangingSigns("hanging_sign", TFCCeilingHangingSignBlock::new);
    public static final Map<VanillaWood, Map<Metal, Id<TFCWallHangingSignBlock>>> WALL_HANGING_SIGNS = registerHangingSigns("wall_hanging_sign", TFCWallHangingSignBlock::new);

    public static final Map<VanillaWood, Map<Wood.BlockType, Id<Block>>> WOODS = Helpers.mapOf(VanillaWood.class, wood ->
            Helpers.mapOf(Wood.BlockType.class, type ->
                    register(type.nameFor(wood), createWood(wood, type), type.createBlockItem(wood, new Item.Properties()))
            )
    );

    public static Supplier<Block> createWood(VanillaWood VanillaWood, Wood.BlockType blockType)
    {
        return blockType.create(VanillaWood);
    }
/*
    public static final Map<TreeSpecies, Map<TreeSpecies.BlockType, Id<Block>>> TREE_SPECIES = Helpers.mapOf(TreeSpecies.class, wood ->
            Helpers.mapOf(TreeSpecies.BlockType.class, type ->
                    register((String) type.nameFor(wood), createTreeSpecies(wood, type), type.createBlockItem(new Item.Properties()))
            )
    );

    public static Supplier<Block> createTreeSpecies(TreeSpecies wood, TreeSpecies.BlockType blockType)
    {
        return blockType.create(wood);
    }

    public static final Map<UniqueLogs, Map<UniqueLogs.BlockType, Id<Block>>> UNIQUE_LOGS = Helpers.mapOf(UniqueLogs.class, wood ->
            Helpers.mapOf(UniqueLogs.BlockType.class, type ->
                    register(type.nameFor(wood), createUniqueLogs(wood, type), type.createBlockItem(new Item.Properties()))
            )
    );

    public static Supplier<Block> createUniqueLogs(UniqueLogs wood, UniqueLogs.BlockType blockType)
    {
        return blockType.create(wood);
    }

    public static final Map<AncientLogs, Map<AncientLogs.BlockType, Id<Block>>> ANCIENT_LOGS = Helpers.mapOf(AncientLogs.class, wood ->
            Helpers.mapOf(AncientLogs.BlockType.class, type ->
                    register(type.nameFor(wood), createAncientLogs(wood, type), type.createBlockItem(new Item.Properties()))
            )
    );

    public static Supplier<Block> createAncientLogs(AncientLogs wood, AncientLogs.BlockType blockType)
    {
        return blockType.create(wood);
    }

 */

    public static void registerFlowerPotFlowers()
    {
        FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;
        //WOODS.forEach((wood, map) -> pot.addPlant(map.get(Wood.BlockType.SAPLING).getId(), map.get(Wood.BlockType.POTTED_SAPLING)));
        //TREE_SPECIES.forEach((wood, map) -> pot.addPlant(map.get(TreeSpecies.BlockType.SAPLING).getId(), map.get(TreeSpecies.BlockType.POTTED_SAPLING)));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }

    protected static <T extends Block> Id<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties()));
    }

    private static <T extends Block> Id<T> register(String name, Supplier<T> blockSupplier, Item.Properties blockItemProperties)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, blockItemProperties));
    }

    private static <T extends Block> Id<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        return new Id<>(RegistrationHelpers.registerBlock(ModCompatBlocks.BLOCKS, ModCompatItems.ITEMS, name, blockSupplier, blockItemFactory));
    }

    private static <B extends SignBlock> Map<VanillaWood, Map<Metal, Id<B>>> registerHangingSigns(String variant, BiFunction<ExtendedProperties, WoodType, B> factory)
    {
        return Helpers.mapOf(VanillaWood.class, wood ->
                Helpers.mapOf(Metal.class, Metal::allParts, metal -> register(
                        "wood/planks/" + variant + "/" + metal.getSerializedName() + "/" + wood.getSerializedName(),
                        () -> factory.apply(ExtendedProperties.of(wood.woodColor()).sound(SoundType.WOOD).noCollission().strength(1F).flammableLikePlanks().blockEntity(TFCBlockEntities.HANGING_SIGN).ticks(SignBlockEntity::tick), wood.getVanillaWoodType()),
                        (Function<B, BlockItem>) null)
                )
        );
    }

    //public static final Map<SimpleAFCFluid, Id<LiquidBlock>> SIMPLE_FLUIDS = Helpers.mapOf(SimpleAFCFluid.class, fluid ->
    //        registerFluidNoItem("fluid/" + fluid.getId(), () -> new LiquidBlock(AFCFluids.SIMPLE_AFC_FLUIDS.get(fluid).getSource(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).noLootTable()))
    //);

    private static <T extends Block> Id<T> registerFluidNoItem(String name, Supplier<T> blockSupplier)
    {
        return registerFluid(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> Id<T> registerFluid(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        return new Id<>(RegistrationHelpers.registerBlock(ModCompatBlocks.FLUID_BLOCKS, ModCompatItems.ITEMS, name, blockSupplier, blockItemFactory));
    }

    public record Id<T extends Block>(DeferredHolder<Block, T> holder) implements RegistryHolder<Block, T>, ItemLike
    {
        @Override
        public Item asItem()
        {
            return get().asItem();
        }
    }
}





