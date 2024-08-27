package net.vg.fishingfrenzy.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.vg.fishingfrenzy.item.DeluxeFishingRodItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(GuiGraphics.class)
public abstract class GuiGraphicsMixin {

    @Shadow public abstract PoseStack pose();

    @Shadow public abstract int drawString(Font arg, String string, int i, int j, int k, boolean bl);

    @Shadow public abstract void fill(RenderType arg, int i, int j, int k, int l, int m);

    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    private void drawCustomItemInSlot(Font font, ItemStack itemStack, int i, int j, String string, CallbackInfo ci) {
        if (!itemStack.isEmpty() && itemStack.getItem() instanceof DeluxeFishingRodItem item) {
            PoseStack matrices = this.pose();

            matrices.pushPose();
            if (itemStack.getCount() != 1 || string != null) {
                String string2 = string == null ? String.valueOf(itemStack.getCount()) : string;
                matrices.translate(0.0F, 0.0F, 200.0F);
                this.drawString(font, string2, i + 19 - 2 - font.width(string2), j + 6 + 3, 16777215, true);
            }


            if (itemStack.isBarVisible()) {
                int k = itemStack.getBarWidth();
                int l = itemStack.getBarColor();

                int barX = i + 2;
                int barY = j + 13;

                this.fill(RenderType.guiOverlay(), barX, barY, barX + 13, barY + 2, -16777216);
                this.fill(RenderType.guiOverlay(), barX, barY, barX + k, barY + 1, l | -16777216);
            }

            if (item.isBundleBarVisible(itemStack)) {
                int bundleStep = item.getBundleBarWidth(itemStack);
                int bundleColor = item.getBundleBarColor();

                int barX = i + 2;
                int barY = j + 13;

                // Draw bundle contents bar above durability bar
                this.fill(RenderType.guiOverlay(), barX, barY - 2, barX + 13, barY, Color.BLACK.getRGB());
                this.fill(RenderType.guiOverlay(), barX, barY - 2, barX + bundleStep, barY - 1, bundleColor | Color.BLACK.getRGB());

            }

            LocalPlayer localPlayer = this.minecraft.player;
            float f = localPlayer == null ? 0.0F : localPlayer.getCooldowns().getCooldownPercent(itemStack.getItem(), this.minecraft.getTimer().getGameTimeDeltaPartialTick(true));
            if (f > 0.0F) {
                int cooldownStart = j + Mth.floor(16.0F * (1.0F - f));
                int cooldownEnd = cooldownStart + Mth.ceil(16.0F * f);
                this.fill(RenderType.guiOverlay(), i, cooldownStart, i + 16, cooldownEnd, Integer.MAX_VALUE);
            }

            matrices.popPose();
            ci.cancel();
        }
    }
}
