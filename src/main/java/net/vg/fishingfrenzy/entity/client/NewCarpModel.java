package net.vg.fishingfrenzy.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.util.math.MathHelper;

// Model class definition
public class NewCarpModel extends SinglePartEntityModel<SchoolingFishEntity> {
	private final ModelPart carp;
	private final ModelPart head;

	public NewCarpModel(ModelPart root) {
		this.carp = root.getChild("carp");
		this.head = carp.getChild("body").getChild("head");
	}

	public static TexturedModelData getTexturedModelData() {
		// Model data initialization
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData carp = modelPartData.addChild("carp", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		// Body part initialization
		ModelPartData body = carp.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -4.0F, 0.0F));
		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 7).cuboid(3.0F, -5.0F, -1.0F, 3.0F, 4.0F, 2.0F, new Dilation(0.0F))
				.uv(10, 7).cuboid(6.0F, -4.0F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		// Torso initialization
		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -1.0F, 7.0F, 5.0F, 2.0F, new Dilation(0.0F))
				.uv(10, 10).cuboid(-4.0F, -5.0F, -1.0F, -1.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		// Fin initialization
		ModelPartData top_fin = torso.addChild("top_fin", ModelPartBuilder.create().uv(12, 12).cuboid(-2.0F, -2.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 13).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.0F, 1.0F));

		ModelPartData bottom_fin = torso.addChild("bottom_fin", ModelPartBuilder.create().uv(8, 8).cuboid(0.0F, -2.0F, -1.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
				.uv(4, 13).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 2.0F, 1.0F));

		ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(8, 7).cuboid(-7.0F, -3.0F, 0.0F, 2.0F, 1.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 8).cuboid(-7.0F, -4.0F, 0.0F, 1.0F, 1.0F, 0.0F)
				.uv(0, 7).cuboid(-7.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F)
				.uv(0, 1).cuboid(-8.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F)
				.uv(0, 0).cuboid(-8.0F, -5.0F, 0.0F, 1.0F, 1.0F, 0.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
		headPitch = MathHelper.clamp(headPitch, -25.0f, 45.0f);

		this.head.yaw = headYaw * 0.017456292F;
		this.head.pitch = headPitch * 0.017456292F;
	}

//	@Override
//	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
//		carp.render(matrices, vertices, light, overlay, red, green, blue, alpha);
//	}


	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		carp.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public ModelPart getPart() {
		return carp;
	}

	@Override
	public void setAngles(SchoolingFishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);
	}
}
