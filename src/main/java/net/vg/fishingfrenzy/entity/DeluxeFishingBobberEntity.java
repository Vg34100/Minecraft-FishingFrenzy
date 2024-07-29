package net.vg.fishingfrenzy.entity;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.item.custom.BaitItem;
import net.vg.fishingfrenzy.item.custom.BaitProperties;
import net.vg.fishingfrenzy.item.custom.DeluxeFishingRodItem;
import net.vg.fishingfrenzy.mixin.FishingBobberAccessor;

import java.util.Iterator;
import java.util.List;

public class DeluxeFishingBobberEntity extends FishingBobberEntity {
    private final ItemStack baitStack;
    private final int luckBonus;

    public DeluxeFishingBobberEntity(EntityType<? extends FishingBobberEntity> type, World world, int luckBonus, int waitTimeReductionTicks, ItemStack baitStack) {
        super(type, world, luckBonus, waitTimeReductionTicks);
        this.baitStack = baitStack;
        this.luckBonus = luckBonus;
    }

    public DeluxeFishingBobberEntity(PlayerEntity thrower, World world, int luckBonus, int waitTimeReductionTicks, ItemStack baitStack) {
        super(thrower, world, luckBonus, waitTimeReductionTicks);
        this.baitStack = baitStack;
        this.luckBonus = luckBonus;
    }

    private boolean removeIfInvalid(PlayerEntity player) {
        ItemStack itemStack = player.getMainHandStack();
        ItemStack itemStack2 = player.getOffHandStack();
        boolean bl = itemStack.isOf(ModItems.DELUXE_FISHING_ROD);
        boolean bl2 = itemStack2.isOf(ModItems.DELUXE_FISHING_ROD);
        if (!player.isRemoved() && player.isAlive() && (bl || bl2) && !(this.squaredDistanceTo(player) > 1024.0)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }

    @Override
    public int use(ItemStack usedItem) {
        PlayerEntity playerEntity = this.getPlayerOwner();
        if (this.getWorld().isClient || playerEntity == null || this.removeIfInvalid(playerEntity)) {
            return 0;
        }
        int i = 0;
        int hookCountdown = ((FishingBobberAccessor) this).getHookCountdown();
        if (this.getHookedEntity() != null) {
            this.pullHookedEntity(this.getHookedEntity());
            i = this.getHookedEntity() instanceof ItemEntity ? 3 : 5;
        } else if(hookCountdown > 0) {
            System.out.println("luckBonus: " + this.luckBonus + ", playerLuck: " + playerEntity.getLuck());
            LootContextParameterSet lootContextParameterSet = (new LootContextParameterSet.Builder((ServerWorld)this.getWorld()))
                    .add(LootContextParameters.ORIGIN, this.getPos())
                    .add(LootContextParameters.TOOL, usedItem)
                    .add(LootContextParameters.THIS_ENTITY, this)
                    .luck((float)this.luckBonus + playerEntity.getLuck())
                    .build(LootContextTypes.FISHING);
            LootTable lootTable = this.getWorld().getServer().getReloadableRegistries().getLootTable(LootTables.FISHING_GAMEPLAY);

            if (!baitStack.isEmpty() && baitStack.getItem() instanceof BaitItem) {
                BaitProperties baitProperties = (BaitProperties) baitStack.getItem();
                lootTable = this.getWorld().getServer().getReloadableRegistries().getLootTable(baitProperties.getLootTable());
            }
            List<ItemStack> list = lootTable.generateLoot(lootContextParameterSet);
            Criteria.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity)playerEntity, usedItem, this, list);
            Iterator var7 = list.iterator();

            while(var7.hasNext()) {
                    ItemStack itemStack = (ItemStack)var7.next();
                    double d = playerEntity.getX() - this.getX();
                    double e = playerEntity.getY() - this.getY();
                    double f = playerEntity.getZ() - this.getZ();
                    double g = 0.1;

                    int catches = 1;
                    int caught = 0;
                    float multiCatchChance = 0.0f; // Default chance

                    if (!baitStack.isEmpty() &&  baitStack.getItem() instanceof BaitItem) {
                        BaitProperties baitProperties = ((BaitProperties) baitStack.getItem());
                        catches += baitProperties.getMultiCatchAmount();
                        multiCatchChance = baitProperties.getMultiCatchChance();
                    }

                    for (int l = 0; l < catches; l++) {
                        // Ensure the first spawn always happens
                        if (l == 0 || Math.random() < multiCatchChance) {
                            ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), itemStack);
                            itemEntity.setVelocity(d * 0.1, e * 0.1 + Math.sqrt(Math.sqrt(d * d + e * e + f * f)) * 0.08, f * 0.1);
                            this.getWorld().spawnEntity(itemEntity);
                            caught++;
                        }
                    }


                    // Experience
                    playerEntity.getWorld().spawnEntity(new ExperienceOrbEntity(playerEntity.getWorld(), playerEntity.getX(), playerEntity.getY() + 0.5, playerEntity.getZ() + 0.5, this.random.nextInt(6) + 1));
                    // Decrease bait stack in the bundle
                    if (!baitStack.isEmpty()) {
                        DeluxeFishingRodItem fishingRod = (DeluxeFishingRodItem) usedItem.getItem();
                        fishingRod.decrementBaitInBundle(usedItem);
                    }

                    if (itemStack.isIn(ItemTags.FISHES)) {
                        playerEntity.increaseStat(Stats.FISH_CAUGHT, caught);
                    }
                }
            i = 1;
        }
        if (this.isOnGround()) {
            i = 2;
        }
        this.discard();
        return i;
    }


}
