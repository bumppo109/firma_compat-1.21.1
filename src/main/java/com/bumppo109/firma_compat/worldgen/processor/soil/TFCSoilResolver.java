package com.bumppo109.firma_compat.worldgen.processor.soil;

import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.world.chunkdata.ChunkData;
import net.dries007.tfc.world.noise.Noise2D;
import net.dries007.tfc.world.noise.OpenSimplex2D;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;


public final class TFCSoilResolver
{
    private static final Noise2D PATCH_NOISE =
            new OpenSimplex2D(18273952837592L)
                    .octaves(2)
                    .spread(0.04D);


    private TFCSoilResolver()
    {
    }


    public static SoilBlockType.Variant determineVariant(
            LevelReader level,
            BlockPos pos)
    {
        ChunkData data =
                ChunkData.get(level, pos);


        float temperature =
                Helpers.adjustAverageTemperatureByElevation(
                        pos.getY(),
                        data.getAverageSeaLevelTemp(pos),
                        level.getSeaLevel()
                );


        float groundwater =
                data.getBaseGroundwater(pos)
                        + data.getAverageRainfall(pos);


        float variance =
                data.getRainVariance(pos);


        if (groundwater > 25f &&
                Math.abs(variance) > 0.5f)
        {
            return SoilBlockType.Variant.FLUVISOL;
        }


        if (temperature < 16f)
        {
            return SoilBlockType.Variant.ENTISOL;
        }


        if (temperature > 16.7f)
        {
            return SoilBlockType.Variant.OXISOL;
        }


        return PATCH_NOISE.noise(
                pos.getX(),
                pos.getZ()
        ) > 0
                ? SoilBlockType.Variant.OXISOL
                : SoilBlockType.Variant.ENTISOL;
    }
}