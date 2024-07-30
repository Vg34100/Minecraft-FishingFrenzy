package net.vg.fishingfrenzy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.function.SetPotionLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.FishingHookPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.vg.fishingfrenzy.FishingFrenzy;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModLootTableProvider extends SimpleFabricLootTableProvider {
    public ModLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.CHEST);
    }

    public static final RegistryKey<LootTable> MAGNET_FISHING_GAMEPLAY = RegistryKey.of(RegistryKeys.LOOT_TABLE,
            Identifier.of(FishingFrenzy.MOD_ID, "gameplay/magnet_fishing_gameplay"));

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> lootTableBiConsumer) {
        lootTableBiConsumer.accept(
                MAGNET_FISHING_GAMEPLAY,
                LootTable.builder()
                        .pool(
                                LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(LootTableEntry.builder(LootTables.FISHING_TREASURE_GAMEPLAY).weight(50).quality(-2))
                                        .with(LootTableEntry.builder(LootTables.FISHING_FISH_GAMEPLAY).weight(10).quality(-1))
                                        .with(LootTableEntry.builder(LootTables.FISHING_TREASURE_GAMEPLAY).weight(60).quality(2).conditionally(
                                                EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().typeSpecific(FishingHookPredicate.of(true)))
                                        ))
                                        .build()
                        )
        );

    }
}
