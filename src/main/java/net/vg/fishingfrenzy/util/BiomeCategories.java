package net.vg.fishingfrenzy.util;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.List;
import java.util.stream.Stream;

public enum BiomeCategories {
    WARM_WATERS(List.of(
            BiomeKeys.OCEAN,
            BiomeKeys.DEEP_OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.BEACH,
            BiomeKeys.STONY_SHORE,
            BiomeKeys.DESERT
    )),
    COLD_WATERS(List.of(
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
            BiomeKeys.SNOWY_BEACH,
            BiomeKeys.FROZEN_RIVER
    )),
    RIVERS(List.of(
            BiomeKeys.FROZEN_RIVER,
            BiomeKeys.RIVER
    )),
    BEACHES(List.of(
            BiomeKeys.BEACH,
            BiomeKeys.SNOWY_BEACH,
            BiomeKeys.STONY_SHORE
    )),
    FORESTS(List.of(
            BiomeKeys.FOREST,
            BiomeKeys.FLOWER_FOREST,
            BiomeKeys.BIRCH_FOREST,
            BiomeKeys.DARK_FOREST,
            BiomeKeys.OLD_GROWTH_BIRCH_FOREST,
            BiomeKeys.OLD_GROWTH_PINE_TAIGA,
            BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
            BiomeKeys.CHERRY_GROVE
    )),
    JUNGLES(List.of(
            BiomeKeys.JUNGLE,
            BiomeKeys.BAMBOO_JUNGLE,
            BiomeKeys.SPARSE_JUNGLE
    )),
    SWAMPS(List.of(
            BiomeKeys.SWAMP,
            BiomeKeys.MANGROVE_SWAMP
    )),
    MOUNTAINS(List.of(
            BiomeKeys.WINDSWEPT_HILLS,
            BiomeKeys.WINDSWEPT_GRAVELLY_HILLS,
            BiomeKeys.WINDSWEPT_FOREST,
            BiomeKeys.WINDSWEPT_SAVANNA,
            BiomeKeys.SNOWY_SLOPES,
            BiomeKeys.FROZEN_PEAKS,
            BiomeKeys.JAGGED_PEAKS,
            BiomeKeys.STONY_PEAKS,
            BiomeKeys.CHERRY_GROVE,
            BiomeKeys.GROVE
    )),
    ICY_BIOMES(List.of(
            BiomeKeys.SNOWY_SLOPES,
            BiomeKeys.FROZEN_PEAKS,
            BiomeKeys.JAGGED_PEAKS,
            BiomeKeys.STONY_PEAKS,
            BiomeKeys.ICE_SPIKES
    )),
    PLAINS(List.of(
            BiomeKeys.PLAINS,
            BiomeKeys.SUNFLOWER_PLAINS,
            BiomeKeys.SAVANNA,
            BiomeKeys.SAVANNA_PLATEAU,
            BiomeKeys.MEADOW
    )),
    SAVANNAS(List.of(
            BiomeKeys.SAVANNA,
            BiomeKeys.SAVANNA_PLATEAU,
            BiomeKeys.BADLANDS,
            BiomeKeys.WINDSWEPT_SAVANNA
    )),
    DESERTS(List.of(
            BiomeKeys.DESERT,
            BiomeKeys.BADLANDS,
            BiomeKeys.ERODED_BADLANDS,
            BiomeKeys.WOODED_BADLANDS
    )),
    SNOWY_BIOMES(List.of(
            BiomeKeys.SNOWY_TAIGA,
            BiomeKeys.SNOWY_SLOPES,
            BiomeKeys.ICE_SPIKES
    )),
    UNDERGROUND(List.of(
            BiomeKeys.DRIPSTONE_CAVES,
            BiomeKeys.LUSH_CAVES,
            BiomeKeys.DEEP_DARK
    )),
    MISCELLANEOUS(List.of(
            BiomeKeys.MUSHROOM_FIELDS
    ));


    private final List<RegistryKey<Biome>> biomes;

    BiomeCategories(List<RegistryKey<Biome>> biomes) {
        this.biomes = biomes;
    }

    public List<RegistryKey<Biome>> getBiomes() {
        return biomes;
    }

    public static List<RegistryKey<Biome>> combine(BiomeCategories... categories) {
        return Stream.of(categories)
                .flatMap(category -> category.getBiomes().stream())
                .distinct()
                .toList();
    }
}
