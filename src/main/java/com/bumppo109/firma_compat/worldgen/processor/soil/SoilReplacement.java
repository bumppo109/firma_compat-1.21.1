package com.bumppo109.firma_compat.worldgen.processor.soil;


import com.bumppo109.firma_compat.worldgen.processor.ProcessorReplacement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.dries007.tfc.common.blocks.soil.SoilBlockType;



public record SoilReplacement(
        SoilBlockType type
)
        implements ProcessorReplacement
{

    public static final MapCodec<SoilReplacement> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(

                                    Codec.STRING
                                            .xmap(
                                                    SoilBlockType::valueOf,
                                                    Enum::name
                                            )
                                            .fieldOf("replacement")
                                            .forGetter(
                                                    SoilReplacement::type
                                            )

                            )
                            .apply(
                                    instance,
                                    SoilReplacement::new
                            )
            );

}