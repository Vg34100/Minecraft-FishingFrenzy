package net.vg.fishingfrenzy.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.vg.fishingfrenzy.entity.DeluxeFishingHook;
import net.vg.fishingfrenzy.item.bait.BaitItem;
import net.vg.fishingfrenzy.item.bait.BaitProperties;
import org.apache.commons.lang3.math.Fraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class DeluxeFishingRodItem extends FishingRodItem {
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);
    private static final int TOOLTIP_MAX_WEIGHT = 64;

    public DeluxeFishingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack rodStack = pPlayer.getItemInHand(pHand);
        ItemStack baitStack = findBaitInBundle(rodStack);

        if (pPlayer.fishing != null) {
            if (!pLevel.isClientSide) {
                int i = pPlayer.fishing.retrieve(rodStack);
                rodStack.hurtAndBreak(i, pPlayer, LivingEntity.getSlotForHand(pHand));
            }

            pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            pPlayer.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
            if (pLevel instanceof ServerLevel serverlevel) {
                int lureLevel = (int)(EnchantmentHelper.getFishingTimeReduction(serverlevel, rodStack, pPlayer) * 20.0F);
                int luckLevel = EnchantmentHelper.getFishingLuckBonus(serverlevel, rodStack, pPlayer);

                if (!baitStack.isEmpty()) {
                    BaitProperties baitProperties = (BaitProperties) baitStack.getItem();
                    luckLevel += baitProperties.getLuckBonus();
                    lureLevel *= baitProperties.getLureBonus();
                }


                pLevel.addFreshEntity(new DeluxeFishingHook(pPlayer, pLevel, luckLevel, lureLevel, baitStack)); // add new bobber
            }

            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            pPlayer.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(rodStack, pLevel.isClientSide());

    }


    //    Bundle Component Properties
    public boolean overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player) {
        if (action != ClickAction.SECONDARY) {
            return false;
        } else {
            BundleContents bundleContents = (BundleContents)stack.get(DataComponents.BUNDLE_CONTENTS);
            if (bundleContents == null) {
                return false;
            } else {
                ItemStack itemStack = slot.getItem();
                BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
                if (itemStack.isEmpty()) {
                    this.playRemoveOneSound(player);
                    ItemStack itemStack2 = mutable.removeOne();
                    if (itemStack2 != null) {
                        ItemStack itemStack3 = slot.safeInsert(itemStack2);
                        mutable.tryInsert(itemStack3);
                    }
                } else if (itemStack.getItem().canFitInsideContainerItems() && isBait(itemStack)) {
                    int i = mutable.tryTransfer(slot, player);
                    if (i > 0) {
                        this.playInsertSound(player);
                    }
                }

                stack.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
                return true;
            }
        }
    }

    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
            BundleContents bundleContents = (BundleContents)stack.get(DataComponents.BUNDLE_CONTENTS);
            if (bundleContents == null) {
                return false;
            } else {
                BundleContents.Mutable mutable = new BundleContents.Mutable(bundleContents);
                if (other.isEmpty()) {
                    ItemStack itemStack = mutable.removeOne();
                    if (itemStack != null) {
                        this.playRemoveOneSound(player);
                        access.set(itemStack);
                    }
                } else if (isBait(other)) {
                    int i = mutable.tryInsert(other);
                    if (i > 0) {
                        this.playInsertSound(player);
                    }
                }

                stack.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
                return true;
            }
        } else {
            return false;
        }
    }

    // Bundle stuff
    private ItemStack findBaitInBundle(ItemStack rodStack) {
        BundleContents bundleContentsComponent = rodStack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        if (bundleContentsComponent.isEmpty()) {
            return ItemStack.EMPTY;
        }
        for (ItemStack itemStack : bundleContentsComponent.items()) {
            if (isBait(itemStack)) {
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }

    public void decrementBaitInBundle(ItemStack rodStack) {
        BundleContents bundleContentsComponent = rodStack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        List<ItemStack> originalStacks = (List<ItemStack>) bundleContentsComponent.items();
        List<ItemStack> stacks = new ArrayList<>(originalStacks);
        Random random = new Random();

        boolean modified = false;
        for (ItemStack itemStack : stacks) {
            if (isBait(itemStack)) {
                BaitProperties baitProperties = (BaitProperties) itemStack.getItem();
                if (random.nextFloat() <= baitProperties.getUseChance()) {
                    if (itemStack.getCount() > 1) {
                        itemStack.shrink(1);
                    } else {
                        stacks.remove(itemStack);
                    }
                    modified = true;
                }
                break;
            }
        }

        if (modified) {
            if (stacks.isEmpty()) {
                rodStack.set(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            } else {
                rodStack.set(DataComponents.BUNDLE_CONTENTS, new BundleContents(stacks));
            }
        }
    }


    public boolean isBundleBarVisible(ItemStack stack) {
        BundleContents bundleContents = (BundleContents)stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return bundleContents.weight().compareTo(Fraction.ZERO) > 0;
    }

    public int getBundleBarWidth(ItemStack stack) {
        BundleContents bundleContents = (BundleContents)stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return Math.min(1 + Mth.mulAndTruncate(bundleContents.weight(), 12), 13);
    }

    public int getBundleBarColor() {
        return BAR_COLOR;
    }

    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return !stack.has(DataComponents.HIDE_TOOLTIP) && !stack.has(DataComponents.HIDE_ADDITIONAL_TOOLTIP) ? Optional.ofNullable((BundleContents)stack.get(DataComponents.BUNDLE_CONTENTS)).map(BundleTooltip::new) : Optional.empty();
    }

    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        BundleContents bundleContents = (BundleContents)stack.get(DataComponents.BUNDLE_CONTENTS);
        if (bundleContents != null) {
            int i = Mth.mulAndTruncate(bundleContents.weight(), 64);
            tooltipComponents.add(Component.translatable("item.minecraft.bundle.fullness", new Object[]{i, 64}).withStyle(ChatFormatting.GRAY));
        }

    }

    public void onDestroyed(ItemEntity itemEntity) {
        BundleContents bundleContents = (BundleContents)itemEntity.getItem().get(DataComponents.BUNDLE_CONTENTS);
        if (bundleContents != null) {
            itemEntity.getItem().set(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            ItemUtils.onContainerDestroyed(itemEntity, bundleContents.itemsCopy());
        }
    }

    // Check if the given stack is a bait item
    private boolean isBait(ItemStack stack) {
        return stack.getItem() instanceof BaitItem; //* Change to BaitItem when created
    }

    //    Bundle Sounds
    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }
}
