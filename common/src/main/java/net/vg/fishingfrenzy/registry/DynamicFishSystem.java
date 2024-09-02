package net.vg.fishingfrenzy.registry;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.vg.fishingfrenzy.Constants;
import net.vg.fishingfrenzy.entity.mob.CustomBreedableSchoolingFishEntity;
import net.vg.fishingfrenzy.item.fish.FishRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class DynamicFishSystem {

    public static void registerFish(FishRegistry fishRegistry) {
        if (fishRegistry.hasFishEntityType()) {
            var entityTypeSupplier = fishRegistry.getFishEntityType();

            // Register attributes
            Constants.LOGGER.info(entityTypeSupplier.toString());
            EntityAttributeRegistry.register(entityTypeSupplier, CustomBreedableSchoolingFishEntity::createAttributes);

            // Register model layer and renderer
            EntityRendererRegistry.register(entityTypeSupplier, (context) -> new FishRenderer(context, fishRegistry));
            EntityModelLayerRegistry.register(fishRegistry.getModelLayer(), () -> {
                try {
                    Method method = fishRegistry.getModelClass().getMethod("createBodyLayer");
                    return (LayerDefinition) method.invoke(null);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to get model data for " + fishRegistry.getFishName(), e);
                }
            });
        }
    }

    private static class FishRenderer extends MobRenderer<CustomBreedableSchoolingFishEntity, EntityModel<CustomBreedableSchoolingFishEntity>> {
        private final ResourceLocation texture;
        private final FishRegistry fishRegistry;

        public FishRenderer(EntityRendererProvider.Context context, FishRegistry fishRegistry) {
            super(context, createModel(context, fishRegistry), 0.6f);
            this.fishRegistry = fishRegistry;
            this.texture = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/entity/" + fishRegistry.getFishName() + ".png");
        }

        private static EntityModel<CustomBreedableSchoolingFishEntity> createModel(EntityRendererProvider.Context context, FishRegistry fishRegistry) {
            try {
                Constructor<? extends EntityModel<CustomBreedableSchoolingFishEntity>> constructor =
                        fishRegistry.getModelClass().getConstructor(ModelPart.class);
                return constructor.newInstance(context.bakeLayer(fishRegistry.getModelLayer()));
            } catch (Exception e) {
                throw new RuntimeException("Failed to create model for " + fishRegistry.getFishName(), e);
            }
        }

        @Override
        public ResourceLocation getTextureLocation(CustomBreedableSchoolingFishEntity entity) {
            return texture;
        }

        @Override
        public void render(CustomBreedableSchoolingFishEntity livingEntity, float f, float g, PoseStack poseStack, net.minecraft.client.renderer.MultiBufferSource multiBufferSource, int i) {
            if (livingEntity.isBaby()) {
                poseStack.scale((float) (1f * 0.5), (float) (1f * 0.5), (float) (1f * 0.5));
            } else {
                poseStack.scale(1f, 1f, 1f);
            }
            super.render(livingEntity, f, g, poseStack, multiBufferSource, i);
        }
    }
}