package net.vg.fishingfrenzy.item.custom;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.predicate.NumberRange;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.biome.Biome;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.vg.fishingfrenzy.management.FishManager;
import net.vg.fishingfrenzy.management.FishPreset;
import net.vg.fishingfrenzy.util.StatusEffectEntry;

import java.util.List;
import java.util.Optional;

public class FishRegistry {

    private final Item fish;
    private final Item spawnEgg;
    private final Item bait;

    private final String fish_name;

    private final int weight;
    private final int quality;
    private final NumberRange.DoubleRange yRange;
    private final int minTime;
    private final int maxTime;
    private final boolean isWeatherDependent;
    private final boolean raining;
    private final boolean thundering;
    private final List<RegistryKey<Biome>> biomes;
    private final int primaryColor;
    private final int secondaryColor;
    private final EntityType<? extends MobEntity> fishEntityType;
    private final int spawningWeight;
    private final Pair<Integer, Integer> groupSizes;
    private final FoodComponent foodComponent;

    public FishRegistry(String name, FishProperties properties) {
        this.fish_name = name;



        this.weight = properties.getWeight();
        this.quality = properties.getQuality();
        this.yRange = properties.getYRange();
        this.minTime = properties.getMinTime();
        this.maxTime = properties.getMaxTime();
        this.isWeatherDependent = properties.isWeatherDependent();
        this.raining = properties.isRaining();
        this.thundering = properties.isThundering();
        this.biomes = properties.getBiomes();
        this.primaryColor = properties.getPrimaryColor();
        this.secondaryColor = properties.getSecondaryColor();
        this.fishEntityType = properties.getFishEntityType();
        this.spawningWeight = properties.getSpawningWeight();
        this.groupSizes = properties.getGroupSizes();

        this.foodComponent = createFoodComponent(properties.getFoodAttributes(), properties.isSnack(), properties.getStatusEffects());


        this.fish = createFishItem(new Item.Settings().food(foodComponent), properties);
        this.spawnEgg = createSpawnEgg(new Item.Settings());
        this.bait = createBait(new Item.Settings(), new BaitPropertiesBuilder().setTargetedFish(this.fish));

        FishManager.addFishRegistry(this);
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
    public int getPrimaryColor() {
        return primaryColor;
    }
    public int getSecondaryColor() {
        return secondaryColor;
    }
    public EntityType<? extends MobEntity> getFishEntityType() {
        return fishEntityType;
    }
    public boolean hasFishEntityType() {
        return fishEntityType != null;
    }
    public int getSpawningWeight() {
        return spawningWeight;
    }
    public Pair<Integer, Integer> getGroupSizes() {
        return groupSizes;
    }
    public Item getFish() {
        return fish;
    }
    public Item getSpawnEgg() {
        return spawnEgg;
    }
    public Item getBait() {
        return bait;
    }
    public String getFishName() {
        return fish_name;
    }

    private Item createFishItem(Item.Settings settings, FishProperties properties) {
        FishItem fishItem = new FishItem(settings, properties);
        Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, getFishName()), fishItem);
        return fishItem;
    }

    private Item createSpawnEgg(Item.Settings settings) {
        if (this.hasFishEntityType()) {
            SpawnEggItem spawnEgg = new SpawnEggItem(this.getFishEntityType(), this.getPrimaryColor(), this.getSecondaryColor(), settings);
            Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, getFishName() + "_spawn_egg"), spawnEgg);
            return spawnEgg;
        }
        return null;
    }

    private Item createBait(Item.Settings settings, BaitProperties properties) {
        TargetBaitItem bait = new TargetBaitItem(this.getPrimaryColor(), this.getSecondaryColor(), settings, properties);
        Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, getFishName() + "_bait"), bait);
        return bait;
    }

    private FoodComponent createFoodComponent(Pair<Integer, Float> foodAttributes, boolean snack, List<StatusEffectEntry> statusEffects) {
        FoodComponent.Builder builder = new FoodComponent.Builder().nutrition(foodAttributes.getFirst()).saturationModifier(foodAttributes.getSecond());
        if (snack) {
            builder.snack();
        }
        for (StatusEffectEntry entry : statusEffects) {
            builder.statusEffect(new StatusEffectInstance(entry.getEffect(), entry.getDuration(), entry.getAmplifier()), entry.getChance());
        }
        return builder.build();
    }

    public void registerItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(this.fish, Models.GENERATED);
        if (this.spawnEgg != null) {
            Model spawnEggModel = new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty());
            itemModelGenerator.register(this.spawnEgg, spawnEggModel);
        }
        if (this.bait != null) {
            Model baitModel = new Model(Optional.of(Identifier.of(FishingFrenzy.MOD_ID, "item/template_target_bait")), Optional.empty());
            itemModelGenerator.register(this.bait, baitModel);
        }
    }

    public void registerItemColorProviders() {
        if (this.bait != null) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorHelper.Argb.fullAlpha(((TargetBaitItem) stack.getItem()).getColor(tintIndex)), this.bait);
        }
    }


}
