package com.bumppo109.firma_compat.worldgen.processor.rock;

import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.DecorationBlockHolder;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.rock.Rock.BlockType;
import net.dries007.tfc.world.settings.RockSettings;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public final class TFCRockLookup
{
    private TFCRockLookup()
    {
    }


    public static BlockState get(
            RockSettings rock,
            BlockType type)
    {
        return getBlock(
                rock,
                type
        ).defaultBlockState();
    }


    private static Block getBlock(
            RockSettings rock,
            BlockType type)
    {
        return switch(type)
        {
            case RAW ->
                    rock.raw();

            case HARDENED ->
                    rock.hardened();

            case COBBLE ->
                    rock.cobble();

            case GRAVEL ->
                    rock.gravel();

            default ->
                    findRegisteredVariant(
                            rock,
                            type
                    );
        };
    }


    private static Block findRegisteredVariant(
            RockSettings rock,
            BlockType type)
    {
        for (Rock tfcRock : Rock.values())
        {
            Block block =
                    TFCBlocks.ROCK_BLOCKS
                            .get(tfcRock)
                            .get(type)
                            .get();

            if (TFCBlocks.ROCK_BLOCKS
                    .get(tfcRock)
                    .get(BlockType.RAW)
                    .get()
                    == rock.raw())
            {
                return block;
            }
        }


        /*
         * fallback for safety
         */
        return rock.raw();
    }
}