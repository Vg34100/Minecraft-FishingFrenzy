package net.vg.fishingfrenzy.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.item.custom.BaitItem;
import net.vg.fishingfrenzy.item.custom.BaitPropertiesBuilder;
import net.vg.fishingfrenzy.item.custom.DeluxeFishingRodItem;

public class ModItems {

//    public static final Item BAIT = registerItem("bait",
//            new BaitItem(new Item.Settings().maxCount(64)));

    public static final Item GENERIC_BAIT = registerItem("generic_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLureBonus(5)
                    .setLuckBonus(1)));

    public static final Item DELUXE_BAIT = registerItem("deluxe_bait",
            new BaitItem(new Item.Settings(), new BaitPropertiesBuilder()
                    .setLureBonus(10)
                    .setLuckBonus(3)
                    .setMultiCatchAmount(3)
                    .setMultiCatchChance(0.5f)));


    public static final Item DELUXE_FISHING_ROD = registerItem("deluxe_fishing_rod",
            new DeluxeFishingRodItem(new Item.Settings().maxDamage(64).component(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT)));

    // Ocean
    public static final Item RAW_ANCHOVY = registerItem("raw_anchovy",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_ANCHOVY)));
    public static final Item RAW_SARDINE = registerItem("raw_sardine",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_SARDINE)));
    public static final Item RAW_RED_MULLET = registerItem("raw_red_mullet",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_RED_MULLET)));
    public static final Item RAW_HERRING = registerItem("raw_herring",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_HERRING)));
    public static final Item RAW_RED_SNAPPER = registerItem("raw_red_snapper",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_RED_SNAPPER)));
    public static final Item RAW_SQUID = registerItem("raw_squid",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_SQUID)));
    public static final Item RAW_ALBACORE = registerItem("raw_albacore",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_ALBACORE)));
    public static final Item RAW_HALIBUT = registerItem("raw_halibut",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_HALIBUT)));

    public static final Item RAW_TUNA = registerItem("raw_tuna",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_TUNA)));
    public static final Item RAW_OCTOPUS = registerItem("raw_octopus",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_OCTOPUS)));
    public static final Item RAW_FLOUNDER = registerItem("raw_flounder",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_FLOUNDER)));
    public static final Item RAW_TILAPIA = registerItem("raw_tilapia",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_TILAPIA)));

    public static final Item RAW_LIONFISH = registerItem("raw_lionfish",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_LIONFISH)));


    // Night Market
    public static final Item MIDNIGHT_SQUID = registerItem("midnight_squid",
            new Item(new Item.Settings().food(ModFoodComponents.MIDNIGHT_SQUID)));
    public static final Item SPOOK_FISH = registerItem("spook_fish",
            new Item(new Item.Settings().food(ModFoodComponents.SPOOK_FISH)));
    public static final Item BLOBFISH = registerItem("blobfish",
            new Item(new Item.Settings().food(ModFoodComponents.BLOBFISH)));

    // Ocean (continued)
    public static final Item RAW_EEL = registerItem("raw_eel",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_EEL)));
    public static final Item SEA_CUCUMBER = registerItem("sea_cucumber",
            new Item(new Item.Settings().food(ModFoodComponents.SEA_CUCUMBER)));
    public static final Item SUPER_CUCUMBER = registerItem("super_cucumber",
            new Item(new Item.Settings().food(ModFoodComponents.SUPER_CUCUMBER)));

    // River & Mountain
    public static final Item RAW_WALLEYE = registerItem("raw_walleye",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_WALLEYE)));
    public static final Item RAW_PERCH = registerItem("raw_perch",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_PERCH)));
    public static final Item RAW_CHUB = registerItem("raw_chub",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_CHUB)));
    public static final Item RAW_LINGCOD = registerItem("raw_lingcod",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_LINGCOD)));

    // River (Salmon)
    public static final Item RAW_BREAM = registerItem("raw_bream",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_BREAM)));
    public static final Item RAW_SMALLMOUTH_BASS = registerItem("raw_smallmouth_bass",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_SMALLMOUTH_BASS)));
    public static final Item RAW_RAINBOW_TROUT = registerItem("raw_rainbow_trout",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_RAINBOW_TROUT)));
    public static final Item RAW_PIKE = registerItem("raw_pike",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_PIKE)));
    public static final Item RAW_SUNFISH = registerItem("raw_sunfish",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_SUNFISH)));
    public static final Item RAW_TIGER_TROUT = registerItem("raw_tiger_trout",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_TIGER_TROUT)));
    public static final Item RAW_DORADO = registerItem("raw_dorado",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_DORADO)));
    public static final Item RAW_SHAD = registerItem("raw_shad",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_SHAD)));

    public static final Item RAW_BLUE_DISCUS = registerItem("raw_blue_discus",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_BLUE_DISCUS)));

    public static final Item RAW_CATFISH = registerItem("raw_catfish",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_CATFISH)));
    public static final Item RAW_WOODSKIP = registerItem("raw_woodskip",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_WOODSKIP)));

    public static final Item RAW_GOBY = registerItem("raw_goby",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_GOBY)));

    // Mountain
    public static final Item RAW_CARP = registerItem("raw_carp",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_CARP)));
    public static final Item RAW_LARGEMOUTH_BASS = registerItem("raw_largemouth_bass",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_LARGEMOUTH_BASS)));
    public static final Item RAW_STURGEON = registerItem("raw_sturgeon",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_STURGEON)));
    public static final Item RAW_BULLHEAD = registerItem("raw_bullhead",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_BULLHEAD)));

    public static final Item RAW_MIDNIGHT_CARP = registerItem("raw_midnight_carp",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_MIDNIGHT_CARP)));

    // Desert
    public static final Item RAW_SANDFISH = registerItem("raw_sandfish",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_SANDFISH)));
    public static final Item SCORPION_CARP = registerItem("scorpion_carp",
            new Item(new Item.Settings().food(ModFoodComponents.SCORPION_CARP)));

    // Underground
    public static final Item RAW_GHOSTFISH = registerItem("raw_ghostfish",
            new Item(new Item.Settings().food(ModFoodComponents.RAW_GHOSTFISH)));
    public static final Item COBBLESTONE_FISH = registerItem("cobblestone_fish",
            new Item(new Item.Settings().food(ModFoodComponents.COBBLESTONE_FISH)));
    public static final Item ICE_PIP = registerItem("ice_pip",
            new Item(new Item.Settings().food(ModFoodComponents.ICE_PIP)));
    public static final Item LAVA_EEL = registerItem("lava_eel",
            new Item(new Item.Settings().food(ModFoodComponents.LAVA_EEL)));
    public static final Item VOID_SALMON = registerItem("void_salmon",
            new Item(new Item.Settings().food(ModFoodComponents.VOID_SALMON)));
    public static final Item SLIMEJACK = registerItem("slimejack",
            new Item(new Item.Settings().food(ModFoodComponents.SLIMEJACK)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name), item);
    }


    public static void registerItems() {
        FishingFrenzy.LOGGER.info("Registering Items for: " + FishingFrenzy.MOD_ID);
    }
}
