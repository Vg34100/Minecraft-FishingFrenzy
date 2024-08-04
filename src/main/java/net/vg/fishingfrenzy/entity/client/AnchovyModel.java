package net.vg.fishingfrenzy.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class AnchovyModel extends SinglePartEntityModel<SchoolingFishEntity> {
	private final ModelPart anchovy;
	private final ModelPart head;

	public AnchovyModel(ModelPart root) {
		this.anchovy = root.getChild("anchovy");
		this.head = anchovy.getChild("main").getChild("head");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData anchovy = modelPartData.addChild("anchovy", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData main = anchovy.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = main.addChild("head", ModelPartBuilder.create().uv(0, 8).cuboid(-1.0F, -5.0F, -5.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData torso = main.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -5.0F, -2.0F, 2.0F, 3.0F, 5.0F, new Dilation(0.0F))
		.uv(7, 5).cuboid(0.0F, -6.0F, -2.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 1).cuboid(0.0F, -2.0F, -1.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tail = main.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = tail.addChild("cube_r1", ModelPartBuilder.create().uv(0, 7).cuboid(0.99F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.0F, 4.0F, 0.8727F, 0.0F, 0.0F));

		ModelPartData cube_r2 = tail.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(1.01F, -1.0F, -1.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -4.0F, 4.0F, 0.8727F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 16, 16);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
		headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

		this.head.yaw = headYaw * 0.017456292F;
		this.head.pitch = headPitch * 0.017456292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		anchovy.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return anchovy;
	}

	@Override
	public void setAngles(SchoolingFishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);
	}
}