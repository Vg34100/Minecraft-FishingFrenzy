package net.vg.fishingfrenzy.item.fish;

import com.mojang.datafixers.util.Pair;
import dev.architectury.core.item.ArchitecturyMobBucketItem;
import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.platform.Platform;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.architectury.utils.Env;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.Fluids;
import net.vg.fishingfrenzy.Constants;
import net.vg.fishingfrenzy.entity.mob.CustomBreedableSchoolingFishEntity;
import net.vg.fishingfrenzy.item.bait.BaitPropertiesBuilder;
import net.vg.fishingfrenzy.item.bait.targetbait.TargetBaitItem;
import net.vg.fishingfrenzy.registry.DynamicFishEntityGenerator;
import net.vg.fishingfrenzy.registry.DynamicFishSystem;
import net.vg.fishingfrenzy.registry.ModItems;
import net.vg.fishingfrenzy.util.StatusEffectEntry;

import java.awt.*;
import java.util.List;

public class FishRegistry {
    private final String fish_name;

    // Item registrations
    private final RegistrySupplier<Item> fish;
    private final RegistrySupplier<Item> cookedFish;
    private final RegistrySupplier<Item> bait;
    private final RegistrySupplier<Item> spawnEgg;
    private RegistrySupplier<Item> bucketItem;

    // Entity properties
    private final RegistrySupplier<EntityType<CustomBreedableSchoolingFishEntity>> fishEntityType;
    private final ModelLayerLocation modelLayer;
    private final Class<? extends EntityModel<CustomBreedableSchoolingFishEntity>> modelClass;

    // Additional properties
    private final Item breedingItem;
    private final List<Item> additionalDrops;
    private final boolean shouldAttack;

    private final List<ResourceKey<Biome>> biomes;
    private final MinMaxBounds.Doubles yRange;

    private final int weight;
    private final int quality;

    private final int minTime;
    private final int maxTime;

    private final boolean isWeatherDependent;
    private final boolean raining;
    private final boolean thundering;

    private final int spawningWeight;
    private final Pair<Integer, Integer> groupSizes;

    public FishRegistry(String name, FishProperties properties, Class<? extends EntityModel<CustomBreedableSchoolingFishEntity>> modelClass) {
        this.fish_name = name;
        this.modelClass = modelClass;

        this.breedingItem = properties.getBreedingItem();
        this.additionalDrops = properties.getAdditionalDrops();
        this.shouldAttack = properties.shouldAttack();

        this.biomes = properties.getBiomes();
        this.yRange = properties.getYRange();

        this.weight = properties.getWeight();
        this.quality = properties.getQuality();

        this.minTime = properties.getMinTime();
        this.maxTime = properties.getMaxTime();

        this.isWeatherDependent = properties.isWeatherDependent();
        this.raining = properties.isRaining();
        this.thundering = properties.isThundering();

        this.spawningWeight = properties.getSpawningWeight();
        this.groupSizes = properties.getGroupSizes();

        FoodProperties foodProperties = createFoodComponent(properties.getFoodAttributes(), properties.isSnack(), properties.getStatusEffects());
        FoodProperties cookedFoodProperties = createFoodComponent(properties.getCookedFoodAttributes(), properties.isSnack(), properties.getCookedStatusEffects());

        this.fishEntityType = DynamicFishEntityGenerator.generateFishEntity(this);

        // Register items
        this.fish = ModItems.ITEMS.register(name, () -> new FishItem(new Item.Properties().food(foodProperties), properties));
        this.cookedFish  = ModItems.ITEMS.register("cooked_" + name, () -> new Item(new Item.Properties().food(cookedFoodProperties)));

        this.bait = ModItems.ITEMS.register(name + "_bait", () -> new TargetBaitItem(properties.getPrimaryColor(), properties.getSecondaryColor(), new Item.Properties(), new BaitPropertiesBuilder().setTargetedFish(fish.get())));
        // Register Bait Item Color
        if (Platform.getEnvironment() == Env.CLIENT) {
            ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> {
                if (stack.getItem() instanceof TargetBaitItem targetBaitItem) {
                    return targetBaitItem.getColor(tintIndex) | 0xFF000000; // Full alpha
                }
                return 0xFFFFFFFF; // Default to white if not a TargetBaitItem
            }, this.bait);
        }

//        this.bucketItem = null;
//        this.spawnEgg = null;
        this.spawnEgg = ModItems.ITEMS.register(name + "_spawn_egg", () -> new ArchitecturySpawnEggItem(fishEntityType, properties.getPrimaryColor(), properties.getSecondaryColor(), new Item.Properties()));



        if (modelClass != null) {
            this.modelLayer = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), "main");
//            DynamicFishSystem.registerFish(this);
        } else {
            this.modelLayer = null;
            Constants.LOGGER.info("ModelClass found null for fish: {}", name);
        }

//        FishManager.addFishRegistry(this);
    }


    public void postInit() {
        this.bucketItem = ModItems.ITEMS.register(fish_name + "_bucket", () ->
                new ArchitecturyMobBucketItem(
                        fishEntityType,
                        () -> Fluids.WATER,
                        () -> SoundEvents.BUCKET_EMPTY_FISH,
                        new Item.Properties()
                                .stacksTo(1)
                                .component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)
                )
        );
        FishManager.addFishRegistry(this);
    }


    // Handles ModFoodComponents
    private FoodProperties createFoodComponent(Pair<Integer, Float> foodAttributes, boolean snack, List<StatusEffectEntry> statusEffects) {
        FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(foodAttributes.getFirst()).saturationModifier(foodAttributes.getSecond());
        if (snack) {
            builder.fast();
        }
        for (StatusEffectEntry entry : statusEffects) {
            builder.effect(new MobEffectInstance(entry.effect(), entry.duration(), entry.amplifier()), entry.chance());
        }
        return builder.build();
    }
    public String getFishName() {
        return fish_name;
    }
    public RegistrySupplier<Item> getFishRegistry() {
        return fish;
    }
    public RegistrySupplier<Item> getCookedFishRegistry() {
        return cookedFish;
    }
    public RegistrySupplier<Item> getTargetBaitRegistry() {
        return bait;
    }

    public RegistrySupplier<EntityType<CustomBreedableSchoolingFishEntity>> getFishEntityType() {
        return fishEntityType;
    }
    public EntityType<CustomBreedableSchoolingFishEntity> getFishEntityTypeDirectly() {
        return fishEntityType.get();
    }


    public ModelLayerLocation getModelLayer() {
        return modelLayer;
    }

    public Class<? extends EntityModel<CustomBreedableSchoolingFishEntity>> getModelClass() {
        return modelClass;
    }
    public boolean hasFishEntityType() {
        return fishEntityType != null;
    }



    public Item getBreedingItem() {
        return breedingItem;
    }
    public List<Item> getAdditionalDrops() {
        return additionalDrops;
    }
    public boolean shouldAttack() {
        return shouldAttack;
    }
    public List<ResourceKey<Biome>> getBiomes() {
        return biomes;
    }
    public MinMaxBounds.Doubles getYRange() {
        return yRange;
    }
    public int getWeight() {
        return weight;
    }
    public int getQuality() {
        return quality;
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
    public int getSpawningWeight() {
        return spawningWeight;
    }
    public Pair<Integer, Integer> getGroupSizes() {
        return groupSizes;
    }
}
