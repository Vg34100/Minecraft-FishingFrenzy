package net.vg.fishingfrenzy.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.entity.custom.BonefishEntity;

public class BonefishRenderer extends MobEntityRenderer<BonefishEntity, BonefishModel<BonefishEntity>> {
    private static final Identifier TEXTURE = Identifier.of(FishingFrenzy.MOD_ID, "textures/entity/bonefish.png");

    public BonefishRenderer(EntityRendererFactory.Context context) {
        super(context, new BonefishModel<>(context.getPart(ModModelLayers.BONEFISH)), 0.6f);
    }

    @Override
    public Identifier getTexture(BonefishEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(BonefishEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
