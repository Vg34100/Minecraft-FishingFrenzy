package net.vg.fishingfrenzy.item.custom;

import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.tooltip.BundleTooltipData;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.vg.fishingfrenzy.entity.DeluxeFishingBobberEntity;
import net.vg.fishingfrenzy.item.ModItems;
import org.apache.commons.lang3.math.Fraction;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class DeluxeFishingRodItem extends FishingRodItem {
    private static final int ITEM_BAR_COLOR = MathHelper.packRgb(0.4F, 0.4F, 1.0F);

    public DeluxeFishingRodItem(Settings settings) {
        super(settings);
    }


    public static float getAmountFilled(ItemStack stack) {
        BundleContentsComponent bundleContentsComponent = (BundleContentsComponent)stack.getOrDefault(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);
        return bundleContentsComponent.getOccupancy().floatValue();
    }

    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) {
            return false;
        } else {
            BundleContentsComponent bundleContentsComponent = (BundleContentsComponent)stack.get(DataComponentTypes.BUNDLE_CONTENTS);
            if (bundleContentsComponent == null) {
                return false;
            } else {
                ItemStack itemStack = slot.getStack();
                BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(bundleContentsComponent);
                if (itemStack.isEmpty()) {
                    ItemStack itemStack2 = builder.removeFirst();
                    if (itemStack2 != null) {
                        ItemStack itemStack3 = slot.insertStack(itemStack2);
                        builder.add(itemStack3);
                    }
                } else if (itemStack.getItem().canBeNested() && isBait(itemStack)) {
                    int i = builder.add(slot, player);
                    if (i > 0) {
                        // Item added successfully
                    }
                }

                stack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());
                return true;
            }
        }
    }

    public boolean isBundleBarVisible(ItemStack stack) {
        BundleContentsComponent bundleContentsComponent = (BundleContentsComponent)stack.getOrDefault(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);
        return bundleContentsComponent.getOccupancy().compareTo(Fraction.ZERO) > 0;
    }

    public int getBundleBarStep(ItemStack stack) {
        BundleContentsComponent bundleContentsComponent = (BundleContentsComponent)stack.getOrDefault(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);
        return Math.min(1 + MathHelper.multiplyFraction(bundleContentsComponent.getOccupancy(), 12), 13);
    }

    public int getBundleBarColor(ItemStack stack) {
        return ITEM_BAR_COLOR;
    }

    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT && slot.canTakePartial(player)) {
            BundleContentsComponent bundleContentsComponent = (BundleContentsComponent)stack.get(DataComponentTypes.BUNDLE_CONTENTS);
            if (bundleContentsComponent == null) {
                return false;
            } else {
                BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(bundleContentsComponent);
                if (otherStack.isEmpty()) {
                    ItemStack itemStack = builder.removeFirst();
                    if (itemStack != null) {
                        cursorStackReference.set(itemStack);
                    }
                } else if (isBait(otherStack)) {
                    int i = builder.add(otherStack);
                    if (i > 0) {
                        // Item added successfully
                    }
                }

                stack.set(DataComponentTypes.BUNDLE_CONTENTS, builder.build());
                return true;
            }
        } else {
            return false;
        }
    }

//    public Optional<TooltipData> getTooltipData(ItemStack stack) {
//        return !stack.contains(DataComponentTypes.HIDE_TOOLTIP) && !stack.contains(DataComponentTypes.HIDE_ADDITIONAL_TOOLTIP) ? Optional.ofNullable((BundleContentsComponent)stack.get(DataComponentTypes.BUNDLE_CONTENTS)).map(BundleTooltipData::new) : Optional.empty();
//    }

//    public Optional<TooltipData> getTooltipData(ItemStack stack) {
//        return !stack.contains(DataComponentTypes.HIDE_TOOLTIP) &&
//                !stack.contains(DataComponentTypes.HIDE_ADDITIONAL_TOOLTIP) ?
//                Optional.ofNullable((BundleContentsComponent)stack
//                        .get(DataComponentTypes.BUNDLE_CONTENTS))
//                        .map(BundleTooltipData::new) : Optional.empty();
//    }
//
//    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
//        BundleContentsComponent bundleContentsComponent = (BundleContentsComponent)stack.get(DataComponentTypes.BUNDLE_CONTENTS);
//        if (bundleContentsComponent != null) {
//            int i = MathHelper.multiplyFraction(bundleContentsComponent.getOccupancy(), 64);
////            tooltip.add(Text.translatable("item.minecraft.bundle.fullness", new Object[]{i, 64}).formatted(Formatting.GRAY));
//        }
//
//    }

    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        if (!stack.contains(DataComponentTypes.HIDE_TOOLTIP) && !stack.contains(DataComponentTypes.HIDE_ADDITIONAL_TOOLTIP)) {
            BundleContentsComponent bundleContentsComponent = (BundleContentsComponent) stack.get(DataComponentTypes.BUNDLE_CONTENTS);
            if (bundleContentsComponent != null && !bundleContentsComponent.isEmpty()) {
                List<ItemStack> nonEmptyStacks = new ArrayList<>();
                for (ItemStack itemStack : bundleContentsComponent.iterate()) {
                    if (!itemStack.isEmpty()) {
                        nonEmptyStacks.add(itemStack);
                    }
                }
                return Optional.of(new BundleTooltipData(new BundleContentsComponent(nonEmptyStacks)));
            }
        }
        return Optional.empty();
    }

    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        BundleContentsComponent bundleContentsComponent = (BundleContentsComponent)stack.get(DataComponentTypes.BUNDLE_CONTENTS);
        if (bundleContentsComponent != null) {
            int i = MathHelper.multiplyFraction(bundleContentsComponent.getOccupancy(), 64);
            if (i > 0) {
                tooltip.add(Text.translatable("item.minecraft.bundle.fullness", new Object[]{i, 64}).formatted(Formatting.GRAY));
            }
        }
    }


    public void onItemEntityDestroyed(ItemEntity entity) {
        BundleContentsComponent bundleContentsComponent = (BundleContentsComponent)entity.getStack().get(DataComponentTypes.BUNDLE_CONTENTS);
        if (bundleContentsComponent != null) {
            entity.getStack().set(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);
            ItemUsage.spawnItemContents(entity, bundleContentsComponent.iterateCopy());
        }
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack rodStack = user.getStackInHand(hand);
        ItemStack baitStack = findBaitInBundle(rodStack);

        // Check if the player already has a fishing bobber in the world
        if (user.fishHook != null) {
            if (!world.isClient) {
                // Apply damage to the fishing rod based on the action
                int i = user.fishHook.use(rodStack);
                System.out.println("DAMAGE?");
                rodStack.damage(i, user, LivingEntity.getSlotForHand(hand));
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
//                int baitBonus = !baitStack.isEmpty() ? 200 : 0; // Increase catch rate with bait

                if (!baitStack.isEmpty()) {
                    BaitProperties baitProperties = (BaitProperties) baitStack.getItem();
                    luckLevel += baitProperties.getLuckBonus();
                    lureLevel += baitProperties.getLureBonus();
                }


                // Spawn the fishing bobber entity
                FishingBobberEntity fishingBobberEntity = new DeluxeFishingBobberEntity(user, serverWorld, luckLevel, lureLevel, baitStack);
                serverWorld.spawnEntity(fishingBobberEntity);
            }

            user.incrementStat(Stats.USED.getOrCreateStat(this));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return TypedActionResult.success(rodStack, world.isClient());
    }

    // Find bait item in the BundleContentsComponent
    private ItemStack findBaitInBundle(ItemStack rodStack) {
        BundleContentsComponent bundleContentsComponent = (BundleContentsComponent) rodStack.getOrDefault(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);
        if (bundleContentsComponent.isEmpty()) {
            return ItemStack.EMPTY;
        }
        for (ItemStack itemStack : bundleContentsComponent.iterate()) {
            if (isBait(itemStack)) {
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }

    // Decrease bait item in the BundleContentsComponent
    public void decrementBaitInBundle(ItemStack rodStack) {
        BundleContentsComponent bundleContentsComponent = (BundleContentsComponent) rodStack.getOrDefault(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);
        List<ItemStack> originalStacks = (List<ItemStack>) bundleContentsComponent.iterate();
        List<ItemStack> stacks = new ArrayList<>(originalStacks);
        Random random = new Random();

        boolean modified = false;

        for (ItemStack itemStack : stacks) {
            if (isBait(itemStack)) {
                BaitProperties baitProperties = (BaitProperties) itemStack.getItem();
                if (random.nextFloat() <= baitProperties.getUseChance()) {
                    if (itemStack.getCount() > 1) {
                        itemStack.decrement(1);
                    } else {
                        System.out.println("Only One Bait");
                        stacks.remove(itemStack);
                    }
                    modified = true;
                    break;
                }
            }
        }

        if (modified) {
            if (stacks.isEmpty()) {
                rodStack.set(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);
            } else {
                rodStack.set(DataComponentTypes.BUNDLE_CONTENTS, new BundleContentsComponent(stacks));
            }
        }
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
