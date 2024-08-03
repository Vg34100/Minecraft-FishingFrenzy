package net.vg.fishingfrenzy.management;

import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.vg.fishingfrenzy.entity.ModEntities;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.item.custom.BaitPropertiesBuilder;
import net.vg.fishingfrenzy.item.custom.FishPropertiesBuilder;
import net.vg.fishingfrenzy.item.custom.FishRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FishManager {
    public static final List<FishRegistry> FISH_REGISTRIES = new ArrayList<>();
    public static final Map<String, FishRegistry> FISH_REGISTRY_MAP = new HashMap<>();

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
    }

    public static void registerItemModels(ItemModelGenerator itemModelGenerator) {
        for (FishRegistry registry : FISH_REGISTRIES) {
            registry.registerItemModels(itemModelGenerator);
        }
    }

    public static void registerItemColorProviders() {
        for (FishRegistry registry : FISH_REGISTRIES) {
            registry.registerItemColorProviders();
        }
    }


}
