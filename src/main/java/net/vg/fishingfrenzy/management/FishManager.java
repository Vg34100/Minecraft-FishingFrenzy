package net.vg.fishingfrenzy.management;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryWrapper;
import net.vg.fishingfrenzy.datagen.ModItemTagProvider;
import net.vg.fishingfrenzy.entity.ModEntities;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.item.custom.BaitPropertiesBuilder;
import net.vg.fishingfrenzy.item.custom.FishPropertiesBuilder;
import net.vg.fishingfrenzy.item.custom.FishRegistry;

import java.util.*;

public class FishManager {

    public enum ItemType {
        FISH,
        BAIT,
        SPAWN_EGG;
    }

    public static final List<FishRegistry> FISH_REGISTRIES = new ArrayList<>();
    public static final Map<String, FishRegistry> FISH_REGISTRY_MAP = new HashMap<>();

    public static final EnumMap<ItemType, List<Item>> ALL_ITEMS = new EnumMap<>(ItemType.class);
    static {
        ALL_ITEMS.put(ItemType.FISH, new ArrayList<>());
        ALL_ITEMS.put(ItemType.BAIT, new ArrayList<>());
        ALL_ITEMS.put(ItemType.SPAWN_EGG, new ArrayList<>());
    }


    public static FishRegistry FISH_1;

    public static void registerAllFish() {
        FISH_1 = new FishRegistry(
                "fish_1",
                new FishPropertiesBuilder()
                        .setPrimaryColor(0x036ffc)
                        .setSecondaryColor(0xb0bdcf));

        FishRegistry FISH_2 = new FishRegistry(
                "fish_2",
                FishPreset.applyPresets(
                        new FishPropertiesBuilder(),
                                FishPreset.TEST,
                                FishPreset.RAIN,
                                FishPreset.LIGHT)
                        .setFishEntityType(ModEntities.BONEFISH)
                        .setPrimaryColor(0x036ffc)
                        .setSecondaryColor(0xb0bdcf)
                        .setWeight(3000)  // Overriding the TEST preset
                        .addStatusEffect(StatusEffects.DARKNESS, 150, 0, 0.1f)
                        .addStatusEffect(StatusEffects.ABSORPTION, 150, 0, 0.1f)
        );
    }

    public static void addFishRegistry(FishRegistry fishRegistry) {
        FISH_REGISTRIES.add(fishRegistry);
        FISH_REGISTRY_MAP.put(fishRegistry.getFishName(), fishRegistry);

        Item fish = fishRegistry.getFish();
        Item bait = fishRegistry.getBait();
        Item spawnEgg = fishRegistry.getSpawnEgg();

        if (fish != null) {
            ALL_ITEMS.get(ItemType.FISH).add(fish);
        }
        if (bait != null) {
            ALL_ITEMS.get(ItemType.BAIT).add(bait);
        }
        if (spawnEgg != null) {
            ALL_ITEMS.get(ItemType.SPAWN_EGG).add(spawnEgg);
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

    // Handles FishingFrenzyClient -> Targeted Bait Alpha
    public static void registerItemColorProviders() {
        for (FishRegistry registry : FISH_REGISTRIES) {
            registry.registerItemColorProviders();
        }
    }


}
