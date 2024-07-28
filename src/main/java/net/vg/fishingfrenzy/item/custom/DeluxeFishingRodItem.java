package net.vg.fishingfrenzy.item.custom;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.vg.fishingfrenzy.entity.DeluxeFishingBobberEntity;

public class DeluxeFishingRodItem extends FishingRodItem {
    public DeluxeFishingRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack rodStack = user.getStackInHand(hand);
        ItemStack baitStack = findBait(user);

        // Check if the player already has a fishing bobber in the world
        if (user.fishHook != null) {
            if (!world.isClient) {
                // Apply damage to the fishing rod based on the action
                int i = user.fishHook.use(rodStack);
                rodStack.damage(i, user, LivingEntity.getSlotForHand(hand));

                // Consume one bait item if available
//                if (!baitStack.isEmpty()) {
//                    baitStack.decrement(1);
//                }
            }

            // Play sound for retrieving the bobber
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            // Play sound for throwing the bobber
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                int lureLevel = (int)(EnchantmentHelper.getFishingTimeReduction(serverWorld, rodStack, user) * 20.0F);
                int luckLevel = EnchantmentHelper.getFishingLuckBonus(serverWorld, rodStack, user);
                int baitBonus = !baitStack.isEmpty() ? 200 : 0; // Increase catch rate with bait

                // Spawn the fishing bobber entity
                FishingBobberEntity fishingBobberEntity = new DeluxeFishingBobberEntity(user, serverWorld, luckLevel, lureLevel  + baitBonus, baitStack);
                serverWorld.spawnEntity(fishingBobberEntity);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return TypedActionResult.success(rodStack, world.isClient());
    }
//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        ItemStack rodStack = user.getStackInHand(hand);
//        ItemStack baitStack = findBait(user);
//
//        // Check if the player already has a fishing bobber in the world
//        if (user.fishHook != null) {
//            if (!world.isClient) {
//                // Apply damage to the fishing rod based on the action
//                int i = user.fishHook.use(rodStack);
//                rodStack.damage(i, user, LivingEntity.getSlotForHand(hand));
//
//                // Consume one bait item if available
//                if (!baitStack.isEmpty()) {
//                    baitStack.decrement(1);
//                }
//            }
//
//            // Play sound for retrieving the bobber
//            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
//            user.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
//        } else {
//            // Play sound for throwing the bobber
//            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
//            if (world instanceof ServerWorld) {
//                ServerWorld serverWorld = (ServerWorld) world;
//                int lureLevel = (int)(EnchantmentHelper.getFishingTimeReduction(serverWorld, rodStack, user) * 20.0F);
//                int luckLevel = EnchantmentHelper.getFishingLuckBonus(serverWorld, rodStack, user);
//                int baitBonus = !baitStack.isEmpty() ? 2 : 0; // Increase catch rate with bait
//                FishingBobberEntity fishingBobberEntity = new FishingBobberEntity(user, world, luckLevel + baitBonus, lureLevel);
//                world.spawnEntity(fishingBobberEntity);
//            }
//
//            user.incrementStat(Stats.USED.getOrCreateStat(this));
//            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
//        }
//
//        return TypedActionResult.success(rodStack, world.isClient());
//    }

    // Find bait item in the player's inventory
    private ItemStack findBait(PlayerEntity player) {
        for (int i = 0; i < player.getInventory().size(); ++i) {
            ItemStack itemStack = player.getInventory().getStack(i);
            if (isBait(itemStack)) {
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }

    // Check if the given stack is a bait item
    private boolean isBait(ItemStack stack) {
        return stack.getItem() instanceof BaitItem;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
