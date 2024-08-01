package net.vg.fishingfrenzy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.entity.ModEntities;
import net.vg.fishingfrenzy.entity.client.CarpModel;
import net.vg.fishingfrenzy.entity.client.CarpRenderer;
import net.vg.fishingfrenzy.entity.client.ModModelLayers;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.item.custom.CustomBaitItem;

public class FishingFrenzyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.CARP, CarpRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CARP, CarpModel::getTexturedModelData);

        // Render the colored texture
        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> ((CustomBaitItem) stack.getItem()).getColor(tintIndex), ModItems.TESTBAIT
        );

        // Render the casted texture
        ModelPredicateProviderRegistry.register(ModItems.DELUXE_FISHING_ROD, Identifier.of("cast"), (stack, world, entity, seed) -> {
            boolean bl2;
            if (entity == null) {
                return 0.0f;
            }
            boolean bl = entity.getMainHandStack() == stack;
            bl2 = entity.getOffHandStack() == stack;
            if (entity.getMainHandStack().getItem() instanceof FishingRodItem) {
                bl2 = false;
            }
            return (bl || bl2) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishHook != null ? 1.0f : 0.0f;
        });

    }
}
