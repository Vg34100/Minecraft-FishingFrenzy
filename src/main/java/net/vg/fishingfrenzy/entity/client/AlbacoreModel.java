package net.vg.fishingfrenzy.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.vg.fishingfrenzy.entity.animation.ModAnimations;
import net.vg.fishingfrenzy.entity.custom.AlbacoreEntity;
import net.vg.fishingfrenzy.entity.custom.BonefishEntity;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class AlbacoreModel<T extends AlbacoreEntity> extends SinglePartEntityModel<T> {
    private final ModelPart albacore;
    private final ModelPart head;

    public AlbacoreModel(ModelPart root) {
        this.albacore = root.getChild("albacore");
        this.head = albacore.getChild("main").getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData albacore = modelPartData.addChild("albacore", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData main = albacore.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -2.0F));

        ModelPartData head = main.addChild("head", ModelPartBuilder.create().uv(0, 11).cuboid(-1.0F, -7.0F, -5.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                .uv(7, 11).cuboid(-1.0F, -7.0F, -6.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(16, 11).cuboid(-1.0F, -4.0F, -6.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData torso = main.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -7.0F, -2.0F, 2.0F, 4.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 10).cuboid(0.0F, -10.0F, 1.0F, 0.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-1.0F, -6.0F, 5.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 11).cuboid(-2.0F, -6.0F, -1.0F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F))
                .uv(11, 0).cuboid(1.0F, -6.0F, -1.0F, 1.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData topfin = torso.addChild("topfin", ModelPartBuilder.create().uv(11, 0).cuboid(0.0F, -1.0F, -5.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(11, 0).cuboid(0.0F, -2.0F, -4.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 10).cuboid(0.0F, -3.0F, -3.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 5).cuboid(0.0F, -1.0F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -7.0F, 3.0F));

        ModelPartData bottomfin = torso.addChild("bottomfin", ModelPartBuilder.create().uv(4, 4).cuboid(0.0F, -3.0F, -1.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(0.0F, -3.0F, 0.0F, 0.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tail = main.addChild("tail", ModelPartBuilder.create().uv(0, 3).cuboid(0.0F, -6.0F, 6.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(10, 12).cuboid(0.0F, -7.0F, 7.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(11, 4).cuboid(0.0F, -4.0F, 7.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public ModelPart getPart() {
        return albacore;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        albacore.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public void setAngles(AlbacoreEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        //this.animateMovement(ModAnimations.BONEFISH_IDLE, limbSwing, limbSwingAmount, 2f, 2.5f);
        //this.updateAnimation(entity.idleAnimationState, ModAnimations.BONEFISH_IDLE, ageInTicks, 1f);

    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
        headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

        this.head.yaw = headYaw * 0.017456292F;
        this.head.pitch = headPitch * 0.017456292F;

    }
}