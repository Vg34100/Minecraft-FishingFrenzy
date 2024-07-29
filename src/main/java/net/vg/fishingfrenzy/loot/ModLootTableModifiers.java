package net.vg.fishingfrenzy.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.TimeCheckLootCondition;
import net.minecraft.loot.condition.WeatherCheckLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.vg.fishingfrenzy.item.ModItems;

public class ModLootTableModifiers {

    private static final RegistryKey<LootTable> FISHING_LOOT_TABLE_KEY = LootTables.FISHING_FISH_GAMEPLAY;

    public static void modifyLootTables() {
        LootTableEvents.REPLACE.register((key, original, source, registries) -> {
            if (source.isBuiltin() && FISHING_LOOT_TABLE_KEY.equals(key)) {
                RegistryWrapper.WrapperLookup wrapperLookup = registries;
                RegistryWrapper.Impl<Biome> biomeRegistry = wrapperLookup.getWrapperOrThrow(RegistryKeys.BIOME);

                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(Items.COD)
                                .weight(60))
                        .with(ItemEntry.builder(Items.SALMON)
                                .weight(25))
                        .with(ItemEntry.builder(Items.TROPICAL_FISH)
                                .weight(2))
                        .with(ItemEntry.builder(Items.PUFFERFISH)
                                .weight(13))
                        .with(ItemEntry.builder(ModItems.RAW_CARP)
                                .weight(3000)
                                .conditionally(TimeCheckLootCondition
                                        .create(BoundedIntUnaryOperator.create(6000, 18000)) // Available from 6000 to 18000 ticks (morning to evening)
                                        .period(24000L)) // Full Minecraft day cycle
                                .conditionally(WeatherCheckLootCondition.create()
                                        .raining(false)
                                        .thundering(false))
                                .conditionally(LocationCheckLootCondition.builder(
                                        LocationPredicate.Builder.create()
                                        .biome(RegistryEntryList.of(biomeRegistry.getOrThrow(BiomeKeys.RIVER))))
                        ));
                return LootTable.builder().pool(poolBuilder).build();
            }

            return original;
        });
    }
}