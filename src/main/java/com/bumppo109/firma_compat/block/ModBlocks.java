package com.bumppo109.firma_compat.block;

import com.bumppo109.firma_compat.FirmaCompatibility;
import com.bumppo109.firma_compat.block.entities.ModBlockEntities;
import com.bumppo109.firma_compat.item.ModItems;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.wood.TFCCeilingHangingSignBlock;
import net.dries007.tfc.common.blocks.wood.TFCWallHangingSignBlock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.Metal;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.dries007.tfc.util.registry.RegistryHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, FirmaCompatibility.MODID);

    public static final Map<VanillaWood, Map<VanillaWood.BlockType, Id<Block>>> WOODS = Helpers.mapOf(VanillaWood.class, wood ->
            Helpers.mapOf(VanillaWood.BlockType.class, type ->
                    register(type.nameFor(wood), type.create(wood), type.createBlockItem(wood, new Item.Properties()))
            )
    );
    public static final Map<VanillaWood, Map<Metal, Id<TFCCeilingHangingSignBlock>>> CEILING_HANGING_SIGNS = registerHangingSigns("hanging_sign", TFCCeilingHangingSignBlock::new);
    public static final Map<VanillaWood, Map<Metal, Id<TFCWallHangingSignBlock>>> WALL_HANGING_SIGNS = registerHangingSigns("wall_hanging_sign", TFCWallHangingSignBlock::new);

    private static <B extends SignBlock> Map<VanillaWood, Map<Metal, Id<B>>> registerHangingSigns(String variant, BiFunction<ExtendedProperties, WoodType, B> factory)
    {
        return Helpers.mapOf(VanillaWood.class, wood ->
                Helpers.mapOf(Metal.class, Metal::allParts, metal -> register(
                        "wood/" + variant + "/" + metal.getSerializedName() + "/" + wood.getSerializedName(),
                        () -> factory.apply(ExtendedProperties.of(wood.woodColor()).sound(SoundType.WOOD).noCollission().strength(1F).flammableLikePlanks().blockEntity(ModBlockEntities.HANGING_SIGN).ticks(SignBlockEntity::tick), wood.getVanillaWoodType()),
                        (Function<B, BlockItem>) null)
                )
        );
    }

    private static <T extends Block> Id<T> registerNoItem(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> Id<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties()));
    }

    private static <T extends Block> Id<T> register(String name, Supplier<T> blockSupplier, Item.Properties blockItemProperties)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, blockItemProperties));
    }

    private static <T extends Block> Id<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        return new Id<>(RegistrationHelpers.registerBlock(ModBlocks.BLOCKS, ModItems.ITEMS, name, blockSupplier, blockItemFactory));
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
