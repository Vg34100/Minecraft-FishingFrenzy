package net.vg.fishingfrenzy.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.vg.fishingfrenzy.entity.client.*;

public class ModEntityClientInitializer {

    public static void registerEntities() {
        EntityRendererRegistry.register(ModEntities.CARP, CarpRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CARP, CarpModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BONEFISH, BonefishRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BONEFISH, BonefishModel::getTexturedModelData);
    }
}
