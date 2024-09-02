package net.vg.fishingfrenzy.registry;

import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.vg.fishingfrenzy.Constants;
import net.vg.fishingfrenzy.entity.mob.CustomBreedableSchoolingFishEntity;
import net.vg.fishingfrenzy.item.fish.FishRegistry;

public class DynamicFishEntityGenerator {

    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Constants.MOD_ID, net.minecraft.core.registries.Registries.ENTITY_TYPE);

    public static RegistrySupplier<EntityType<CustomBreedableSchoolingFishEntity>> generateFishEntity(FishRegistry fishRegistry) {
        Constants.LOGGER.info("Registering dynamic entity for: {}", fishRegistry.getFishName());
        String entityName = fishRegistry.getFishName();

        RegistrySupplier<EntityType<CustomBreedableSchoolingFishEntity>> entityType = ENTITY_TYPES.register(entityName, () ->
                EntityType.Builder.of(
                                (EntityType<CustomBreedableSchoolingFishEntity> type, Level level) ->
                                        new CustomBreedableSchoolingFishEntity(type, level, fishRegistry) {
                                            @Override
                                            public ItemStack getBucketItemStack() {
                                                return super.getBucketItemStack();
                                            }
                                        },
                                MobCategory.WATER_AMBIENT
                        )
                        .sized(1f, 1f)
                        .build(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, entityName).toString())
        );

        // Register the entity attributes
//        EntityAttributeRegistry.register(entityType, () -> createFishAttributes(fishRegistry));
        Constants.LOGGER.info("Registered dynamic entity: {}", entityName);

        return entityType;
    }

    private static AttributeSupplier.Builder createFishAttributes(FishRegistry fishRegistry) {
        AttributeSupplier.Builder attributes = AbstractSchoolingFish.createAttributes()
                .add(Attributes.MAX_HEALTH, 3)
                .add(Attributes.MOVEMENT_SPEED, 1)
                .add(Attributes.ATTACK_DAMAGE, 1);

        return attributes;
    }

    public static void init() {
        ENTITY_TYPES.register();
    }
}