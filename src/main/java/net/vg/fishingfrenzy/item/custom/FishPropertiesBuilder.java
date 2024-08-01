package net.vg.fishingfrenzy.item.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.predicate.NumberRange;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.List;
import java.util.Optional;

public class FishPropertiesBuilder implements FishProperties {
    private int weight = 25;
    private int quality = 0;
    private NumberRange.DoubleRange yRange = NumberRange.DoubleRange.ANY;
    private int minTime = 0;
    private int maxTime = 24000;
    private boolean weatherDependent = false;
    private boolean raining = false;
    private boolean thundering = false;
    private List<RegistryKey<Biome>> biomes = List.of();
    private int primaryColor = 0xffd476;
    private int secondaryColor = 0xb29452;
    private EntityType<? extends MobEntity> fishEntityType = null;

    private int spawningWeight = 5;
    private Pair<Integer, Integer> groupSizes = Pair.of(2, 3);

    public FishPropertiesBuilder setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public FishPropertiesBuilder setQuality(int quality) {
        this.quality = quality;
        return this;
    }

    public FishPropertiesBuilder setYRange(NumberRange.DoubleRange yRange) {
        this.yRange = yRange;
        return this;
    }

    public FishPropertiesBuilder setMinTime(int minTime) {
        this.minTime = minTime;
        return this;
    }

    public FishPropertiesBuilder setMaxTime(int maxTime) {
        this.maxTime = maxTime;
        return this;
    }

    public FishPropertiesBuilder setWeatherDependent(boolean weatherDependent) {
        this.weatherDependent = weatherDependent;
        return this;
    }

    public FishPropertiesBuilder setRaining(boolean raining) {
        this.raining = raining;
        return this;
    }

    public FishPropertiesBuilder setThundering(boolean thundering) {
        this.thundering = thundering;
        return this;
    }

    public FishPropertiesBuilder setBiomes(List<RegistryKey<Biome>> biomes) {
        this.biomes = biomes;
        return this;
    }

    public FishPropertiesBuilder setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
        return this;
    }

    public FishPropertiesBuilder setSecondaryColor(int secondaryColor) {
        this.secondaryColor = secondaryColor;
        return this;
    }

    public FishPropertiesBuilder setFishEntityType(EntityType<? extends MobEntity> fishEntityType) {
        this.fishEntityType = fishEntityType;
        return this;
    }

    public FishPropertiesBuilder setSpawningWeight(int spawningWeight) {
        this.spawningWeight = spawningWeight;
        return this;
    }

    public FishPropertiesBuilder setGroupSizes(Pair<Integer, Integer> groupSizes) {
        this.groupSizes = groupSizes;
        return this;
    }


    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int getQuality() {
        return quality;
    }

    @Override
    public NumberRange.DoubleRange getYRange() {
        return yRange;
    }

    @Override
    public int getMinTime() {
        return minTime;
    }

    @Override
    public int getMaxTime() {
        return maxTime;
    }

    @Override
    public boolean isWeatherDependent() {
        return weatherDependent;
    }

    @Override
    public boolean isRaining() {
        return raining;
    }

    @Override
    public boolean isThundering() {
        return thundering;
    }

    @Override
    public List<RegistryKey<Biome>> getBiomes() {
        return biomes;
    }

    @Override
    public int getPrimaryColor() {
        return primaryColor;
    }

    @Override
    public int getSecondaryColor() {
        return secondaryColor;
    }

    @Override
    public EntityType<? extends MobEntity> getFishEntityType() {
        return fishEntityType;
    }

    @Override
    public boolean hasFishEntityType() {
        return fishEntityType != null;
    }

    @Override
    public int getSpawningWeight() {
        return spawningWeight;
    }

    @Override
    public Pair<Integer, Integer> getGroupSizes() {
        return groupSizes;
    }
}
