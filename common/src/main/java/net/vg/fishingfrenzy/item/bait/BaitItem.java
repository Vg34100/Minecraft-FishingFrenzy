package net.vg.fishingfrenzy.item.bait;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootTable;

public class BaitItem extends Item implements BaitProperties {
    private final int lureBonus;
    private final int luckBonus;
    private final float useChance;
    private final int multiCatchAmount;
    private final float multiCatchChance;
    private final ResourceKey<LootTable> lootTable;
    private final Item targetedFish;


    public BaitItem(Properties pProperties, BaitProperties bProperties) {
        super(pProperties);

        this.lureBonus = bProperties.getLureBonus();
        this.luckBonus = bProperties.getLuckBonus();

        this.useChance = bProperties.getUseChance();
        this.multiCatchAmount = bProperties.getMultiCatchAmount();
        this.multiCatchChance = bProperties.getMultiCatchChance();
        this.lootTable = bProperties.getLootTable();
        this.targetedFish = bProperties.getTargetedFish();
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
    public ResourceKey<LootTable> getLootTable() {
        return lootTable;
    }

    @Override
    public Item getTargetedFish() {
        return targetedFish;
    }

}
