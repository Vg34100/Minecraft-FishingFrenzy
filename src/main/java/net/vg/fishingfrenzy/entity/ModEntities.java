package net.vg.fishingfrenzy.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.entity.custom.AlbacoreEntity;
import net.vg.fishingfrenzy.entity.custom.BonefishEntity;
import net.vg.fishingfrenzy.entity.custom.CarpEntity;

public class ModEntities {

    public static final EntityType<CarpEntity> CARP = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(FishingFrenzy.MOD_ID, "carp"),
            EntityType.Builder.create(CarpEntity::new, SpawnGroup.WATER_AMBIENT)
                    .dimensions(1f, 1f)
                    .build());

    public static final EntityType<BonefishEntity> BONEFISH = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(FishingFrenzy.MOD_ID, "bonefish"),
            EntityType.Builder.create(BonefishEntity::new, SpawnGroup.WATER_AMBIENT)
                    .dimensions(1f, 1f)
                    .build());

    public static final EntityType<AlbacoreEntity> ALBACORE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(FishingFrenzy.MOD_ID, "albacore"),
            EntityType.Builder.create(AlbacoreEntity::new, SpawnGroup.WATER_AMBIENT)
                    .dimensions(1f, 1f)
                    .build());




    public static void registerEntities() {
        // Ensure this method is called to register the entities
        FishingFrenzy.LOGGER.info("Registering Entities for " + FishingFrenzy.MOD_ID);
    }
}
