package net.vg.fishingfrenzy.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.vg.fishingfrenzy.config.ModConfigs;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.item.custom.FishItem;

import java.util.List;

public class ModEntitySpawns {
    public static void addEntitySpawns() {
        for (Item fishItem : ModItems.FISH_ITEMS) {
            if (fishItem instanceof FishItem) {
                if (((FishItem) fishItem).hasFishEntityType()) {
                    addSpawnForFish((FishItem) fishItem);
                }
            }
        }
    }

    private static boolean isWithinTimeRange(long currentTime, int minTime, int maxTime) {
        currentTime = currentTime % 24000;
        if (minTime <= maxTime) {
            // Normal case: minTime to maxTime within the same day
            return currentTime >= minTime && currentTime <= maxTime;
        } else {
            // Across midnight case: from minTime to 24000 OR from 0 to maxTime
            return currentTime >= minTime || currentTime <= maxTime;
        }
    }

    private static void addSpawnForFish(FishItem fishItem) {
        List<RegistryKey<Biome>> fishBiomes = fishItem.getBiomes();

        if (ModConfigs.EASY_MODE) {
            // Allow spawning in any biome when EASY_MODE is enabled
            BiomeModifications.addSpawn(
                    BiomeSelectors.all(),
                    SpawnGroup.WATER_AMBIENT,
                    fishItem.getFishEntityType(),
                    fishItem.getSpawningWeight(), // You might want to adjust this based on the fish's properties
                    fishItem.getGroupSizes().getFirst(),  // Min group size
                    fishItem.getGroupSizes().getSecond()   // Max group size
            );
        } else {
            BiomeModifications.addSpawn(
                    BiomeSelectors.includeByKey(fishBiomes),
                    SpawnGroup.WATER_AMBIENT,
                    fishItem.getFishEntityType(),
                    fishItem.getSpawningWeight(), // You might want to adjust this based on the fish's properties
                    fishItem.getGroupSizes().getFirst(),  // Min group size
                    fishItem.getGroupSizes().getSecond()   // Max group size
            );
        }



        // Add spawn restriction
        SpawnRestriction.register(
                fishItem.getFishEntityType(),
                SpawnLocationTypes.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, spawnReason, pos, random) -> {
                    // Check if easy mode is enabled
                    if (ModConfigs.EASY_MODE) {
                        return true; // Allow spawning without any additional conditions
                    }
                    // Check Y-range
                    if (!fishItem.getYRange().test((double) pos.getY())) {
                        return false;
                    }

                    // Check time of day
                    long timeOfDay = world.toServerWorld().getTimeOfDay();
                    if (!isWithinTimeRange(timeOfDay, fishItem.getMinTime(), fishItem.getMaxTime())) {
                        return false;
                    }

                    // Check weather conditions
                    if (fishItem.isWeatherDependent()) {
                        boolean isRaining = world.toServerWorld().isRaining();
                        boolean isThundering = world.toServerWorld().isThundering();

                        if (fishItem.isRaining() && !isRaining) {
                            return false;
                        }
                        if (fishItem.isThundering() && !isThundering) {
                            return false;
                        }
                        if (!fishItem.isRaining() && !fishItem.isThundering() && (isRaining || isThundering)) {
                            return false;
                        }
                    }

                    // If all conditions are met, check vanilla water spawn conditions
                    return true;                }
        );

    }
}
