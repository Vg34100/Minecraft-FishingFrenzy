package net.vg.fishingfrenzy.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.config.ModConfigs;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.item.custom.FishItem;
import net.vg.fishingfrenzy.management.FishManager;


public class ModLootTableModifiers {

    private static final RegistryKey<LootTable> FISHING_LOOT_TABLE_KEY = LootTables.FISHING_FISH_GAMEPLAY;

    public static LootCondition.Builder createTimeCondition(int minTime, int maxTime) {
        if (minTime < maxTime) {
            return TimeCheckLootCondition.create(BoundedIntUnaryOperator.create(minTime, maxTime)).period(24000L);
        } else {
            return AnyOfLootCondition.builder(
                    TimeCheckLootCondition.create(BoundedIntUnaryOperator.create(minTime, 24000)).period(24000L),
                    TimeCheckLootCondition.create(BoundedIntUnaryOperator.create(0, maxTime)).period(24000L)
            );
        }
    }

    public static void addItemToFishingLootTable(Item item, int weight) {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (FISHING_LOOT_TABLE_KEY.equals(key)) {
                tableBuilder.modifyPools(pools -> {
                   pools.with(ItemEntry.builder(item)
                           .weight(weight));
                });

            }
        });
    }

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (FISHING_LOOT_TABLE_KEY.equals(key)) {
                FishManager.modifyFishingLootTable(tableBuilder, registries);
            }
        });

//        LootTableEvents.REPLACE.register((key, original, source, registries) -> {
//            if (source.isBuiltin() && FISHING_LOOT_TABLE_KEY.equals(key)) {
//                RegistryWrapper.Impl<Biome> biomeRegistry = registries.getWrapperOrThrow(RegistryKeys.BIOME);
//
//                LootPool.Builder poolBuilder = LootPool.builder()
//                        .rolls(ConstantLootNumberProvider.create(1))
//                        .with(ItemEntry.builder(Items.COD)
//                                .weight(60))
//                        .with(ItemEntry.builder(Items.SALMON)
//                                .weight(25))
//                        .with(ItemEntry.builder(Items.TROPICAL_FISH)
//                                .weight(2))
//                        .with(ItemEntry.builder(Items.PUFFERFISH)
//                                .weight(13));
//
//                for (Item fish : ModItems.FISH_ITEMS) {
//                    FishingFrenzy.LOGGER.info("Adding " + fish.getName() + " to FISHING_FISH_GAMEPLAY");
//                    if (fish instanceof FishItem fishItem) {
//
//                        if (ModConfigs.EASY_MODE) {
//                            poolBuilder.with(ItemEntry.builder(fish)
//                                    .weight(fishItem.getWeight())
//                                    .quality(fishItem.getQuality()));
//                        } else {
//                            LootCondition.Builder locationCondition = RandomChanceLootCondition.builder(1.0f);
//                            if (!fishItem.getBiomes().isEmpty()) {
//                                locationCondition = LocationCheckLootCondition.builder(
//                                        LocationPredicate.Builder.create()
//                                                .biome(fishItem.getEntryBiomes(biomeRegistry))
//                                );
//                            }
//
//
//                            LootCondition.Builder timeCondition = createTimeCondition(fishItem.getMinTime(), fishItem.getMaxTime());
//
//                            poolBuilder.with(ItemEntry.builder(fish)
//                                    .weight(fishItem.getWeight())
//                                    .quality(((FishItem) fish).getQuality())
//                                    .conditionally(timeCondition)
//                                    .conditionally((fishItem.isWeatherDependent() ? WeatherCheckLootCondition.create()
//                                            .raining(fishItem.isRaining())
//                                            .thundering(fishItem.isThundering()) : RandomChanceLootCondition.builder(1.0f))) // Weather condition
//                                    .conditionally(LocationCheckLootCondition.builder(
//                                            LocationPredicate.Builder.createY(fishItem.getYRange()))
//                                    )
//                                    .conditionally(locationCondition));
//
//                        }
//                    }
//                }
//
//
//                return LootTable.builder().pool(poolBuilder).build();
//            }
//
//            return original;
//        });
//        addItemToFishingLootTable(FishManager.FISH_1.getFish(), 150);
    }
}