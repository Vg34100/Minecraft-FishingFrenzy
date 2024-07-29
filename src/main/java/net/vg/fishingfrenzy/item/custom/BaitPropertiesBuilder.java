package net.vg.fishingfrenzy.item.custom;

import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.RegistryKey;

public class BaitPropertiesBuilder implements BaitProperties {
    private int lureBonus = 0;
    private int luckBonus = 0;
    private float useChance = 1.0f;
    private int multiCatchAmount = 0;
    private float multiCatchChance = 0.0f;
    private RegistryKey<LootTable> lootTable = LootTables.FISHING_GAMEPLAY;


    public BaitPropertiesBuilder setLureBonus(int lureBonus) {
        this.lureBonus = lureBonus;
        return this;
    }

    public BaitPropertiesBuilder setLuckBonus(int luckBonus) {
        this.luckBonus = luckBonus;
        return this;
    }

    public BaitPropertiesBuilder setUseChance(float useChance) {
        this.useChance = useChance;
        return this;
    }

    public BaitPropertiesBuilder setMultiCatchAmount(int multiCatchAmount) {
        this.multiCatchAmount = multiCatchAmount;
        return this;
    }

    public BaitPropertiesBuilder setMultiCatchChance(float multiCatchChance) {
        this.multiCatchChance = multiCatchChance;
        return this;
    }

    public BaitPropertiesBuilder setLootTable(RegistryKey<LootTable> lootTable) {
        this.lootTable = lootTable;
        return this;
    }




    @Override
    public int getLureBonus() {
        return lureBonus;
    }

    @Override
    public int getLuckBonus() {
        return luckBonus;
    }

    @Override
    public float getUseChance() {
        return useChance;
    }

    @Override
    public int getMultiCatchAmount() {
        return multiCatchAmount;
    }

    @Override
    public float getMultiCatchChance() {
        return multiCatchChance;
    }

    @Override
    public RegistryKey<LootTable> getLootTable() {
        return lootTable;
    }
}
