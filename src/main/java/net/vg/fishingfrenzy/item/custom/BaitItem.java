package net.vg.fishingfrenzy.item.custom;

import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;

public class BaitItem extends Item implements BaitProperties {
    private final int lureBonus;
    private final int luckBonus;
    private final float useChance;
    private final int multiCatchAmount;
    private final float multiCatchChance;
    private final RegistryKey<LootTable> lootTable;
    private final Item targetedFish;


    public BaitItem(Settings settings, BaitProperties properties) {
        super(settings);
        this.lureBonus = properties.getLureBonus();
        this.luckBonus = properties.getLuckBonus();

        this.useChance = properties.getUseChance();
        this.multiCatchAmount = properties.getMultiCatchAmount();
        this.multiCatchChance = properties.getMultiCatchChance();
        this.lootTable = properties.getLootTable();
        this.targetedFish = properties.getTargetedFish();
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

    @Override
    public Item getTargetedFish() {
        return targetedFish;
    }


}
