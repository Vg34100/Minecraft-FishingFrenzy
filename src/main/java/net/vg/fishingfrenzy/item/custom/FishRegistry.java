package net.vg.fishingfrenzy.item.custom;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.WeatherCheckLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.biome.Biome;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.vg.fishingfrenzy.datagen.ModItemTagProvider;
import net.vg.fishingfrenzy.management.FishManager;
import net.vg.fishingfrenzy.management.FishPreset;
import net.vg.fishingfrenzy.util.ModTags;
import net.vg.fishingfrenzy.util.StatusEffectEntry;

import java.util.List;
import java.util.Optional;

import static net.vg.fishingfrenzy.loot.ModLootTableModifiers.createTimeCondition;

public class FishRegistry {

    private final Item fish;
    private final Item spawnEgg;
    private final Item bait;

    private final String fish_name;

    private final int weight;
    private final int quality;
    private final NumberRange.DoubleRange yRange;
    private final int minTime;
    private final int maxTime;
    private final boolean isWeatherDependent;
    private final boolean raining;
    private final boolean thundering;
    private final List<RegistryKey<Biome>> biomes;
    private final int primaryColor;
    private final int secondaryColor;
    private final EntityType<? extends MobEntity> fishEntityType;
    private final int spawningWeight;
    private final Pair<Integer, Integer> groupSizes;

    public FishRegistry(String name, FishProperties properties) {
        this.fish_name = name;



        this.weight = properties.getWeight();
        this.quality = properties.getQuality();
        this.yRange = properties.getYRange();
        this.minTime = properties.getMinTime();
        this.maxTime = properties.getMaxTime();
        this.isWeatherDependent = properties.isWeatherDependent();
        this.raining = properties.isRaining();
        this.thundering = properties.isThundering();
        this.biomes = properties.getBiomes();
        this.primaryColor = properties.getPrimaryColor();
        this.secondaryColor = properties.getSecondaryColor();
        this.fishEntityType = properties.getFishEntityType();
        this.spawningWeight = properties.getSpawningWeight();
        this.groupSizes = properties.getGroupSizes();

        FoodComponent foodComponent = createFoodComponent(properties.getFoodAttributes(), properties.isSnack(), properties.getStatusEffects());


        this.fish = createFishItem(new Item.Settings().food(foodComponent), properties);
        this.spawnEgg = createSpawnEgg(new Item.Settings());
        this.bait = createBait(new Item.Settings(), new BaitPropertiesBuilder().setTargetedFish(this.fish));

        FishManager.addFishRegistry(this);
    }

    public int getWeight() {
        return weight;
    }
    public int getQuality() {
        return quality;
    }
    public NumberRange.DoubleRange getYRange() {
        return yRange;
    }
    public int getMinTime() {
        return minTime;
    }
    public int getMaxTime() {
        return maxTime;
    }
    public boolean isWeatherDependent() {
        return isWeatherDependent;
    }
    public boolean isRaining() {
        return raining;
    }
    public boolean isThundering() {
        return thundering;
    }
    public List<RegistryKey<Biome>> getBiomes() {
        return biomes;
    }
    public int getPrimaryColor() {
        return primaryColor;
    }
    public int getSecondaryColor() {
        return secondaryColor;
    }
    public EntityType<? extends MobEntity> getFishEntityType() {
        return fishEntityType;
    }
    public boolean hasFishEntityType() {
        return fishEntityType != null;
    }
    public int getSpawningWeight() {
        return spawningWeight;
    }
    public Pair<Integer, Integer> getGroupSizes() {
        return groupSizes;
    }
    public Item getFish() {
        return fish;
    }
    public Item getSpawnEgg() {
        return spawnEgg;
    }
    public Item getBait() {
        return bait;
    }
    public String getFishName() {
        return fish_name;
    }




    // Handles ModItems: registerFish
    private Item createFishItem(Item.Settings settings, FishProperties properties) {
        FishItem fishItem = new FishItem(settings, properties);
        Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, getFishName()), fishItem);
        return fishItem;
    }

    // Handles ModItems: registerSpawnEgg
    private Item createSpawnEgg(Item.Settings settings) {
        if (this.hasFishEntityType()) {
            SpawnEggItem spawnEgg = new SpawnEggItem(this.getFishEntityType(), this.getPrimaryColor(), this.getSecondaryColor(), settings);
            Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, getFishName() + "_spawn_egg"), spawnEgg);
            return spawnEgg;
        }
        return null;
    }

    // Handles ModItems: registerTargetedBait
    private Item createBait(Item.Settings settings, BaitProperties properties) {
        TargetBaitItem bait = new TargetBaitItem(this.getPrimaryColor(), this.getSecondaryColor(), settings, properties);
        Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, getFishName() + "_bait"), bait);
        return bait;
    }

    // Handles ModFoodComponents
    private FoodComponent createFoodComponent(Pair<Integer, Float> foodAttributes, boolean snack, List<StatusEffectEntry> statusEffects) {
        FoodComponent.Builder builder = new FoodComponent.Builder().nutrition(foodAttributes.getFirst()).saturationModifier(foodAttributes.getSecond());
        if (snack) {
            builder.snack();
        }
        for (StatusEffectEntry entry : statusEffects) {
            builder.statusEffect(new StatusEffectInstance(entry.getEffect(), entry.getDuration(), entry.getAmplifier()), entry.getChance());
        }
        return builder.build();
    }

    // Handles ModLootTableModifiers
    public void modifyFishingLootTable(LootTable.Builder tableBuilder, RegistryWrapper. WrapperLookup registries) {
        LootCondition.Builder locationCondition = RandomChanceLootCondition.builder(1.0f);
        RegistryWrapper.Impl<Biome> biomeRegistry = registries.getWrapperOrThrow(RegistryKeys.BIOME);

        if (!biomes.isEmpty()) {
            locationCondition = LocationCheckLootCondition.builder(
                    LocationPredicate.Builder.create().biome(getEntryBiomes(biomeRegistry))
            );
        }
        LootCondition.Builder timeCondition = createTimeCondition(minTime, maxTime);

        LootCondition.Builder finalLocationCondition = locationCondition;
        tableBuilder.modifyPools(pools -> {
            pools.with(ItemEntry.builder(fish)
                    .weight(weight)
                    .quality(quality)
                    .conditionally(timeCondition)
                    .conditionally(isWeatherDependent ? WeatherCheckLootCondition.create().raining(raining).thundering(thundering) : RandomChanceLootCondition.builder(1.0f))
                    .conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.createY(yRange)))
                    .conditionally(finalLocationCondition)
            );
        });
    }

    // Handles Data Generation: ModModelProvider
    public void registerItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(this.fish, Models.GENERATED);
        if (this.spawnEgg != null) {
            Model spawnEggModel = new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty());
            itemModelGenerator.register(this.spawnEgg, spawnEggModel);
        }
        if (this.bait != null) {
            Model baitModel = new Model(Optional.of(Identifier.of(FishingFrenzy.MOD_ID, "item/template_target_bait")), Optional.empty());
            itemModelGenerator.register(this.bait, baitModel);
        }
    }

    // Handle Data Generation: ModLangProvider
    public void registerTranslations(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        String fishName = Registries.ITEM.getId(fish).getPath();
        translationBuilder.add("item.fishingfrenzy." + fishName, capitalize(fishName.replace("_", " ")));

        if (bait != null) {
            String baitName = Registries.ITEM.getId(bait).getPath();
            translationBuilder.add("item.fishingfrenzy." + baitName, capitalize(baitName.replace("_", " ")));
        }


        if (spawnEgg != null) {
            String eggName = Registries.ITEM.getId(spawnEgg).getPath();
            String formattedName = capitalize(fishName.replace("_", " ")) + " Spawn Egg";
            translationBuilder.add("item.fishingfrenzy." + eggName, formattedName);
        }
    }

    // Handle Data Generation: ModItemTagProvider
    public void registerItemTags(ModItemTagProvider tagProvider) {
        tagProvider.addToTag(ItemTags.FISHES, fish);

        if (bait != null) {
            tagProvider.addToTag(ModTags.Items.BAITS, bait);
            tagProvider.addToTag(ModTags.Items.TARGET_BAITS, bait);
        }
    }

    // Handles FishingFrenzyClient -> Targeted Bait Alpha
    public void registerItemColorProviders() {
        if (this.bait != null) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorHelper.Argb.fullAlpha(((TargetBaitItem) stack.getItem()).getColor(tintIndex)), this.bait);
        }
    }

    // Helper function to capitalize strings
    private String capitalize(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    // Helper function to turn biomes list into correct class for LocationCondition
    public RegistryEntryList<Biome> getEntryBiomes(RegistryWrapper<Biome> biomeRegistry) {
        List<RegistryKey<Biome>> biomeKeys = getBiomes();
        if (biomeKeys.isEmpty()) {
            return RegistryEntryList.of();
        }
        return RegistryEntryList.of(
                biomeKeys.stream()
                        .map(biomeRegistry::getOrThrow)
                        .toList()
        );
    }

}
