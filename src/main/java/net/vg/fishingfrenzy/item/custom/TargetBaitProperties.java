package net.vg.fishingfrenzy.item.custom;

import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.RegistryKey;

public interface TargetBaitProperties {

    default int getLureBonus() {
        return 4;
    }

    default int getLuckBonus() {
        return 2;
    }

    default float getUseChance() {
        return 1.0f;
    }

    default int getMultiCatchAmount() {
        return 3;
    }

    default float getMultiCatchChance() {
        return 0.1f;
    }

    default RegistryKey<LootTable> getLootTable() {
        return LootTables.FISHING_GAMEPLAY;
    }

    default Item getTargetedFish() { return null; }
}
