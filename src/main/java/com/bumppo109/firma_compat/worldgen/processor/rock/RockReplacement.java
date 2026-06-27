package com.bumppo109.firma_compat.worldgen.processor.rock;

import com.bumppo109.firma_compat.worldgen.processor.ProcessorReplacement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;


public enum RockReplacement
        implements ProcessorReplacement
{
    RAW,
    HARDENED,
    LOOSE_COBBLE,
    HARDENED_COBBLE,
    GRAVEL,
    BRICKS,
    CRACKED_BRICKS,
    MOSSY_BRICKS,
    SAND,
    SANDSTONE,
    SUSPICIOUS_GRAVEL,
    SUSPICIOUS_SAND;



    public static final MapCodec<RockReplacement> CODEC =
            Codec.STRING
                    .xmap(
                            RockReplacement::valueOf,
                            RockReplacement::name
                    )
                    .fieldOf("replacement");
}