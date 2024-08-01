package net.vg.fishingfrenzy.item.custom;

import net.minecraft.predicate.NumberRange;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.List;

public interface FishProperties {

    default int getWeight() {
        return 25;
    }

    default int getQuality() {
        return 0;
    }

    default NumberRange.DoubleRange getYRange() {
        return NumberRange.DoubleRange.ANY;
    }

    default int getMinTime() {
        return 0;
    }

    default int getMaxTime() {
        return 24000;
    }

    default boolean isWeatherDependent() {
        return false;
    }

    default boolean isRaining() {
        return false;
    }

    default boolean isThundering() {
        return false;
    }

    default List<RegistryKey<Biome>> getBiomes() {
        return List.of();
    }

    default int getPrimaryColor() {
        return 0xffd476;
    }

    default int getSecondaryColor() {
        return 0xb29452;
    }
}
