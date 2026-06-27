package com.bumppo109.firma_compat.worldgen.processor.soil;

import com.bumppo109.firma_compat.FirmaCompat;
import com.bumppo109.firma_compat.util.TFCCodecs;
import com.bumppo109.firma_compat.worldgen.processor.ModStructureProcessors;
import com.mojang.serialization.MapCodec;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;


public class TFCSoilProcessor extends StructureProcessor
{
    public static final MapCodec<TFCSoilProcessor> CODEC =
            TFCCodecs.TFC_SOIL;


    private final Map<Block, SoilBlockType> replacements;
    private final List<TagReplacement> tags;


    public TFCSoilProcessor(
            Map<Block, SoilBlockType> replacements,
            List<TagReplacement> tags)
    {
        this.replacements = Map.copyOf(replacements);
        this.tags = List.copyOf(tags);
    }


    public Map<Block, SoilBlockType> getReplacements()
    {
        return replacements;
    }


    public List<TagReplacement> getTags()
    {
        return tags;
    }


    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(
            LevelReader level,
            BlockPos structureOffset,
            BlockPos piecePos,
            StructureTemplate.StructureBlockInfo original,
            StructureTemplate.StructureBlockInfo transformed,
            StructurePlaceSettings settings)
    {
        Block block =
                transformed.state().getBlock();

        FirmaCompat.LOGGER.info(
                "TFC processor saw {}",
                BuiltInRegistries.BLOCK.getKey(transformed.state().getBlock())
        );

        SoilBlockType soil =
                replacements.get(block);


        if (soil == null)
        {
            for (TagReplacement replacement : tags)
            {
                if (block.defaultBlockState()
                        .is(replacement.getTag()))
                {
                    soil = replacement.getType();
                    break;
                }
            }
        }


        if (soil == null)
        {
            return transformed;
        }


        SoilBlockType.Variant variant =
                TFCSoilResolver.determineVariant(
                        level,
                        transformed.pos()
                );


        Decoration decoration =
                Decoration.of(block);


        BlockState replacement =
                TFCBlockLookup.get(
                        soil,
                        variant,
                        decoration
                );


        replacement =
                BlockStateUtil.copyCommonProperties(
                        transformed.state(),
                        replacement
                );


        return new StructureTemplate.StructureBlockInfo(
                transformed.pos(),
                replacement,
                transformed.nbt()
        );
    }


    @Override
    protected StructureProcessorType<?> getType()
    {
        return ModStructureProcessors.TFC_SOIL.get();
    }


    public static class TagReplacement
    {
        private final TagKey<Block> tag;
        private final SoilBlockType type;


        public TagReplacement(
                ResourceLocation tag,
                SoilBlockType type)
        {
            this.tag =
                    TagKey.create(
                            BuiltInRegistries.BLOCK.key(),
                            tag
                    );

            this.type = type;
        }


        public ResourceLocation getTagLocation()
        {
            return tag.location();
        }


        public TagKey<Block> getTag()
        {
            return tag;
        }


        public SoilBlockType getType()
        {
            return type;
        }
    }
}