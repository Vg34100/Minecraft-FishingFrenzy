package net.vg.fishingfrenzy.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.vg.fishingfrenzy.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberValidityMixin extends Entity {

    public FishingBobberValidityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
            method = "removeIfInvalid",
            at = @At("HEAD"),
            cancellable = true
    )
    private void validateFishingRod(PlayerEntity playerEntity, CallbackInfoReturnable<Boolean> cir) {
        ItemStack mainHandStack = playerEntity.getMainHandStack();
        ItemStack offHandStack = playerEntity.getOffHandStack();

        // Check if the main hand or offhand has a valid fishing rod
        boolean mainHandHasValidRod = isValidFishingRod(mainHandStack);
        boolean offHandHasValidRod = isValidFishingRod(offHandStack);


        // Validate if the player is alive, not removed, has a valid rod, and is within a certain distance
        if (!playerEntity.isRemoved() && playerEntity.isAlive() && (mainHandHasValidRod || offHandHasValidRod) && this.squaredDistanceTo(playerEntity) <= 1024.0D) {
            cir.setReturnValue(false); // Prevent the bobber from being removed
        }
    }

    @Unique
    private static boolean isValidFishingRod(ItemStack stack) {
        return stack.isOf(Items.FISHING_ROD) || ModItems.FISHING_RODS.contains(stack.getItem());
    }
}
