package net.vg.fishingfrenzy.item.custom;

import net.minecraft.item.Item;
import net.minecraft.predicate.NumberRange;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.List;

public class FishItem extends Item {

    private final int weight;
    private final int quality;
    private final NumberRange.DoubleRange yRange;
    private final int minTime;
    private final int maxTime;
    private final boolean isWeatherDependent;
    private final boolean raining;
    private final boolean thundering;
    private final List<RegistryKey<Biome>> biomes;

    public FishItem(Settings settings, FishProperties properties) {
        super(settings);
        this.weight = properties.getWeight();
        this.quality = properties.getQuality();
        this.yRange = properties.getYRange();
        this.minTime = properties.getMinTime();
        this.maxTime = properties.getMaxTime();
        this.isWeatherDependent = properties.isWeatherDependent();
        this.raining = properties.isRaining();
        this.thundering = properties.isThundering();
        this.biomes = properties.getBiomes();
    }

    public int getWeight() {
        return weight;
    }

    public int getQuality() {
        return quality;
    }

    public NumberRange.DoubleRange getYRange() {
        return yRange;
    }

    public int getMinTime() {
        return minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public boolean isWeatherDependent() {
        return isWeatherDependent;
    }

    public boolean isRaining() {
        return raining;
    }

    public boolean isThundering() {
        return thundering;
    }

    public List<RegistryKey<Biome>> getBiomes() {
        return biomes;
    }
}
