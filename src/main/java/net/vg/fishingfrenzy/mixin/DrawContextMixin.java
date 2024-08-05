package net.vg.fishingfrenzy.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.math.MathHelper;
import net.vg.fishingfrenzy.item.custom.DeluxeFishingRodItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrawContext.class)
public abstract class DrawContextMixin {

    @Shadow @Final private MinecraftClient client;

    @Shadow public abstract void fill(RenderLayer guiOverlay, int x1, int y1, int x2, int y2, int color);

    @Shadow public abstract int drawText(TextRenderer textRenderer, Text text, int x, int y, int color, boolean shadow);

    @Inject(method = "drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    private void drawCustomItemInSlot(TextRenderer textRenderer, ItemStack stack, int x, int y, @Nullable String countOverride, CallbackInfo ci) {
        if (!stack.isEmpty() && stack.getItem() instanceof DeluxeFishingRodItem item) {
            MatrixStack matrices = ((DrawContext)(Object)this).getMatrices();

            matrices.push();
            if (stack.getCount() != 1 || countOverride != null) {
                String string = countOverride == null ? String.valueOf(stack.getCount()) : countOverride;
                matrices.translate(0.0F, 0.0F, 200.0F);
                this.drawText(textRenderer, Text.of(string), x + 19 - 2 - textRenderer.getWidth(string), y + 6 + 3, 16777215, true);
            }

            if (item.isItemBarVisible(stack)) {
                int durabilityStep = item.getItemBarStep(stack);
                int durabilityColor = item.getItemBarColor(stack);


                int barX = x + 2;
                int barY = y + 13;

//                 Draw durability bar
                this.fill(RenderLayer.getGuiOverlay(), barX, barY, barX + 13, barY + 2, Colors.BLACK);
                this.fill(RenderLayer.getGuiOverlay(), barX, barY, barX + durabilityStep, barY + 1, durabilityColor | Colors.BLACK);
            }

            if (item.isBundleBarVisible(stack)) {
                int bundleStep = item.getBundleBarStep(stack);
                int bundleColor = item.getBundleBarColor();
                int barX = x + 2;
                int barY = y + 13;

                // Draw bundle contents bar above durability bar
                this.fill(RenderLayer.getGuiOverlay(), barX, barY - 2, barX + 13, barY, Colors.BLACK);
                this.fill(RenderLayer.getGuiOverlay(), barX, barY - 2, barX + bundleStep, barY - 1, bundleColor | Colors.BLACK);

            }

            PlayerEntity clientPlayerEntity = client.player;
            float f = clientPlayerEntity == null ? 0.0F : clientPlayerEntity.getItemCooldownManager().getCooldownProgress(stack.getItem(), (client.getRenderTickCounter().getTickDelta(true)));
            if (f > 0.0F) {
                int cooldownStart = y + MathHelper.floor(16.0F * (1.0F - f));
                int cooldownEnd = cooldownStart + MathHelper.ceil(16.0F * f);
                this.fill(RenderLayer.getGuiOverlay(), x, cooldownStart, x + 16, cooldownEnd, Integer.MAX_VALUE);
            }

            matrices.pop();
            ci.cancel();
        }
    }
}
