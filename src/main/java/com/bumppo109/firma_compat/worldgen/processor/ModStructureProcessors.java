package com.bumppo109.firma_compat.worldgen.processor;

import com.bumppo109.firma_compat.FirmaCompat;
import com.bumppo109.firma_compat.worldgen.processor.rock.TFCRockProcessor;
import com.bumppo109.firma_compat.worldgen.processor.soil.TFCSoilProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class ModStructureProcessors
{
    public static final DeferredRegister<StructureProcessorType<?>> PROCESSORS =
            DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, FirmaCompat.MODID);

    public static final Supplier<StructureProcessorType<TFCSoilProcessor>> TFC_SOIL =
            PROCESSORS.register("tfc_soil", () -> () -> TFCSoilProcessor.CODEC);

    public static final Supplier<StructureProcessorType<TFCRockProcessor>> TFC_ROCK =
            PROCESSORS.register(
                    "tfc_rock",
                    () -> () -> TFCRockProcessor.CODEC
            );

    public static void register(IEventBus bus)
    {
        PROCESSORS.register(bus);
    }

    private ModStructureProcessors()
    {
    }
}