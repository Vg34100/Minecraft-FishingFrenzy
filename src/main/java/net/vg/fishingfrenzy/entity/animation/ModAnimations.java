package net.vg.fishingfrenzy.entity.animation;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class ModAnimations {

    public static final Animation CARP_IDLE = Animation.Builder.create(1.0F)
            .addBoneAnimation("carp", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.2F, AnimationHelper.createRotationalVector(0.0F, -27.5F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.65F, AnimationHelper.createRotationalVector(0.0F, 10.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.95F, AnimationHelper.createRotationalVector(0.0F, -5.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.2F, AnimationHelper.createRotationalVector(0.0F, -15.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.55F, AnimationHelper.createRotationalVector(0.0F, 7.5F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.9F, AnimationHelper.createRotationalVector(0.0F, 2.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .build();

}
