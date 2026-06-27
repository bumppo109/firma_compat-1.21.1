package com.bumppo109.firma_compat.util;

import com.bumppo109.firma_compat.worldgen.processor.rock.TFCRockProcessor;
import com.bumppo109.firma_compat.worldgen.processor.soil.TFCSoilProcessor;
import com.bumppo109.firma_compat.worldgen.processor.soil.TFCSoilProcessor.TagReplacement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.dries007.tfc.common.blocks.rock.Rock;
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


    public static final Codec<TagReplacement> TAG_REPLACEMENT_CODEC =
            RecordCodecBuilder.create(instance ->
                    instance.group(

                            net.minecraft.resources.ResourceLocation.CODEC
                                    .fieldOf("tag")
                                    .forGetter(
                                            TagReplacement::getTagLocation
                                    ),

                            SOIL_BLOCK_TYPE
                                    .fieldOf("soil")
                                    .forGetter(
                                            TagReplacement::getType
                                    )

                    ).apply(
                            instance,
                            TagReplacement::new
                    )
            );


    public static final MapCodec<TFCSoilProcessor> TFC_SOIL =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(

                            Codec.unboundedMap(
                                            BuiltInRegistries.BLOCK.byNameCodec(),
                                            SOIL_BLOCK_TYPE
                                    )
                                    .optionalFieldOf(
                                            "replacements",
                                            Map.of()
                                    )
                                    .forGetter(
                                            TFCSoilProcessor::getReplacements
                                    ),


                            TAG_REPLACEMENT_CODEC
                                    .listOf()
                                    .optionalFieldOf(
                                            "tag_replacements",
                                            List.of()
                                    )
                                    .forGetter(
                                            TFCSoilProcessor::getTags
                                    )

                    ).apply(
                            instance,
                            TFCSoilProcessor::new
                    )
            );

    public static final Codec<Rock.BlockType> ROCK_BLOCK_TYPE =
            Codec.STRING.xmap(
                    Rock.BlockType::valueOf,
                    Rock.BlockType::name
            );


    public static final MapCodec<TFCRockProcessor> TFC_ROCK =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(

                                    Codec.unboundedMap(
                                                    BuiltInRegistries.BLOCK.byNameCodec(),
                                                    ROCK_BLOCK_TYPE
                                            )
                                            .optionalFieldOf(
                                                    "replacements",
                                                    Map.of()
                                            )
                                            .forGetter(
                                                    TFCRockProcessor::getReplacements
                                            )

                            )
                            .apply(
                                    instance,
                                    TFCRockProcessor::new
                            )
            );
}