package com.bumppo109.firma_compat.worldgen.processor.soil;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public final class BlockStateUtil
{
    private BlockStateUtil()
    {
    }

    public static BlockState copyCommonProperties(
            BlockState from,
            BlockState to)
    {
        for (Property<?> property : from.getProperties())
        {
            to = copy(property, from, to);
        }

        return to;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> BlockState copy(
            Property<?> property,
            BlockState from,
            BlockState to)
    {
        Property<T> prop = (Property<T>) property;

        if (to.hasProperty(prop))
        {
            to = to.setValue(prop, from.getValue(prop));
        }

        return to;
    }
}