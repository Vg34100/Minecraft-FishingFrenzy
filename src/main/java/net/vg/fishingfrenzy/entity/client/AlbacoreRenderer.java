package net.vg.fishingfrenzy.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.entity.client.AlbacoreModel;
import net.vg.fishingfrenzy.entity.client.BonefishModel;
import net.vg.fishingfrenzy.entity.client.ModModelLayers;
import net.vg.fishingfrenzy.entity.custom.AlbacoreEntity;
import net.vg.fishingfrenzy.entity.custom.BonefishEntity;

public class AlbacoreRenderer extends MobEntityRenderer<AlbacoreEntity, AlbacoreModel<AlbacoreEntity>> {
    private static final Identifier TEXTURE = Identifier.of(FishingFrenzy.MOD_ID, "textures/entity/albacore.png");

    public AlbacoreRenderer(EntityRendererFactory.Context context) {
        super(context, new AlbacoreModel(context.getPart(ModModelLayers.ALBACORE)), 0.6f);
    }

    @Override
    public Identifier getTexture(AlbacoreEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(AlbacoreEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
