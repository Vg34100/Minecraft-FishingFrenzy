package net.vg.fishingfrenzy.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.entity.custom.CarpEntity;

public class CarpRenderer extends MobEntityRenderer<CarpEntity, CarpModel<CarpEntity>> {
    private static final Identifier TEXTURE = Identifier.of(FishingFrenzy.MOD_ID, "textures/entity/carp.png");

    public CarpRenderer(EntityRendererFactory.Context context) {
        super(context, new CarpModel<>(context.getPart(ModModelLayers.CARP)), 0.6f);
    }

    @Override
    public Identifier getTexture(CarpEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(CarpEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
