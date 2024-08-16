package net.vg.fishingfrenzy.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.datagen.ModLootTableProvider;
import net.vg.fishingfrenzy.item.custom.*;
import net.vg.fishingfrenzy.management.FishManager;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

//    public static final List<Item> FISH_ITEMS = new ArrayList<>();
//    public static final List<Item> FISH_SPAWN_EGGS = new ArrayList<>();

    public static final List<Item> FISHING_RODS = new ArrayList<>();

    public static final List<Item> BAIT_ITEMS = new ArrayList<>();
//    public static final List<Item> TARGETED_BAIT_ITEMS = new ArrayList<>();

    public static final Item TEST_BUCK = registerItem("item",
            new EntityBucketItem(
                    EntityType.PUFFERFISH,
                    Fluids.WATER,
                    SoundEvents.ITEM_BUCKET_EMPTY_FISH,
                    new Item.Settings().maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));

    public static final Item DELUXE_FISHING_ROD = registerItem("deluxe_fishing_rod",
            new DeluxeFishingRodItem(new Item.Settings().maxDamage(64).component(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT)), FISHING_RODS);


    // Basic
    public static final Item GENERIC_BAIT = registerItem("generic_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLureBonus(2)
                    .setLuckBonus(1)), BAIT_ITEMS);

    // Overall better
    public static final Item DELUXE_BAIT = registerItem("deluxe_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLureBonus(10)
                    .setLuckBonus(3)
                    .setMultiCatchAmount(2)
                    .setMultiCatchChance(0.2f)), BAIT_ITEMS);

    // Double the fish
    public static final Item WILD_BAIT = registerItem("wild_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLureBonus(8)
                    .setMultiCatchAmount(1)
                    .setMultiCatchChance(0.9f)), BAIT_ITEMS);

    // Double the fish
    public static final Item MAGIC_BAIT = registerItem("magic_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLureBonus(8)
                    .setMultiCatchAmount(9)
                    .setMultiCatchChance(0.2f)), BAIT_ITEMS);

    // Treasure greater chance
    public static final Item MAGNET = registerItem("magnet",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLuckBonus(-1)
                    .setLootTable(ModLootTableProvider.MAGNET_FISHING_GAMEPLAY)
                    .setMultiCatchAmount(1)
                    .setMultiCatchChance(0.05f)), BAIT_ITEMS);

    // Small Chance for 4 fish
    public static final Item CHALLENGE_BAIT = registerItem("challenge_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLuckBonus(4)
                    .setLureBonus(2)
                    .setMultiCatchAmount(3)
                    .setMultiCatchChance(0.1f)), BAIT_ITEMS);




    // Ocean
//    public static final Item RAW_ANCHOVY = registerFishItem("raw_anchovy",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_ANCHOVY), new FishPropertiesBuilder()
//                    .setWeight(50)
//                    .setBiomes(
//                            BiomeCategories.combine(
//                                    BiomeCategories.WARM_WATERS,
//                                    BiomeCategories.COLD_WATERS,
//                                    BiomeCategories.BEACHES,
//                                    BiomeCategories.FORESTS))
//                    .setYRange(HeightRanges.SEA_LEVEL.getRange())
//                    .setQuality(1)));
//
//    public static final Item RAW_SARDINE = registerFishItem("raw_sardine",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SARDINE), new FishPropertiesBuilder()
//                    .setWeight(30)
//                    .setMinTime(6000)
//                    .setMaxTime(18000)
//                    .setYRange(HeightRanges.SEA_LEVEL.getRange())));
//
//    public static final Item RAW_RED_MULLET = registerFishItem("raw_red_mullet",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_RED_MULLET), new FishPropertiesBuilder()
//                    .setWeight(35)
//                    .setBiomes(
//                            BiomeCategories.combine(
//                                    BiomeCategories.BEACHES,
//                                    BiomeCategories.WARM_WATERS,
//                                    BiomeCategories.DESERTS))
//                    .setYRange(HeightRanges.SEA_LEVEL.getRange())));
//
//    public static final Item RAW_HERRING = registerFishItem("raw_herring",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_HERRING), new FishPropertiesBuilder()
//                    .setWeight(65)
//                    .setMinTime(6000)
//                    .setMaxTime(18000)));
//
//    public static final Item RAW_RED_SNAPPER = registerFishItem("raw_red_snapper",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_RED_SNAPPER), new FishPropertiesBuilder()
//                    .setWeight(50)
//                    .setBiomes(
//                            BiomeCategories.combine(
//                                    BiomeCategories.BEACHES,
//                                    BiomeCategories.WARM_WATERS,
//                                    BiomeCategories.JUNGLES))
//                    .setRaining(true)
//                    .setWeatherDependent(true)));
//
//    public static final Item RAW_SQUID = registerFishItem("raw_squid",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SQUID), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setMinTime(18000)
//                    .setMaxTime(24000)
//                    .setYRange(HeightRanges.UNDERWATER_CAVES.getRange())
//                    .setBiomes(
//                            BiomeCategories.combine(
//                                    BiomeCategories.WARM_WATERS,
//                                    BiomeCategories.COLD_WATERS,
//                                    BiomeCategories.UNDERGROUND,
//                                    BiomeCategories.BEACHES))));

//    public static final Item RAW_ALBACORE = registerFishItem("raw_albacore",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_ALBACORE), new FishPropertiesBuilder()
//                    .setFishEntityType(ModEntities.ALBACORE)
//                    .setPrimaryColor(0x72b287)
//                    .setSecondaryColor(0x196c8d)
//                    .setWeight(30)
//                    .setYRange(HeightRanges.SEA_LEVEL.getRange())
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.WARM_WATERS, BiomeCategories.COLD_WATERS, BiomeCategories.RIVERS, BiomeCategories.BEACHES))));


//    public static final Item RAW_HALIBUT = registerFishItem("raw_halibut",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_HALIBUT), new FishPropertiesBuilder()
//                    .setWeight(35)
//                    .setMinTime(18000)
//                    .setMaxTime(6000)
//                    .setYRange(HeightRanges.SEA_LEVEL.getRange())
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.WARM_WATERS, BiomeCategories.COLD_WATERS, BiomeCategories.MOUNTAINS, BiomeCategories.BEACHES))));
//
//    public static final Item RAW_TUNA = registerFishItem("raw_tuna",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_TUNA), new FishPropertiesBuilder()
//                    .setWeight(60)
//                    .setYRange(HeightRanges.SEA_LEVEL.getRange())
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.WARM_WATERS, BiomeCategories.COLD_WATERS, BiomeCategories.RIVERS, BiomeCategories.BEACHES, BiomeCategories.MOUNTAINS, BiomeCategories.FORESTS))
//                    .setQuality(2)));
//
//    public static final Item RAW_OCTOPUS = registerFishItem("raw_octopus",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_OCTOPUS), new FishPropertiesBuilder()
//                    .setWeight(10)
//                    .setQuality(2)
//                    .setMinTime(6000)
//                    .setMaxTime(18000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.WARM_WATERS, BiomeCategories.COLD_WATERS, BiomeCategories.UNDERGROUND, BiomeCategories.MISCELLANEOUS))
//                    .setYRange(HeightRanges.SEA_LEVEL.getRange())));
//
//    public static final Item RAW_FLOUNDER = registerFishItem("raw_flounder",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_FLOUNDER), new FishPropertiesBuilder()
//                    .setWeight(30)
//                    .setQuality(1)));
//
//    public static final Item RAW_TILAPIA = registerFishItem("raw_tilapia",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_TILAPIA), new FishPropertiesBuilder()
//                    .setWeight(35)
//                    .setMinTime(2000)
//                    .setMaxTime(18000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.SWAMPS))
//                    .setYRange(HeightRanges.LOWLANDS.getRange())));
//
//    public static final Item RAW_LIONFISH = registerFishItem("raw_lionfish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_LIONFISH), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setQuality(2)
//                    .setMinTime(2000)
//                    .setMaxTime(18000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.WARM_WATERS, BiomeCategories.COLD_WATERS, BiomeCategories.BEACHES, BiomeCategories.JUNGLES))));
//
//    // Night Market
//    public static final Item MIDNIGHT_SQUID = registerFishItem("midnight_squid",
//            new FishItem(new Item.Settings().food(ModFoodComponents.MIDNIGHT_SQUID), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setQuality(2)
//                    .setMinTime(18000)
//                    .setMaxTime(6000)
//                    .setYRange(HeightRanges.CAVERN.getRange())
//                    .setRaining(true)
//                    .setWeatherDependent(true)));
//
//    public static final Item SPOOK_FISH = registerFishItem("spook_fish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.SPOOK_FISH), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setQuality(2)
//                    .setMinTime(18000)
//                    .setMaxTime(6000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.SWAMPS, BiomeCategories.UNDERGROUND))));
//
//    public static final Item BLOBFISH = registerFishItem("blobfish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.BLOBFISH), new FishPropertiesBuilder()
//                    .setWeight(10)
//                    .setMinTime(18000)
//                    .setMaxTime(6000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.MISCELLANEOUS))));
//
//    // Ocean (continued)
//    public static final Item RAW_EEL = registerFishItem("raw_eel",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_EEL), new FishPropertiesBuilder()
//                    .setWeight(25)
//                    .setYRange(HeightRanges.UNDERWATER_CAVES.getRange())
//                    .setQuality(1)));
//
//    public static final Item SEA_CUCUMBER = registerFishItem("sea_cucumber",
//            new FishItem(new Item.Settings().food(ModFoodComponents.SEA_CUCUMBER), new FishPropertiesBuilder()
//                    .setWeight(15)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.WARM_WATERS, BiomeCategories.COLD_WATERS, BiomeCategories.BEACHES, BiomeCategories.FORESTS))));
//
//    public static final Item SUPER_CUCUMBER = registerFishItem("super_cucumber",
//            new FishItem(new Item.Settings().food(ModFoodComponents.SUPER_CUCUMBER), new FishPropertiesBuilder()
//                    .setWeight(10)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.WARM_WATERS, BiomeCategories.COLD_WATERS, BiomeCategories.SWAMPS, BiomeCategories.SNOWY_BIOMES))
//                    .setYRange(NumberRange.DoubleRange.atMost(60.0))
//                    .setQuality(1)));
//
//    // River & Mountain
//    public static final Item RAW_WALLEYE = registerFishItem("raw_walleye",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_WALLEYE), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setWeatherDependent(true)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.WARM_WATERS, BiomeCategories.FORESTS, BiomeCategories.PLAINS))
//                    .setMinTime(4000)
//                    .setMaxTime(18000)
//                    .setRaining(true)
//                    .setThundering(false)
//                    .setQuality(2)));
//
//    public static final Item RAW_PERCH = registerFishItem("raw_perch",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_PERCH), new FishPropertiesBuilder()
//                    .setWeight(15)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.WARM_WATERS, BiomeCategories.BEACHES, BiomeCategories.JUNGLES))
//                    .setYRange(HeightRanges.LOWLANDS.getRange())));
//
//    public static final Item RAW_CHUB = registerFishItem("raw_chub",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_CHUB), new FishPropertiesBuilder()
//                    .setWeight(10)));
//
//    public static final Item RAW_LINGCOD = registerFishItem("raw_lingcod",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_LINGCOD), new FishPropertiesBuilder()
//                    .setWeight(25)
//                    .setMinTime(2000)
//                    .setMaxTime(10000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.MOUNTAINS))
//                    .setYRange(HeightRanges.MOUNTAIN_LEVEL.getRange())));
//
//    // River (Salmon)
//    public static final Item RAW_BREAM = registerFishItem("raw_bream",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_BREAM), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.PLAINS))
//                    .setYRange(NumberRange.DoubleRange.between(10.0, 40.0))));
//
//    public static final Item RAW_SMALLMOUTH_BASS = registerFishItem("raw_smallmouth_bass",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SMALLMOUTH_BASS), new FishPropertiesBuilder()
//                    .setWeight(40)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.PLAINS, BiomeCategories.JUNGLES, BiomeCategories.SWAMPS))));
//
//    public static final Item RAW_RAINBOW_TROUT = registerFishItem("raw_rainbow_trout",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_RAINBOW_TROUT), new FishPropertiesBuilder()
//                    .setWeight(15)
//                    .setMinTime(2000)
//                    .setMaxTime(18000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.MOUNTAINS))
//                    .setYRange(HeightRanges.MOUNTAIN_LEVEL.getRange())));
//
//    public static final Item RAW_PIKE = registerFishItem("raw_pike",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_PIKE), new FishPropertiesBuilder()
//                    .setWeight(25)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.SWAMPS))));
//
//    public static final Item RAW_SUNFISH = registerFishItem("raw_sunfish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SUNFISH), new FishPropertiesBuilder()
//                    .setWeight(10)
//                    .setMinTime(6000)
//                    .setMaxTime(18000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.PLAINS))
//                    .setYRange(HeightRanges.LOWLANDS.getRange())));
//
//    public static final Item RAW_TIGER_TROUT = registerFishItem("raw_tiger_trout",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_TIGER_TROUT), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.MOUNTAINS, BiomeCategories.FORESTS))
//                    .setYRange(HeightRanges.MOUNTAIN_LEVEL.getRange())));
//
//    public static final Item RAW_DORADO = registerFishItem("raw_dorado",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_DORADO), new FishPropertiesBuilder()
//                    .setWeight(30)
//                    .setMinTime(6000)
//                    .setMaxTime(18000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.PLAINS))
//                    .setYRange(HeightRanges.LOWLANDS.getRange())));
//
//    public static final Item RAW_SHAD = registerFishItem("raw_shad",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SHAD), new FishPropertiesBuilder()
//                    .setWeight(15)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.SWAMPS))));
//
//    public static final Item RAW_BLUE_DISCUS = registerFishItem("raw_blue_discus",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_BLUE_DISCUS), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setQuality(1)
//                    .setMinTime(6000)
//                    .setMaxTime(18000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.JUNGLES, BiomeCategories.WARM_WATERS))
//                    .setYRange(HeightRanges.LOWLANDS.getRange())));
//
//    public static final Item RAW_CATFISH = registerFishItem("raw_catfish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_CATFISH), new FishPropertiesBuilder()
//                    .setWeight(30)
//                    .setQuality(1)
//                    .setMinTime(2000)
//                    .setMaxTime(18000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.SWAMPS, BiomeCategories.MISCELLANEOUS))
//                    .setYRange(HeightRanges.LOWLANDS.getRange())));
//
//    public static final Item RAW_WOODSKIP = registerFishItem("raw_woodskip",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_WOODSKIP), new FishPropertiesBuilder()
//                    .setWeight(15)
//                    .setQuality(1)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.MISCELLANEOUS))));
//
//    public static final Item RAW_GOBY = registerFishItem("raw_goby",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_GOBY), new FishPropertiesBuilder()
//                    .setWeight(10)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS))
//                    .setYRange(HeightRanges.LOWLANDS.getRange())));

    // Mountain
//    public static final Item RAW_CARP = registerFishItem("raw_carp",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_CARP), new FishPropertiesBuilder()
//                    .setFishEntityType(ModEntities.CARP)
//                    .setPrimaryColor(0xe5b650)
//                    .setSecondaryColor(0xffd578)
//                    .setWeight(45)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.PLAINS, BiomeCategories.FORESTS, BiomeCategories.MOUNTAINS))));

//    public static final Item RAW_LARGEMOUTH_BASS = registerFishItem("raw_largemouth_bass",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_LARGEMOUTH_BASS), new FishPropertiesBuilder()
//                    .setWeight(30)
//                    .setMinTime(500)
//                    .setMaxTime(19000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.PLAINS, BiomeCategories.FORESTS))));
//
//    public static final Item RAW_STURGEON = registerFishItem("raw_sturgeon",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_STURGEON), new FishPropertiesBuilder()
//                    .setWeight(35)
//                    .setMinTime(2000)
//                    .setMaxTime(18000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.MISCELLANEOUS, BiomeCategories.MOUNTAINS, BiomeCategories.SWAMPS))
//                    .setYRange(HeightRanges.MOUNTAIN_LEVEL.getRange())));
//
//    public static final Item RAW_BULLHEAD = registerFishItem("raw_bullhead",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_BULLHEAD), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.MOUNTAINS))));
//
//    public static final Item RAW_MIDNIGHT_CARP = registerFishItem("raw_midnight_carp",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_MIDNIGHT_CARP), new FishPropertiesBuilder()
//                    .setWeight(10)
//                    .setMinTime(18000)
//                    .setMaxTime(6000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.RIVERS, BiomeCategories.FORESTS, BiomeCategories.MOUNTAINS))
//                    .setYRange(HeightRanges.MOUNTAIN_LEVEL.getRange())
//                    .setQuality(1)));
//
//    // Desert
//    public static final Item RAW_SANDFISH = registerFishItem("raw_sandfish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SANDFISH), new FishPropertiesBuilder()
//                    .setWeight(25)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.DESERTS, BiomeCategories.SAVANNAS))));
//
//    public static final Item SCORPION_CARP = registerFishItem("scorpion_carp",
//            new FishItem(new Item.Settings().food(ModFoodComponents.SCORPION_CARP), new FishPropertiesBuilder()
//                    .setWeight(10)
//                    .setMinTime(6000)
//                    .setMaxTime(24000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.DESERTS, BiomeCategories.SAVANNAS))));
//
//    // Underground
//    public static final Item RAW_GHOSTFISH = registerFishItem("raw_ghostfish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_GHOSTFISH), new FishPropertiesBuilder()
//                    .setWeight(15)
//                    .setQuality(1)
//                    .setYRange(HeightRanges.DEEP_DARK.getRange())));
//
//    public static final Item COBBLESTONE_FISH = registerFishItem("cobblestone_fish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.COBBLESTONE_FISH), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setYRange(HeightRanges.CAVERN.getRange())));
//
//    public static final Item ICE_PIP = registerFishItem("ice_pip",
//            new FishItem(new Item.Settings().food(ModFoodComponents.ICE_PIP), new FishPropertiesBuilder()
//                    .setWeight(25)
//                    .setMinTime(6000)
//                    .setMaxTime(24000)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.ICY_BIOMES, BiomeCategories.SNOWY_BIOMES, BiomeCategories.MOUNTAINS))));
//
//    public static final Item LAVA_EEL = registerFishItem("lava_eel",
//            new FishItem(new Item.Settings().food(ModFoodComponents.LAVA_EEL), new FishPropertiesBuilder()
//                    .setWeight(30)
//                    .setYRange(HeightRanges.UNDERGROUND_LEVEL.getRange())));
//
//    public static final Item VOID_SALMON = registerFishItem("void_salmon",
//            new FishItem(new Item.Settings().food(ModFoodComponents.VOID_SALMON), new FishPropertiesBuilder()
//                    .setWeight(20)
//                    .setYRange(HeightRanges.DEEP_DARK.getRange())));
//
//    public static final Item SLIMEJACK = registerFishItem("slimejack",
//            new FishItem(new Item.Settings().food(ModFoodComponents.SLIMEJACK), new FishPropertiesBuilder()
//                    .setWeight(15)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.SWAMPS, BiomeCategories.UNDERGROUND))));

//    public static final Item BONEFISH = registerFishItem("bonefish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SARDINE), new FishPropertiesBuilder()
////                    .setFishEntityType(ModEntities.BONEFISH)
//                    .setPrimaryColor(0xa0a082)
//                    .setSecondaryColor(0x595945)
//                    .setWeight(10)
//                    .setBiomes(BiomeCategories.combine(BiomeCategories.WARM_WATERS, BiomeCategories.COLD_WATERS, BiomeCategories.JUNGLES, BiomeCategories.SWAMPS))
//                    .setYRange(HeightRanges.CAVERN.getRange())
//                    .setQuality(1)
//                    .setSpawningWeight(3)
//                    .setGroupSizes(Pair.of(1,2))));

//    public static final Item HARPYFISH = registerFishItem("harpyfish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SARDINE), new FishPropertiesBuilder()
//                    .setWeight(10)
//                    .setYRange(HeightRanges.SKY_LEVEL.getRange())
//                    .setQuality(1)
//                    .setPrimaryColor(0x0398fc)
//                    .setSecondaryColor(0xfc039d)));
//
//    public static final Item FLYING_FISH = registerFishItem("flying_fish",
//            new FishItem(new Item.Settings().food(ModFoodComponents.RAW_SARDINE), new FishPropertiesBuilder()
//                    .setWeight(10)
//                    .setYRange(HeightRanges.HIGH_PLATEAUS.getRange()) // there's also .atMost() or .between()
//                    .setQuality(1)
//                    .setPrimaryColor(0x036ffc)
//                    .setSecondaryColor(0xb0bdcf)));

//    public static final FishRegistry FISH_1 = new FishRegistry(
//            "fish_1",
//            new FishPropertiesBuilder()
//                    .setFishEntityType(ModEntities.BONEFISH)
//                    .setPrimaryColor(0x036ffc)
//                    .setSecondaryColor(0xb0bdcf),
//            new Item.Settings(),
//            new BaitPropertiesBuilder()
//
//
//
//
//    );



//    private static void createBait() {
//        for (Item fish : FISH_ITEMS) {
//            if (fish instanceof FishItem fishItem) {
//
//                String fishName = Registries.ITEM.getId(fish).getPath();
//                String baitName = fishName + "_bait";
//                registerItem(baitName,
//                        new TargetBaitItem(((FishItem) fish).getPrimaryColor(), ((FishItem) fish).getSecondaryColor(), new Item.Settings(), new BaitPropertiesBuilder()
//                                .setLuckBonus(4)
//                                .setLureBonus(2)
//                                .setTargetedFish(fish)
//                                .setMultiCatchAmount(3)
//                                .setMultiCatchChance(0.1f)),
//                        TARGETED_BAIT_ITEMS, BAIT_ITEMS);
//            }
//        }
//    }

//    private static Item registerBaitItem(String name, Item item) {
//        TARGETED_BAIT_ITEMS.add(item);
//        return Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name), item);
//    }

//    private static void createSpawnItems() {
//        for (Item fish : FISH_ITEMS) {
//            if (fish instanceof FishItem fishItem) {
//
//                if (fishItem.hasFishEntityType()) {
//                    EntityType<? extends MobEntity> entityType = fishItem.getFishEntityType();
//                    String fishName = Registries.ITEM.getId(fish).getPath();
//                    String eggName = fishName + "_spawn_egg";
//                    int primaryColor = fishItem.getPrimaryColor();
//                    int secondaryColor = fishItem.getSecondaryColor();
//
//                    FishingFrenzy.LOGGER.info("Creating spawn egg for " + fishName +
//                            " with colors: " + String.format("0x%06X", primaryColor) +
//                            ", " + String.format("0x%06X", secondaryColor));
//
//                    registerItem(eggName, new SpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Settings()), FISH_SPAWN_EGGS);
//                } else {
//                    FishingFrenzy.LOGGER.info("Skipping spawn egg creation for " + Registries.ITEM.getId(fish).getPath() +
//                            " as no entity type is set.");
//                }
//            }
//        }
//    }



//    private static Item registerSpawnEgg(String name, Item item) {
//        FISH_SPAWN_EGGS.add(item);
//        return Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name), item);
//    }

//    private static Item registerFishItem(String name, Item item) {
//        FISH_ITEMS.add(item);
//        return Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name), item);
//    }


    @SafeVarargs
    private static Item registerItem(String name, Item item, List<Item>... optionalLists) {
        Item registeredItem = Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name), item);
        for (List<Item> optionalList : optionalLists) {
            if (optionalList != null) {
                optionalList.add(registeredItem);
            }
        }
        return registeredItem;
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name), item);
    }


    public static void registerItems() {
        FishingFrenzy.LOGGER.info("Registering Items for: " + FishingFrenzy.MOD_ID);
        FishingFrenzy.LOGGER.warn("No data fixer registered error does not cause issues. It's just a thing.");
        FishManager.registerAllFish();

//
//        createBait();
//        createSpawnItems();
    }
}
