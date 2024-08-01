package net.vg.fishingfrenzy.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;

public class ModModelLayers {
    public static final EntityModelLayer CARP =
            new EntityModelLayer(Identifier.of(FishingFrenzy.MOD_ID, "carp"), "main");

    public static final EntityModelLayer BONEFISH =
            new EntityModelLayer(Identifier.of(FishingFrenzy.MOD_ID, "bonefish"), "main");

}
