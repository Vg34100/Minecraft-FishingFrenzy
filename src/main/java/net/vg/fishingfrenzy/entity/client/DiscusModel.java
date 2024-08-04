package net.vg.fishingfrenzy.entity.client;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.vg.fishingfrenzy.entity.custom.DiscusEntity;

public class DiscusModel<T extends DiscusEntity> extends SinglePartEntityModel<T> {
	private final ModelPart blue_discus;
	private final ModelPart head;

	public DiscusModel(ModelPart root) {
		this.blue_discus = root.getChild("blue_discus");
		this.head = blue_discus.getChild("main").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData blue_discus = modelPartData.addChild("blue_discus", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 23.0F, 0.0F));

		ModelPartData main = blue_discus.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = main.addChild("head", ModelPartBuilder.create().uv(14, 6).cuboid(-1.0F, -5.0F, -6.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(10, 11).cuboid(-1.0F, -7.0F, -4.0F, 2.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(14, 0).cuboid(-1.0F, -6.0F, -5.0F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(9, 3).cuboid(0.0F, -8.0F, -4.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(9, 0).cuboid(0.0F, -1.0F, -4.0F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData torso = main.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -7.0F, -3.0F, 2.0F, 6.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 9).cuboid(0.0F, -8.0F, -3.0F, 0.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 6).cuboid(0.0F, -9.0F, -2.0F, 0.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 8).cuboid(0.0F, -1.0F, -3.0F, 0.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 7).cuboid(0.0F, 0.0F, -2.0F, 0.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.5F, -6.0F, 2.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData arms = torso.addChild("arms", ModelPartBuilder.create().uv(4, 16).cuboid(-4.0F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(-1.0F, -2.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -2.0F, -1.0F));

		ModelPartData tail = main.addChild("tail", ModelPartBuilder.create().uv(11, 0).cuboid(0.0F, -4.5F, 2.5F, 0.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = tail.addChild("cube_r1", ModelPartBuilder.create().uv(9, 0).cuboid(0.1F, 0.0F, 0.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, 3.0F, -0.4363F, 0.0F, 0.0F));

		ModelPartData cube_r2 = tail.addChild("cube_r2", ModelPartBuilder.create().uv(9, 1).cuboid(-0.1F, -1.0F, 0.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, 3.0F, 0.4363F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}



	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		blue_discus.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return blue_discus;
	}

	@Override
	public void setAngles(DiscusEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}