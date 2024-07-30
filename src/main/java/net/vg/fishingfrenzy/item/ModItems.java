package net.vg.fishingfrenzy.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.datagen.ModLootTableProvider;
import net.vg.fishingfrenzy.item.custom.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModItems {

    public static final List<Item> FISH_ITEMS = new ArrayList<>();
    public static final List<Item> TARGETED_BAIT_ITEMS = new ArrayList<>();

    public static final Item DELUXE_FISHING_ROD = registerItem("deluxe_fishing_rod",
            new DeluxeFishingRodItem(new Item.Settings().maxDamage(64).component(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT)));


    // Basic
    public static final Item GENERIC_BAIT = registerItem("generic_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLureBonus(2)
                    .setLuckBonus(1)));

    // Overall better
    public static final Item DELUXE_BAIT = registerItem("deluxe_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLureBonus(10)
                    .setLuckBonus(3)
                    .setMultiCatchAmount(2)
                    .setMultiCatchChance(0.2f)));

    // Double the fish
    public static final Item WILD_BAIT = registerItem("wild_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLureBonus(8)
                    .setMultiCatchAmount(1)
                    .setMultiCatchChance(0.9f)));

    // Treasure greater chance
    public static final Item MAGNET = registerItem("magnet",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLuckBonus(-1)
                    .setLootTable(ModLootTableProvider.MAGNET_FISHING_GAMEPLAY)
                    .setMultiCatchAmount(1)
                    .setMultiCatchChance(0.05f)));

    // Small Chance for 4 fish
    public static final Item CHALLENGE_BAIT = registerItem("challenge_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLuckBonus(4)
                    .setLureBonus(2)
                    .setMultiCatchAmount(3)
                    .setMultiCatchChance(0.1f)));





    public static final List<RegistryKey<Biome>> WARMWATERS = List.of(
            BiomeKeys.OCEAN,
            BiomeKeys.LUKEWARM_OCEAN,
            BiomeKeys.DEEP_LUKEWARM_OCEAN,
            BiomeKeys.WARM_OCEAN,
            BiomeKeys.BEACH,
            BiomeKeys.STONY_SHORE,
            BiomeKeys.DESERT
    );

    public static final List<RegistryKey<Biome>> COLDWATERS = List.of(
            BiomeKeys.COLD_OCEAN,
            BiomeKeys.DEEP_COLD_OCEAN,
            BiomeKeys.FROZEN_OCEAN,
            BiomeKeys.DEEP_FROZEN_OCEAN,
            BiomeKeys.SNOWY_BEACH,
            BiomeKeys.FROZEN_RIVER
    );

    public static final List<RegistryKey<Biome>> RIVERS = List.of(
            BiomeKeys.FROZEN_RIVER,
            BiomeKeys.RIVER
    );

    public static final List<RegistryKey<Biome>> BEACHES = List.of(
            BiomeKeys.BEACH,
            BiomeKeys.SNOWY_BEACH,
            BiomeKeys.STONY_SHORE
    );


    public static final List<RegistryKey<Biome>> FORESTS = List.of(
            BiomeKeys.FOREST,
            BiomeKeys.FLOWER_FOREST,
            BiomeKeys.BIRCH_FOREST,
            BiomeKeys.DARK_FOREST,
            BiomeKeys.OLD_GROWTH_BIRCH_FOREST,
            BiomeKeys.OLD_GROWTH_PINE_TAIGA,
            BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
            BiomeKeys.CHERRY_GROVE
    );

    public static final List<RegistryKey<Biome>> JUNGLES = List.of(
            BiomeKeys.JUNGLE,
            BiomeKeys.BAMBOO_JUNGLE,
            BiomeKeys.SPARSE_JUNGLE
    );

    public static final List<RegistryKey<Biome>> SWAMPS = List.of(
            BiomeKeys.SWAMP,
            BiomeKeys.MANGROVE_SWAMP
    );

    public static final List<RegistryKey<Biome>> MOUNTAINS = List.of(
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
    );

    public static final List<RegistryKey<Biome>> ICY_BIOMES = List.of(
            BiomeKeys.SNOWY_SLOPES,
            BiomeKeys.FROZEN_PEAKS,
            BiomeKeys.JAGGED_PEAKS,
            BiomeKeys.STONY_PEAKS,
            BiomeKeys.ICE_SPIKES
    );

    public static final List<RegistryKey<Biome>> PLAINS = List.of(
            BiomeKeys.PLAINS,
            BiomeKeys.SUNFLOWER_PLAINS,
            BiomeKeys.SAVANNA,
            BiomeKeys.SAVANNA_PLATEAU,
            BiomeKeys.MEADOW
    );

    public static final List<RegistryKey<Biome>> SAVANNAS = List.of(
            BiomeKeys.SAVANNA,
            BiomeKeys.SAVANNA_PLATEAU,
            BiomeKeys.BADLANDS,
            BiomeKeys.WINDSWEPT_SAVANNA
            );

    public static final List<RegistryKey<Biome>> DESERTS = List.of(
            BiomeKeys.DESERT,
            BiomeKeys.BADLANDS,
            BiomeKeys.ERODED_BADLANDS,
            BiomeKeys.WOODED_BADLANDS
    );

    public static final List<RegistryKey<Biome>> SNOWY_BIOMES = List.of(
            BiomeKeys.SNOWY_TAIGA,
            BiomeKeys.SNOWY_SLOPES,
            BiomeKeys.ICE_SPIKES
    );

    public static final List<RegistryKey<Biome>> UNDERGROUND = List.of(
            BiomeKeys.DRIPSTONE_CAVES,
            BiomeKeys.LUSH_CAVES,
            BiomeKeys.DEEP_DARK
    );

    public static final List<RegistryKey<Biome>> MISCELLANEOUS = List.of(
            BiomeKeys.MUSHROOM_FIELDS
    );

    // No Range given means anywhere
    public static final NumberRange.DoubleRange SEALEVEL = NumberRange.DoubleRange.between(0.0, 70.0);
    public static final NumberRange.DoubleRange MOUNTAINLEVEL = NumberRange.DoubleRange.between(65.0, 155.0);
    public static final NumberRange.DoubleRange HIGHPLATEAUS = NumberRange.DoubleRange.between(90.0, 160.0);
    public static final NumberRange.DoubleRange SKYLEVEL = NumberRange.DoubleRange.atLeast(150.0);
    public static final NumberRange.DoubleRange UNDERGROUNDLEVEL = NumberRange.DoubleRange.between(10.0, 50.0);
    public static final NumberRange.DoubleRange CAVERN = NumberRange.DoubleRange.atMost(10.0);
    public static final NumberRange.DoubleRange DEEP_DARK = NumberRange.DoubleRange.atMost(-40.0);
    public static final NumberRange.DoubleRange UNDERWATER_CAVES = NumberRange.DoubleRange.between(-10.0, 30.0);
    public static final NumberRange.DoubleRange LOWLANDS = NumberRange.DoubleRange.between(30.0, 65.0);



    @SafeVarargs
    public static List<RegistryKey<Biome>> combine(List<RegistryKey<Biome>>... lists) {
        List<RegistryKey<Biome>> combinedList = new ArrayList<>();

        for (List<RegistryKey<Biome>> list : lists) {
            combinedList.addAll(list);
        }

        return Collections.unmodifiableList(combinedList); // Make the list immutable
    }

    // Ocean
    public static final Item RAW_ANCHOVY = registerFishItem("raw_anchovy",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_ANCHOVY), new FishPropertiesBuilder()
                    .setWeight(50)
                    .setBiomes(combine(WARMWATERS, COLDWATERS, BEACHES, FORESTS))
                    .setYRange(SEALEVEL)
                    .setQuality(1)));

    public static final Item RAW_SARDINE = registerFishItem("raw_sardine",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SARDINE), new FishPropertiesBuilder()
                    .setWeight(30)
                    .setMinTime(6000)
                    .setMaxTime(18000)
                    .setYRange(SEALEVEL)));

    public static final Item RAW_RED_MULLET = registerFishItem("raw_red_mullet",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_RED_MULLET), new FishPropertiesBuilder()
                    .setWeight(35)
                    .setBiomes(combine(BEACHES, WARMWATERS, DESERTS))
                    .setYRange(SEALEVEL)));

    public static final Item RAW_HERRING = registerFishItem("raw_herring",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_HERRING), new FishPropertiesBuilder()
                    .setWeight(65)
                    .setMinTime(6000)
                    .setMaxTime(18000)));

    public static final Item RAW_RED_SNAPPER = registerFishItem("raw_red_snapper",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_RED_SNAPPER), new FishPropertiesBuilder()
                    .setWeight(50)
                    .setBiomes(combine(BEACHES, WARMWATERS, JUNGLES))
                    .setRaining(true)
                    .setWeatherDependent(true)));

    public static final Item RAW_SQUID = registerFishItem("raw_squid",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SQUID), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setMinTime(18000)
                    .setMaxTime(24000)
                    .setYRange(UNDERWATER_CAVES)
                    .setBiomes(combine(WARMWATERS, COLDWATERS, UNDERGROUND, BEACHES))));

    public static final Item RAW_ALBACORE = registerFishItem("raw_albacore",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_ALBACORE), new FishPropertiesBuilder()
                    .setWeight(30)
                    .setYRange(SEALEVEL)
                    .setBiomes(combine(WARMWATERS, COLDWATERS, RIVERS, BEACHES))));

    public static final Item RAW_HALIBUT = registerFishItem("raw_halibut",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_HALIBUT), new FishPropertiesBuilder()
                    .setWeight(35)
                    .setMinTime(18000)
                    .setMaxTime(6000)
                    .setYRange(SEALEVEL)
                    .setBiomes(combine(WARMWATERS, COLDWATERS, MOUNTAINS, BEACHES))));

    public static final Item RAW_TUNA = registerFishItem("raw_tuna",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_TUNA), new FishPropertiesBuilder()
                    .setWeight(60)
                    .setYRange(SEALEVEL)
                    .setBiomes(combine(WARMWATERS, COLDWATERS, RIVERS, BEACHES, MOUNTAINS, FORESTS))
                    .setQuality(2)));

    public static final Item RAW_OCTOPUS = registerFishItem("raw_octopus",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_OCTOPUS), new FishPropertiesBuilder()
                    .setWeight(10)
                    .setQuality(2)
                    .setMinTime(6000)
                    .setMaxTime(18000)
                    .setBiomes(combine(WARMWATERS, COLDWATERS, UNDERGROUND, MISCELLANEOUS))
                    .setYRange(SEALEVEL)));

    public static final Item RAW_FLOUNDER = registerFishItem("raw_flounder",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_FLOUNDER), new FishPropertiesBuilder()
                    .setWeight(30)
                    .setQuality(1)));

    public static final Item RAW_TILAPIA = registerFishItem("raw_tilapia",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_TILAPIA), new FishPropertiesBuilder()
                    .setWeight(35)
                    .setMinTime(2000)
                    .setMaxTime(18000)
                    .setBiomes(combine(RIVERS, FORESTS, SWAMPS))
                    .setYRange(LOWLANDS)));

    public static final Item RAW_LIONFISH = registerFishItem("raw_lionfish",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_LIONFISH), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setQuality(2)
                    .setMinTime(2000)
                    .setMaxTime(18000)
                    .setBiomes(combine(WARMWATERS, COLDWATERS, BEACHES, JUNGLES))));

    // Night Market
    public static final Item MIDNIGHT_SQUID = registerFishItem("midnight_squid",
            new FishItem(new Item.Settings().food(ModFoodComponents.MIDNIGHT_SQUID), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setQuality(2)
                    .setMinTime(18000)
                    .setMaxTime(6000)
                    .setYRange(CAVERN)
                    .setRaining(true)
                    .setWeatherDependent(true)));

    public static final Item SPOOK_FISH = registerFishItem("spook_fish",
            new FishItem(new Item.Settings().food(ModFoodComponents.SPOOK_FISH), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setQuality(2)
                    .setMinTime(18000)
                    .setMaxTime(6000)
                    .setBiomes(combine(SWAMPS, UNDERGROUND))));

    public static final Item BLOBFISH = registerFishItem("blobfish",
            new FishItem(new Item.Settings().food(ModFoodComponents.BLOBFISH), new FishPropertiesBuilder()
                    .setWeight(10)
                    .setMinTime(18000)
                    .setMaxTime(6000)
                    .setBiomes(MISCELLANEOUS)));

    // Ocean (continued)
    public static final Item RAW_EEL = registerFishItem("raw_eel",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_EEL), new FishPropertiesBuilder()
                    .setWeight(25)
                    .setYRange(UNDERWATER_CAVES)
                    .setQuality(1)));

    public static final Item SEA_CUCUMBER = registerFishItem("sea_cucumber",
            new FishItem(new Item.Settings().food(ModFoodComponents.SEA_CUCUMBER), new FishPropertiesBuilder()
                    .setWeight(15)
                    .setBiomes(combine(WARMWATERS, COLDWATERS, BEACHES, FORESTS))));

    public static final Item SUPER_CUCUMBER = registerFishItem("super_cucumber",
            new FishItem(new Item.Settings().food(ModFoodComponents.SUPER_CUCUMBER), new FishPropertiesBuilder()
                    .setWeight(10)
                    .setBiomes(combine(WARMWATERS, COLDWATERS, SWAMPS, SNOWY_BIOMES))
                    .setYRange(NumberRange.DoubleRange.atMost(60.0))
                    .setQuality(1)));

    // River & Mountain
    public static final Item RAW_WALLEYE = registerFishItem("raw_walleye",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_WALLEYE), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setWeatherDependent(true)
                    .setBiomes(combine(RIVERS, WARMWATERS, FORESTS, PLAINS))
                    .setMinTime(4000)
                    .setMaxTime(18000)
                    .setRaining(true)
                    .setThundering(false)
                    .setQuality(2)));

    public static final Item RAW_PERCH = registerFishItem("raw_perch",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_PERCH), new FishPropertiesBuilder()
                    .setWeight(15)
                    .setBiomes(combine(RIVERS, FORESTS, WARMWATERS, BEACHES, JUNGLES))
                    .setYRange(LOWLANDS)));

    public static final Item RAW_CHUB = registerFishItem("raw_chub",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_CHUB), new FishPropertiesBuilder()
                    .setWeight(10)));

    public static final Item RAW_LINGCOD = registerFishItem("raw_lingcod",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_LINGCOD), new FishPropertiesBuilder()
                    .setWeight(25)
                    .setMinTime(2000)
                    .setMaxTime(10000)
                    .setBiomes(combine(RIVERS, FORESTS, MOUNTAINS))
                    .setYRange(MOUNTAINLEVEL)));

    // River (Salmon)
    public static final Item RAW_BREAM = registerFishItem("raw_bream",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_BREAM), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setBiomes(combine(RIVERS, FORESTS, PLAINS))
                    .setYRange(NumberRange.DoubleRange.between(10.0, 40.0))));

    public static final Item RAW_SMALLMOUTH_BASS = registerFishItem("raw_smallmouth_bass",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SMALLMOUTH_BASS), new FishPropertiesBuilder()
                    .setWeight(40)
                    .setBiomes(combine(RIVERS, FORESTS, PLAINS, JUNGLES, SWAMPS))));

    public static final Item RAW_RAINBOW_TROUT = registerFishItem("raw_rainbow_trout",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_RAINBOW_TROUT), new FishPropertiesBuilder()
                    .setWeight(15)
                    .setMinTime(2000)
                    .setMaxTime(18000)
                    .setBiomes(combine(RIVERS, FORESTS, MOUNTAINS))
                    .setYRange(MOUNTAINLEVEL)));

    public static final Item RAW_PIKE = registerFishItem("raw_pike",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_PIKE), new FishPropertiesBuilder()
                    .setWeight(25)
                    .setBiomes(combine(RIVERS, FORESTS, SWAMPS))));

    public static final Item RAW_SUNFISH = registerFishItem("raw_sunfish",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SUNFISH), new FishPropertiesBuilder()
                    .setWeight(10)
                    .setMinTime(6000)
                    .setMaxTime(18000)
                    .setBiomes(combine(RIVERS, FORESTS, PLAINS))
                    .setYRange(LOWLANDS)));

    public static final Item RAW_TIGER_TROUT = registerFishItem("raw_tiger_trout",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_TIGER_TROUT), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setBiomes(combine(RIVERS, MOUNTAINS, FORESTS))
                    .setYRange(MOUNTAINLEVEL)));

    public static final Item RAW_DORADO = registerFishItem("raw_dorado",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_DORADO), new FishPropertiesBuilder()
                    .setWeight(30)
                    .setMinTime(6000)
                    .setMaxTime(18000)
                    .setBiomes(combine(RIVERS, PLAINS))
                    .setYRange(LOWLANDS)));

    public static final Item RAW_SHAD = registerFishItem("raw_shad",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SHAD), new FishPropertiesBuilder()
                    .setWeight(15)
                    .setBiomes(combine(RIVERS, FORESTS, SWAMPS))));

    public static final Item RAW_BLUE_DISCUS = registerFishItem("raw_blue_discus",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_BLUE_DISCUS), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setQuality(1)
                    .setMinTime(6000)
                    .setMaxTime(18000)
                    .setBiomes(combine(RIVERS, JUNGLES, WARMWATERS))
                    .setYRange(LOWLANDS)));

    public static final Item RAW_CATFISH = registerFishItem("raw_catfish",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_CATFISH), new FishPropertiesBuilder()
                    .setWeight(30)
                    .setQuality(1)
                    .setMinTime(2000)
                    .setMaxTime(18000)
                    .setBiomes(combine(RIVERS, SWAMPS, MISCELLANEOUS))
                    .setYRange(LOWLANDS)));

    public static final Item RAW_WOODSKIP = registerFishItem("raw_woodskip",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_WOODSKIP), new FishPropertiesBuilder()
                    .setWeight(15)
                    .setQuality(1)
                    .setBiomes(combine(RIVERS, FORESTS, MISCELLANEOUS))));

    public static final Item RAW_GOBY = registerFishItem("raw_goby",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_GOBY), new FishPropertiesBuilder()
                    .setWeight(10)
                    .setBiomes(combine(RIVERS, FORESTS))
                    .setYRange(LOWLANDS)));

    // Mountain
    public static final Item RAW_CARP = registerFishItem("raw_carp",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_CARP), new FishPropertiesBuilder()
                    .setWeight(45)
                    .setBiomes(combine(RIVERS, PLAINS, FORESTS, MOUNTAINS))));

    public static final Item RAW_LARGEMOUTH_BASS = registerFishItem("raw_largemouth_bass",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_LARGEMOUTH_BASS), new FishPropertiesBuilder()
                    .setWeight(30)
                    .setMinTime(500)
                    .setMaxTime(19000)
                    .setBiomes(combine(RIVERS, PLAINS, FORESTS))));

    public static final Item RAW_STURGEON = registerFishItem("raw_sturgeon",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_STURGEON), new FishPropertiesBuilder()
                    .setWeight(35)
                    .setMinTime(2000)
                    .setMaxTime(18000)
                    .setBiomes(combine(RIVERS, MISCELLANEOUS, MOUNTAINS, SWAMPS))
                    .setYRange(MOUNTAINLEVEL)));

    public static final Item RAW_BULLHEAD = registerFishItem("raw_bullhead",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_BULLHEAD), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setBiomes(combine(RIVERS, MOUNTAINS))));

    public static final Item RAW_MIDNIGHT_CARP = registerFishItem("raw_midnight_carp",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_MIDNIGHT_CARP), new FishPropertiesBuilder()
                    .setWeight(10)
                    .setMinTime(18000)
                    .setMaxTime(6000)
                    .setBiomes(combine(RIVERS, FORESTS, MOUNTAINS))
                    .setYRange(MOUNTAINLEVEL)
                    .setQuality(1)));

    // Desert
    public static final Item RAW_SANDFISH = registerFishItem("raw_sandfish",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SANDFISH), new FishPropertiesBuilder()
                    .setWeight(25)
                    .setBiomes(combine(DESERTS, SAVANNAS))));

    public static final Item SCORPION_CARP = registerFishItem("scorpion_carp",
            new FishItem(new Item.Settings().food(ModFoodComponents.SCORPION_CARP), new FishPropertiesBuilder()
                    .setWeight(10)
                    .setMinTime(6000)
                    .setMaxTime(24000)
                    .setBiomes(combine(DESERTS, SAVANNAS))));

    // Underground
    public static final Item RAW_GHOSTFISH = registerFishItem("raw_ghostfish",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_GHOSTFISH), new FishPropertiesBuilder()
                    .setWeight(15)
                    .setQuality(1)
                    .setYRange(DEEP_DARK)));

    public static final Item COBBLESTONE_FISH = registerFishItem("cobblestone_fish",
            new FishItem(new Item.Settings().food(ModFoodComponents.COBBLESTONE_FISH), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setYRange(CAVERN)));

    public static final Item ICE_PIP = registerFishItem("ice_pip",
            new FishItem(new Item.Settings().food(ModFoodComponents.ICE_PIP), new FishPropertiesBuilder()
                    .setWeight(25)
                    .setMinTime(6000)
                    .setMaxTime(24000)
                    .setBiomes(combine(ICY_BIOMES, SNOWY_BIOMES, MOUNTAINS))));

    public static final Item LAVA_EEL = registerFishItem("lava_eel",
            new FishItem(new Item.Settings().food(ModFoodComponents.LAVA_EEL), new FishPropertiesBuilder()
                    .setWeight(30)
                    .setYRange(UNDERGROUNDLEVEL)));

    public static final Item VOID_SALMON = registerFishItem("void_salmon",
            new FishItem(new Item.Settings().food(ModFoodComponents.VOID_SALMON), new FishPropertiesBuilder()
                    .setWeight(20)
                    .setYRange(DEEP_DARK)));

    public static final Item SLIMEJACK = registerFishItem("slimejack",
            new FishItem(new Item.Settings().food(ModFoodComponents.SLIMEJACK), new FishPropertiesBuilder()
                    .setWeight(15)
                    .setBiomes(combine(SWAMPS, UNDERGROUND))));

    public static final Item BONEFISH = registerFishItem("bonefish",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SARDINE), new FishPropertiesBuilder()
                    .setWeight(10)
                    .setBiomes(combine(WARMWATERS, COLDWATERS, JUNGLES, SWAMPS))
                    .setYRange(CAVERN) // there's also .atMost() or .between()
                    .setQuality(1)));

    public static final Item HARPYFISH = registerFishItem("harpyfish",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SARDINE), new FishPropertiesBuilder()
                    .setWeight(10)
                    .setYRange(SKYLEVEL) // there's also .atMost() or .between()
                    .setQuality(1)));

    public static final Item FLYING_FISH = registerFishItem("flying_fish",
            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SARDINE), new FishPropertiesBuilder()
                    .setWeight(10)
                    .setYRange(HIGHPLATEAUS) // there's also .atMost() or .between()
                    .setQuality(1)));


    // Targeted Bait

//    public static final Item COD_BAIT = registerBaitItem("cod_bait",
//            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
//                    .setLuckBonus(4)
//                    .setLureBonus(2)
//                    .setTargetedFish(Items.COD)
//                    .setMultiCatchAmount(3)
//                    .setMultiCatchChance(0.1f)));
//    public static final Item CARP_BAIT = registerBaitItem("carp_bait",
//            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
//                    .setLuckBonus(4)
//                    .setLureBonus(2)
//                    .setTargetedFish(ModItems.RAW_CARP)
//                    .setMultiCatchAmount(3)
//                    .setMultiCatchChance(0.1f)));

    private static void createBait() {
        for (Item fish : FISH_ITEMS) {
            String fishName = Registries.ITEM.getId(fish).getPath();
            String baitName = fishName + "_bait";
            registerBaitItem(baitName,
                    new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                            .setLuckBonus(4)
                            .setLureBonus(2)
                            .setTargetedFish(fish)
                            .setMultiCatchAmount(3)
                            .setMultiCatchChance(0.1f)));
        }
    }


    private static Item registerBaitItem(String name, Item item) {
        TARGETED_BAIT_ITEMS.add(item);
        return Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name), item);
    }

    private static Item registerFishItem(String name, Item item) {
        FISH_ITEMS.add(item);
        return Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name), item);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name), item);
    }


    public static void registerItems() {
        FishingFrenzy.LOGGER.info("Registering Items for: " + FishingFrenzy.MOD_ID);
        createBait();
    }
}
