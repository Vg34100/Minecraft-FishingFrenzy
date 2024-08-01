package net.vg.fishingfrenzy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.vg.fishingfrenzy.entity.ModEntities;
import net.vg.fishingfrenzy.entity.ModEntityClientInitializer;
import net.vg.fishingfrenzy.entity.client.*;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.item.custom.CustomSpawnEggItem;
import net.vg.fishingfrenzy.item.custom.TargetBaitItem;

public class FishingFrenzyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModEntityClientInitializer.registerEntities();
//        EntityRendererRegistry.register(ModEntities.CARP, CarpRenderer::new);
//        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CARP, CarpModel::getTexturedModelData);
//
//        EntityRendererRegistry.register(ModEntities.BONEFISH, BonefishRenderer::new);
//        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BONEFISH, BonefishModel::getTexturedModelData);

        for (Item baitItem : ModItems.TARGETED_BAIT_ITEMS) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                    ColorHelper.Argb.fullAlpha(((TargetBaitItem)stack.getItem()).getColor(tintIndex)), baitItem);

        }

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
