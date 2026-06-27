package com.bumppo109.firma_compat.worldgen.processor;


import com.bumppo109.firma_compat.worldgen.processor.rock.RockReplacement;
import com.bumppo109.firma_compat.worldgen.processor.soil.SoilReplacement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;


public final class ProcessorReplacementCodec
{
    private ProcessorReplacementCodec()
    {
    }


    public static final Codec<ProcessorReplacement> CODEC =
            Codec.STRING.dispatch(
                    "type",
                    ProcessorReplacementCodec::getType,
                    ProcessorReplacementCodec::getCodec
            );



    private static String getType(
            ProcessorReplacement replacement)
    {
        return switch (replacement)
        {
            case RockReplacement ignored ->
                    "rock";

            case SoilReplacement ignored ->
                    "soil";
        };
    }



    private static MapCodec<? extends ProcessorReplacement> getCodec(
            String type)
    {
        return switch(type)
        {
            case "rock" ->
                    RockReplacement.CODEC;


            case "soil" ->
                    SoilReplacement.CODEC;


            default ->
                    throw new IllegalArgumentException(
                            "Unknown processor replacement: " + type
                    );
        };
    }
}