package net.vg.fishingfrenzy.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.vg.fishingfrenzy.entity.custom.AlbacoreEntity;
import net.vg.fishingfrenzy.entity.custom.BonefishEntity;
import net.vg.fishingfrenzy.entity.custom.CarpEntity;
import net.vg.fishingfrenzy.entity.custom.DiscusEntity;

public class ModEntityInitializer {
    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(ModEntities.CARP, CarpEntity.createCarpAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.BONEFISH, BonefishEntity.createBonefishAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.ALBACORE, AlbacoreEntity.createAlbacoreAttributes());

        FabricDefaultAttributeRegistry.register(ModEntities.DISCUS, DiscusEntity.createDiscusAttributes());
    }
}
