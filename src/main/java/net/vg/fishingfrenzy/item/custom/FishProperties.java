package net.vg.fishingfrenzy.item.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.world.biome.Biome;
import net.vg.fishingfrenzy.util.StatusEffectEntry;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface FishProperties {

    // Fishing Loot Table Properties
    default int getWeight() {
        return 25;
    }
    default int getQuality() {
        return 0;
    }

    // Loot Table and Entity Spawning Conditions
    default NumberRange.DoubleRange getYRange() {
        return NumberRange.DoubleRange.ANY;
    }
    default int getMinTime() {
        return 0;
    }
    default int getMaxTime() {
        return 24000;
    }
    default boolean isWeatherDependent() {
        return false;
    }
    default boolean isRaining() {
        return false;
    }
    default boolean isThundering() {
        return false;
    }
    default List<RegistryKey<Biome>> getBiomes() {
        return List.of();
    }

    // Spawn Egg Properties
    default int getPrimaryColor() {
        return 0xffd476;
    }
    default int getSecondaryColor() { return 0xb29452; }
    // Spawn Egg and Entity Creation
    default EntityType<? extends MobEntity> getFishEntityType() {
        return null;
    }
    default boolean hasFishEntityType() {
        return getFishEntityType() != null;
    }

    // Entity Spawning Properties
    default int getSpawningWeight() { return 5; }
    default Pair<Integer, Integer> getGroupSizes()  { return Pair.of(2, 3); }


    // Food Properties
    default Pair<Integer, Float> getFoodAttributes() {
        return Pair.of(2, 0.1f);
    }
    default List<StatusEffectEntry> getStatusEffects() {
        return List.of();
    }

    // Cooked Food Properties
    default Pair<Integer, Float> getCookedFoodAttributes() {
        return Pair.of(4, 2.5f);
    }
    default List<StatusEffectEntry> getCookedStatusEffects() {
        return List.of();
    }

    default boolean isSnack() {
        return false;
    }

    // Breeding Properties
    default Item getBreedingItem() {
        return Items.SEAGRASS;
    }

    // Entity Properties
    default double getBabySizeMultiplier() {
        return 0.5;
    }
    default int getMaxHealth() {
        return 3;
    }
    default float getMoveSpeed() {
        return 1.0f;
    }
    default List<Item> getAdditionalDrops() {
        return List.of();
    }
    default boolean shouldAttack() {
        return false;
    }
    default float getAttackDamage() {
        return 1.0f;
    }

    // Add a Target Goal List

}
