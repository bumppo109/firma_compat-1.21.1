package com.bumppo109.firma_compat.block.entities;

import com.bumppo109.firma_compat.FirmaCompatibility;
import com.bumppo109.firma_compat.block.ModBlocks;
import com.bumppo109.firma_compat.block.VanillaWood;
import net.dries007.tfc.common.blockentities.*;
import net.dries007.tfc.common.blockentities.rotation.*;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.dries007.tfc.util.registry.RegistryHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FirmaCompatibility.MODID);

    public static final Id<BarrelBlockEntity> BARREL = register("barrel", BarrelBlockEntity::new, woodBlocks(VanillaWood.BlockType.BARREL));
    public static final Id<LoomBlockEntity> LOOM = register("loom", LoomBlockEntity::new, woodBlocks(VanillaWood.BlockType.LOOM));
    public static final Id<SluiceBlockEntity> SLUICE = register("sluice", SluiceBlockEntity::new, woodBlocks(VanillaWood.BlockType.SLUICE));
    public static final Id<ToolRackBlockEntity> TOOL_RACK = register("tool_rack", ToolRackBlockEntity::new, woodBlocks(VanillaWood.BlockType.TOOL_RACK));
    public static final Id<BookshelfBlockEntity> BOOKSHELF = register("bookshelf", BookshelfBlockEntity::new, woodBlocks(VanillaWood.BlockType.BOOKSHELF));
    //public static final Id<TFCSignBlockEntity> SIGN = register("sign", TFCSignBlockEntity::new, Stream.concat(
    //        woodBlocks(VanillaWood.BlockType.SIGN),
    //        woodBlocks(VanillaWood.BlockType.WALL_SIGN)
    //));
    public static final Id<TFCHangingSignBlockEntity> HANGING_SIGN = register("hanging_sign", TFCHangingSignBlockEntity::new, Stream.of(
            ModBlocks.CEILING_HANGING_SIGNS,
            ModBlocks.WALL_HANGING_SIGNS
    ).flatMap(woodMap -> woodMap.values().stream().flatMap(metalMap -> metalMap.values().stream())));
    public static final Id<LecternBlockEntity> LECTERN = register("lectern", TFCLecternBlockEntity::new, woodBlocks(VanillaWood.BlockType.LECTERN));
    //public static final Id<ShelfBlockEntity> SHELF = register("shelf", ShelfBlockEntity::new, Stream.concat(Stream.of(TFCBlocks.FIRE_BRICK_SHELF), woodBlocks(VanillaWood.BlockType.SHELF)));

    public static final Id<AxleBlockEntity> AXLE = register("axle", AxleBlockEntity::new, woodBlocks(VanillaWood.BlockType.AXLE));
    public static final Id<BladedAxleBlockEntity> BLADED_AXLE = register("bladed_axle", BladedAxleBlockEntity::new, woodBlocks(VanillaWood.BlockType.BLADED_AXLE));
    public static final Id<AxleBlockEntity> CLUTCH = register("clutch", ClutchBlockEntity::new, woodBlocks(VanillaWood.BlockType.CLUTCH));
    public static final Id<EncasedAxleBlockEntity> ENCASED_AXLE = register("encased_axle", EncasedAxleBlockEntity::new, woodBlocks(VanillaWood.BlockType.ENCASED_AXLE));
    public static final Id<GearBoxBlockEntity> GEAR_BOX = register("gear_box", GearBoxBlockEntity::new, woodBlocks(VanillaWood.BlockType.GEAR_BOX));
    public static final Id<WindmillBlockEntity> WINDMILL = register("windmill", WindmillBlockEntity::new, woodBlocks(VanillaWood.BlockType.WINDMILL));
    public static final Id<WaterWheelBlockEntity> WATER_WHEEL = register("water_wheel", WaterWheelBlockEntity::new, woodBlocks(VanillaWood.BlockType.WATER_WHEEL));
    //public static final Id<CrankshaftBlockEntity> CRANKSHAFT = register("crankshaft", CrankshaftBlockEntity::new, TFCBlocks.CRANKSHAFT);

    private static <T extends BlockEntity> Id<T> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block> block)
    {
        return new Id<>(RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block));
    }

    private static <T extends BlockEntity> Id<T> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Stream<? extends Supplier<? extends Block>> blocks)
    {
        return new Id<>(RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, blocks));
    }

    private static Stream<? extends Supplier<? extends Block>> woodBlocks(VanillaWood.BlockType type)
    {
        return ModBlocks.WOODS.values().stream().map(map -> map.get(type));
    }

    public record Id<T extends BlockEntity>(DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> holder)
            implements RegistryHolder<BlockEntityType<?>, BlockEntityType<T>> {}
    
}
