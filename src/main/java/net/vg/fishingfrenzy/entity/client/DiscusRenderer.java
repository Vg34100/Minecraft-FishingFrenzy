package net.vg.fishingfrenzy.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.entity.custom.DiscusEntity;

public class DiscusRenderer extends MobEntityRenderer<DiscusEntity, DiscusModel<DiscusEntity>> {
    private static final Identifier TEXTURE = Identifier.of(FishingFrenzy.MOD_ID, "textures/entity/blue_discus.png");

    public DiscusRenderer(EntityRendererFactory.Context context) {
        super(context, new DiscusModel<>(context.getPart(ModModelLayers.DISCUS)), 0.6f);
    }

    @Override
    public Identifier getTexture(DiscusEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(DiscusEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
