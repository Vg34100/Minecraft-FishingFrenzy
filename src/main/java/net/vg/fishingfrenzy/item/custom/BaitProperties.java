package net.vg.fishingfrenzy.item.custom;

import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.RegistryKey;

public interface BaitProperties {

    default int getLureBonus() {
        return 0;
    }

    default int getLuckBonus() {
        return 0;
    }

    default float getUseChance() {
        return 1.0f;
    }

    default int getMultiCatchAmount() {
        return 0;
    }

    default float getMultiCatchChance() {
        return 0.0f;
    }

    default RegistryKey<LootTable> getLootTable() {
        return LootTables.FISHING_GAMEPLAY;
    }

    default Item getTargetedFish() { return null; }
}
