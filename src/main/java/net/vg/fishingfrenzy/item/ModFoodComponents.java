package net.vg.fishingfrenzy.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {

    // Ocean (Pufferfish)
//    public static final FoodComponent RAW_ANCHOVY = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).build();   // Anytime
//    public static final FoodComponent RAW_SARDINE = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).build();   // Morning - Night
//    public static final FoodComponent RAW_RED_MULLET = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Morning - Night
//    public static final FoodComponent RAW_HERRING = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Anytime
//    public static final FoodComponent RAW_RED_SNAPPER = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).build(); // Morning - Night
//    public static final FoodComponent RAW_SQUID = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100), 0.2f).build();
//    public static final FoodComponent RAW_ALBACORE = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();
//    public static final FoodComponent RAW_HALIBUT = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();
//
//
//    public static final FoodComponent RAW_TUNA = new FoodComponent.Builder().nutrition(3).saturationModifier(0.1f).build(); // Island // Morning - Night
//    public static final FoodComponent RAW_OCTOPUS = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Island
//    public static final FoodComponent RAW_FLOUNDER = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Island
//    public static final FoodComponent RAW_TILAPIA = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Island
//
//    public static final FoodComponent RAW_LIONFISH = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // ONLY Island // Anytime
//
//    // Night Market
//    public static final FoodComponent MIDNIGHT_SQUID = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();
//    public static final FoodComponent SPOOK_FISH = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();
//    public static final FoodComponent BLOBFISH = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();
//
//
//
//
//    public static final FoodComponent RAW_EEL = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).snack().statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 150, 2), 0.1f).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 150, 2), 0.8f).build();
//    public static final FoodComponent SEA_CUCUMBER = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Morning - Night
//    public static final FoodComponent SUPER_CUCUMBER = new FoodComponent.Builder().nutrition(4).saturationModifier(0.1f).build(); // Island
//
//    // River & Mountain
//    public static final FoodComponent RAW_WALLEYE = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();
//    public static final FoodComponent RAW_PERCH = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Anytime
//    public static final FoodComponent RAW_CHUB = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).build(); // Anytime
//    public static final FoodComponent RAW_LINGCOD = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Anytime
//
//
//    // River (Salmon)
//    public static final FoodComponent RAW_BREAM = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();
//    public static final FoodComponent RAW_SMALLMOUTH_BASS = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).build(); // Anytime
//    public static final FoodComponent RAW_RAINBOW_TROUT = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Morning - Night
//    public static final FoodComponent RAW_PIKE = new FoodComponent.Builder().nutrition(3).saturationModifier(0.1f).build(); // Anytime
//    public static final FoodComponent RAW_SUNFISH = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Morning - Night
//    public static final FoodComponent RAW_TIGER_TROUT = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Morning - Night
//    public static final FoodComponent RAW_DORADO = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Morning - Night
//    public static final FoodComponent RAW_SHAD = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();
//
//    public static final FoodComponent RAW_BLUE_DISCUS = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // ONLY Island // Anytime
//
//    public static final FoodComponent RAW_CATFISH = new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).build(); // Secret Woods // Anytime
//    public static final FoodComponent RAW_WOODSKIP = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Secret Woods // Anytime
//
//    public static final FoodComponent RAW_GOBY = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Anytime
//
//
//    // Mountain
//    public static final FoodComponent RAW_CARP = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Anytime
//    public static final FoodComponent RAW_LARGEMOUTH_BASS = new FoodComponent.Builder().nutrition(3).saturationModifier(0.1f).build(); // Morning - Night
//    public static final FoodComponent RAW_STURGEON = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Morning - Night
//    public static final FoodComponent RAW_BULLHEAD = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Anytime
//
//    public static final FoodComponent RAW_MIDNIGHT_CARP = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Night & Island
//
//    // Desert
//    public static final FoodComponent RAW_SANDFISH = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();
//    public static final FoodComponent SCORPION_CARP = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build();
//
//
//    // Underground
//    public static final FoodComponent RAW_GHOSTFISH = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Anytime
//    public static final FoodComponent COBBLESTONE_FISH = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Anytime
//    public static final FoodComponent ICE_PIP = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Ice - Freezing // Anytime
//    public static final FoodComponent LAVA_EEL = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Lava - Fire // Anytime
//    public static final FoodComponent VOID_SALMON = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Witch // Anytime
//    public static final FoodComponent SLIMEJACK = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).build(); // Bug Lair // Anytime


}
