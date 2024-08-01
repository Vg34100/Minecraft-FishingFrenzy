package net.vg.fishingfrenzy.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.vg.fishingfrenzy.entity.ModEntities;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.item.custom.FishItem;

import java.util.List;
import java.util.function.Predicate;

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

    private static void addSpawnForFish(FishItem fishItem) {
        List<RegistryKey<Biome>> fishBiomes = fishItem.getBiomes();
        Predicate<BiomeSelectionContext> biomePredicate = context ->
                fishBiomes.contains(context.getBiomeKey());

        BiomeModifications.addSpawn(
                //BiomeSelectors.all().and(biomePredicate),
                BiomeSelectors.includeByKey(fishBiomes),
                SpawnGroup.WATER_AMBIENT,
                fishItem.getFishEntityType(),
                fishItem.getWeight(), // You might want to adjust this based on the fish's properties
                2,  // Min group size
                4   // Max group size
        );


        // Add spawn restriction
        SpawnRestriction.register(
                fishItem.getFishEntityType(),
                SpawnLocationTypes.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, spawnReason, pos, random) -> {
                    // Check Y-range
                    if (!fishItem.getYRange().test((double) pos.getY())) {
                        return false;
                    }

//                    // Check time of day
//                    long timeOfDay = world.getTimeOfDay() % 24000;
//                    if (timeOfDay < fishItem.getMinTime() || timeOfDay > fishItem.getMaxTime()) {
//                        return false;
//                    }
//
//                    // Check weather conditions
//                    if (fishItem.isWeatherDependent()) {
//                        boolean isRaining = world.isRaining();
//                        boolean isThundering = world.isThundering();
//
//                        if (fishItem.isRaining() && !isRaining) {
//                            return false;
//                        }
//                        if (fishItem.isThundering() && !isThundering) {
//                            return false;
//                        }
//                        if (!fishItem.isRaining() && !fishItem.isThundering() && (isRaining || isThundering)) {
//                            return false;
//                        }
//                    }

                    // If all conditions are met, check vanilla water spawn conditions
                    return true;                }
        );

    }
}
