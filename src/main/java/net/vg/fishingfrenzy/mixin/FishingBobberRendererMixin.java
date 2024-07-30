package net.vg.fishingfrenzy.mixin;

import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.vg.fishingfrenzy.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FishingBobberEntityRenderer.class)
public class FishingBobberRendererMixin {
    @Redirect(method = "getHandPos(Lnet/minecraft/entity/player/PlayerEntity;FF)Lnet/minecraft/util/math/Vec3d;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean redirectIsFishingRod(ItemStack instance, Item item) {
        // Check if the item stack is either a vanilla fishing rod or your custom deluxe fishing rod
        return instance.isOf(Items.FISHING_ROD) || instance.isOf(ModItems.DELUXE_FISHING_ROD);
    }
}
