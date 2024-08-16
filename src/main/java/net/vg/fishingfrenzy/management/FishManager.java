package net.vg.fishingfrenzy.management;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryWrapper;
import net.vg.fishingfrenzy.datagen.ModItemTagProvider;
import net.vg.fishingfrenzy.datagen.ModRecipeProvider;
import net.vg.fishingfrenzy.entity.client.*;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.item.custom.FishPropertiesBuilder;
import net.vg.fishingfrenzy.item.custom.FishRegistry;
import net.vg.fishingfrenzy.util.BiomeCategories;
import net.vg.fishingfrenzy.util.EnvironmentUtil;
import net.vg.fishingfrenzy.util.HeightRanges;

import java.util.*;

public class FishManager {

    public enum ItemType {
        FISH,
        COOKED_FISH,
        BAIT,
        SPAWN_EGG,
        BUCKET
    }

    public static final List<FishRegistry> FISH_REGISTRIES = new ArrayList<>();
    public static final Map<String, FishRegistry> FISH_REGISTRY_MAP = new HashMap<>();

    public static final EnumMap<ItemType, List<Item>> ALL_ITEMS = new EnumMap<>(ItemType.class);
    static {
        ALL_ITEMS.put(ItemType.FISH, new ArrayList<>());
        ALL_ITEMS.put(ItemType.COOKED_FISH, new ArrayList<>());
        ALL_ITEMS.put(ItemType.BAIT, new ArrayList<>());
        ALL_ITEMS.put(ItemType.SPAWN_EGG, new ArrayList<>());
        ALL_ITEMS.put(ItemType.BUCKET, new ArrayList<>());
    }

    public static void registerAllFish() {

        FishRegistry ANCHOVY = new FishRegistry(
                "anchovy",
                FishPreset.applyPresets(
                                new FishPropertiesBuilder(),
                                FishPreset.RARITY_COMMON_SMALL,
                                FishPreset.BIOME_WARM_CLIMATE_FISH,
                                FishPreset.FOOD_LIGHT
                        )
                        .setPrimaryColor(0x28ccf0)
                        .setSecondaryColor(0x93ffb6),
                EnvironmentUtil.isClient() ? AnchovyModel.class : null
        );

        FishRegistry ALBACORE = new FishRegistry(
                "albacore",
                FishPreset.applyPresets(
                        new FishPropertiesBuilder(),
                                FishPreset.RARITY_COMMON,
                                FishPreset.BIOME_WARM_CLIMATE_FISH,
                                FishPreset.FOOD_BALANCED
                )
                        .setPrimaryColor(0x4391ba)
                        .setSecondaryColor(0x296fa2)
                        .setBreedingItem(ANCHOVY.getFish()),
                EnvironmentUtil.isClient() ? AlbacoreModel.class : null
        );

        FishRegistry BLUE_DISCUS = new FishRegistry(
                "blue_discus",
                FishPreset.applyPresets(
                                new FishPropertiesBuilder(),
                                FishPreset.RARITY_COMMON,
                                FishPreset.FOOD_BALANCED
                        )
                        .setPrimaryColor(0x8bf4c5)
                        .setSecondaryColor(0x12e474)
                        .setBiomes(BiomeCategories.JUNGLES.getBiomes()),
                EnvironmentUtil.isClient() ? BlueDiscusModel.class : null
        );

        FishRegistry BREAM = new FishRegistry(
                "bream",
                FishPreset.applyPresets(
                                new FishPropertiesBuilder(),
                                FishPreset.RARITY_COMMON,
                                FishPreset.TIME_NIGHT,
                                FishPreset.FOOD_BALANCED,
                                FishPreset.BIOME_FRESHWATER_FISH
                        )
                        .setPrimaryColor(0x72b6cd)
                        .setSecondaryColor(0x7f80eb)
                        .setYRange(HeightRanges.SEA_LEVEL.getRange()),
                EnvironmentUtil.isClient() ? BreamModel.class : null
        );

        FishRegistry BULLHEAD = new FishRegistry(
                "bullhead",
                FishPreset.applyPresets(
                                new FishPropertiesBuilder(),
                                FishPreset.RARITY_COMMON,
                                FishPreset.BIOME_FRESHWATER_FISH,
                                FishPreset.FOOD_BALANCED
                        )
                        .setPrimaryColor(0x733d1f)
                        .setSecondaryColor(0x8b4828)
                        .setYRange(HeightRanges.SEA_LEVEL.getRange()),
                EnvironmentUtil.isClient() ? BullheadModel.class : null
        );


        FishRegistry CATFISH = new FishRegistry(
                "catfish",
                FishPreset.applyPresets(
                                new FishPropertiesBuilder(),
                                FishPreset.RARITY_LEGENDARY,
                                FishPreset.WEATHER_RAIN,
                                FishPreset.FOOD_HEARTY,
                                FishPreset.BIOME_FRESHWATER_FISH
                        )
                        .setPrimaryColor(0x54473c)
                        .setSecondaryColor(0x494a4b)
                        .setShouldAttack(true)
                        .setAdditionalDrops(List.of(Items.STRING)),
                EnvironmentUtil.isClient() ? CatfishModel.class : null
        );

    }

    public static void addFishRegistry(FishRegistry fishRegistry) {
        FISH_REGISTRIES.add(fishRegistry);
        FISH_REGISTRY_MAP.put(fishRegistry.getFishName(), fishRegistry);

        Item fish = fishRegistry.getFish();
        Item cookedFish = fishRegistry.getCookedFish();
        Item bait = fishRegistry.getBait();
        Item spawnEgg = fishRegistry.getSpawnEgg();
        Item bucketItem = fishRegistry.getBucketItem();

        if (fish != null) {
            ALL_ITEMS.get(ItemType.FISH).add(fish);
        }
        if (bait != null) {
            ALL_ITEMS.get(ItemType.BAIT).add(bait);
        }
        if (spawnEgg != null) {
            ALL_ITEMS.get(ItemType.SPAWN_EGG).add(spawnEgg);
        }
        if (cookedFish != null) {
            ALL_ITEMS.get(ItemType.COOKED_FISH).add(cookedFish);
        }
        if (bucketItem != null) {
            ALL_ITEMS.get(ItemType.BUCKET).add(bucketItem);
        }
    }


    public static List<Item> getItemsByType(ItemType type) {
        return ALL_ITEMS.get(type);
    }

    // Handles ModLootTableModifiers
    public static void modifyFishingLootTable(LootTable.Builder tableBuilder, RegistryWrapper. WrapperLookup registries) {
        for (FishRegistry registry : FISH_REGISTRIES) {
            registry.modifyFishingLootTable(tableBuilder, registries);
        }
    }

    // Handles ModEntitySpawns
    public static void registerAllFishSpawns() {
        for (FishRegistry fishRegistry : FISH_REGISTRIES) {
            fishRegistry.registerEntitySpawn();
        }
    }

    // Handles Data Generation: ModModelProvider
    public static void registerItemModels(ItemModelGenerator itemModelGenerator) {
        for (FishRegistry registry : FISH_REGISTRIES) {
            registry.registerItemModels(itemModelGenerator);
        }
    }

    // Handle Data Generation: ModLangProvider
    public static void registerTranslations(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        for (FishRegistry registry : FISH_REGISTRIES) {
            registry.registerTranslations(translationBuilder);
        }
    }

    // Handle Data Generation: ModItemTagProvider
    public static void registerItemTags(ModItemTagProvider tagProvider) {
        for (FishRegistry registry : FISH_REGISTRIES) {
            registry.registerItemTags(tagProvider);
        }
    }

    // Handle Data Generation: ModRecipeProvider
    public static void registerRecipes(ModRecipeProvider recipeProvider, RecipeExporter exporter) {
        for (FishRegistry registry : FISH_REGISTRIES) {
            registry.registerRecipes(recipeProvider, exporter);
        }
    }

    // Handles FishingFrenzyClient -> Targeted Bait Alpha
    public static void registerItemColorProviders() {
        for (FishRegistry registry : FISH_REGISTRIES) {
            registry.registerItemColorProviders();
        }
    }



}
