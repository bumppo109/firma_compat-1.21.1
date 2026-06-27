package com.bumppo109.firma_compat.util;

import com.bumppo109.firma_compat.worldgen.processor.ProcessorReplacement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.List;
import java.util.Map;

public final class TFCCodecs
{
    private TFCCodecs()
    {
    }


    public static final Codec<SoilBlockType> SOIL_BLOCK_TYPE =
            Codec.STRING.comapFlatMap(
                    name ->
                    {
                        try
                        {
                            return DataResult.success(
                                    SoilBlockType.valueOf(name)
                            );
                        }
                        catch (IllegalArgumentException e)
                        {
                            return DataResult.error(
                                    () -> "Unknown SoilBlockType: " + name
                            );
                        }
                    },
                    Enum::name
            );
}