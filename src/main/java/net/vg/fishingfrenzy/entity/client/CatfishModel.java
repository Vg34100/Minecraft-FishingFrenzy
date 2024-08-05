package net.vg.fishingfrenzy.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.vg.fishingfrenzy.management.CustomBreedableSchoolingFishEntity;

// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class CatfishModel extends SinglePartEntityModel<CustomBreedableSchoolingFishEntity> {
	private final ModelPart catfish;
	private final ModelPart head;

	public CatfishModel(ModelPart root) {
		this.catfish = root.getChild("catfish");
		this.head = catfish.getChild("main").getChild("head");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData catfish = modelPartData.addChild("catfish", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 22.0F, 0.0F));

		ModelPartData main = catfish.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = main.addChild("head", ModelPartBuilder.create().uv(15, 0).cuboid(-2.0F, -6.0F, -6.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head_antenna = head.addChild("head_antenna", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, -2.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(-2.0F, 0.0F, -2.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -7.0F, -5.0F, -0.1745F, 0.0F, 0.0F));

		ModelPartData bottom_antenna = head.addChild("bottom_antenna", ModelPartBuilder.create().uv(4, 3).cuboid(-2.0F, -1.0F, -2.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 0).cuboid(-2.0F, 0.0F, -1.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -1.0F, -4.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData whiskers = head.addChild("whiskers", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.5F));

		ModelPartData bottom_left_whisker = whiskers.addChild("bottom_left_whisker", ModelPartBuilder.create().uv(0, 15).cuboid(-4.0F, 1.5F, -0.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(15, 11).cuboid(-3.0F, 0.5F, -0.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.5F, -6.0F, 0.0F, -0.2618F, 0.0F));

		ModelPartData top_left_whisker = whiskers.addChild("top_left_whisker", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -2.5F, -0.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 1).cuboid(-3.0F, -1.5F, -0.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.5F, -6.0F, 0.0F, -0.2618F, 0.0F));

		ModelPartData bottom_right_whisker = whiskers.addChild("bottom_right_whisker", ModelPartBuilder.create().uv(15, 13).cuboid(1.0F, 0.5F, -0.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(15, 12).cuboid(2.0F, 1.5F, -0.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.5F, -6.0F, 0.0F, 0.2618F, 0.0F));

		ModelPartData top_right_whisker = whiskers.addChild("top_right_whisker", ModelPartBuilder.create().uv(0, 14).cuboid(1.0F, -1.5F, -0.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
		.uv(0, 13).cuboid(2.0F, -2.5F, -0.2F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.5F, -6.0F, 0.0F, 0.2618F, 0.0F));

		ModelPartData torso = main.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -6.0F, -3.0F, 4.0F, 4.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 11).cuboid(-1.0F, -7.0F, -3.0F, 2.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -8.0F, -1.0F, 0.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(15, 15).cuboid(2.0F, -5.0F, -1.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
		.uv(10, 11).cuboid(-3.0F, -5.0F, -1.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tail = main.addChild("tail", ModelPartBuilder.create().uv(0, 9).cuboid(1.0F, -7.0F, 4.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(4, 10).cuboid(1.0F, -5.0F, 4.0F, 0.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 2).cuboid(1.0F, -3.0F, 4.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -10.0f, 10.0f);
		headPitch = MathHelper.clamp(headPitch, -5.0f, 25.0f);

		this.head.yaw = headYaw * 0.017456292F;
		this.head.pitch = headPitch * 0.017456292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		catfish.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return catfish;
	}

	@Override
	public void setAngles(CustomBreedableSchoolingFishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);
	}
}