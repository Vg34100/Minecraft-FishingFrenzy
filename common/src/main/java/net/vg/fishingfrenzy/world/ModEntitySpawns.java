package net.vg.fishingfrenzy.world;

import dev.architectury.hooks.level.biome.BiomeProperties;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.vg.fishingfrenzy.Constants;
import net.vg.fishingfrenzy.config.ModConfigs;
import net.vg.fishingfrenzy.item.fish.FishManager;
import net.vg.fishingfrenzy.item.fish.FishRegistry;

import java.util.Random;

public class ModEntitySpawns {

    public static void addEntitySpawnsBasic() {
        Constants.LOGGER.info("Attempting to add basic entity spawns");

        FishManager.FISH_REGISTRIES.forEach(fishRegistry -> {
            Constants.LOGGER.info("Spawn for {}", fishRegistry.getFishName());
            // Add spawn to all biomes
            BiomeModifications.addProperties(
                    context -> true, // This will add the spawn to all biomes
                    (context, properties) -> properties.getSpawnProperties().addSpawn(
                            MobCategory.WATER_AMBIENT,
                            new MobSpawnSettings.SpawnerData(
                                    fishRegistry.getFishEntityType().get(),
                                    fishRegistry.getSpawningWeight(),
                                    fishRegistry.getGroupSizes().getFirst(),
                                    fishRegistry.getGroupSizes().getSecond()
                            )
                    )
            );

            // Register spawn placement
            SpawnPlacementsRegistry.register(
                    fishRegistry.getFishEntityType(),
                    SpawnPlacementTypes.IN_WATER,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    (entityType, serverLevelAccessor, mobSpawnType, blockPos, random) ->
                            // Only check if the spawn location is in water
                            serverLevelAccessor.getFluidState(blockPos).is(FluidTags.WATER)
            );
        });
        Constants.LOGGER.info("Added basic entity spawns");
    }

    public static void addEntitySpawns() {
        FishManager.FISH_REGISTRIES.forEach(fishRegistry -> {

            BiomeModifications.addProperties(
                    context -> shouldAddSpawn(context, fishRegistry),
                    (context, properties) -> properties.getSpawnProperties().addSpawn(
                            MobCategory.WATER_AMBIENT,
                            new MobSpawnSettings.SpawnerData(
                                    fishRegistry.getFishEntityType().get(),
                                    fishRegistry.getSpawningWeight(),
                                    fishRegistry.getGroupSizes().getFirst(),
                                    fishRegistry.getGroupSizes().getSecond()
                            )
                    )
            );

            SpawnPlacementsRegistry.register(
                    fishRegistry.getFishEntityType(),
                    SpawnPlacementTypes.IN_WATER,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    ((entityType, serverLevelAccessor, mobSpawnType, blockPos, randomSource) -> canSpawn(entityType, serverLevelAccessor, mobSpawnType, blockPos, randomSource, fishRegistry))

            );
        });
    }


    private static boolean shouldAddSpawn(BiomeModifications.BiomeContext context, FishRegistry fishRegistry) {
        return  true;
//        if (ModConfigs.EASY_MODE) {
//            return true;
//        }
//        return fishRegistry.getBiomes().isEmpty() || context.getKey()
//                .map(key -> fishRegistry.getBiomes().contains(key))
//                .orElse(false);
    }

    private static boolean canSpawn(EntityType<? extends Entity> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random, FishRegistry fishRegistry) {
        if (ModConfigs.EASY_MODE) {
            return true;
        }
        if (!fishRegistry.getYRange().matches(pos.getY())) {
            return false;
        }
        long timeOfDay = level.getLevelData().getDayTime();
        if (!isWithinTimeRange(timeOfDay, fishRegistry.getMinTime(), fishRegistry.getMaxTime())) {
            return false;
        }
        if (fishRegistry.isWeatherDependent()) {
            boolean isRaining = level.getLevel().isRaining();
            boolean isThundering = level.getLevel().isThundering();

            if (fishRegistry.isRaining() && !isRaining) {
                return false;
            }
            if (fishRegistry.isThundering() && !isThundering) {
                return false;
            }
            if (!fishRegistry.isRaining() && !fishRegistry.isThundering() && (isRaining || isThundering)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isWithinTimeRange(long currentTime, int minTime, int maxTime) {
        currentTime = currentTime % 24000;
        if (minTime <= maxTime) {
            return currentTime >= minTime && currentTime <= maxTime;
        } else {
            return currentTime >= minTime || currentTime <= maxTime;
        }
    }
}
