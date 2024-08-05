package net.vg.fishingfrenzy.management;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.entity.mob.BreedableSchoolingFishEntity;
import net.vg.fishingfrenzy.item.custom.FishRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicFishSystem {

    public static void registerFish(FishRegistry fishRegistry) {
        if (fishRegistry.hasFishEntityType()) {
            EntityType<CustomBreedableSchoolingFishEntity> entityType = fishRegistry.getFishEntityType();

            // Register attributes
            FishingFrenzy.LOGGER.info(entityType.toString());
//            FabricDefaultAttributeRegistry.register(entityType, createFishAttributes());


            // Client Initializer EntityRendererRegistry  & EntityModelLayerRegistry
            // Register model layer
            // Register renderer
            EntityRendererRegistry.register(entityType, (context) -> new FishRenderer(context, fishRegistry));
            EntityModelLayerRegistry.registerModelLayer(fishRegistry.getModelLayer(), () -> {
                try {
                    Method method = fishRegistry.getModelClass().getMethod("getTexturedModelData");
                    return (TexturedModelData) method.invoke(null);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to get model data for " + fishRegistry.getFishName(), e);
                }
            });


        }

    }

//
//
//    private static DefaultAttributeContainer.Builder createFishAttributes() {
//        return MobEntity.createMobAttributes()
//                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
//                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 2);
//    }

    private static class FishRenderer extends MobEntityRenderer<CustomBreedableSchoolingFishEntity, EntityModel<CustomBreedableSchoolingFishEntity>> {
        private final Identifier texture;
        private final FishRegistry fishRegistry;

        public FishRenderer(EntityRendererFactory.Context context, FishRegistry fishRegistry) {
            super(context, createModel(context, fishRegistry), 0.6f);
            this.fishRegistry = fishRegistry;
            this.texture = Identifier.of(FishingFrenzy.MOD_ID, "textures/entity/" + fishRegistry.getFishName() + ".png");
        }

        private static EntityModel<CustomBreedableSchoolingFishEntity> createModel(EntityRendererFactory.Context context, FishRegistry fishRegistry) {
            try {
                Constructor<? extends EntityModel<CustomBreedableSchoolingFishEntity>> constructor =
                        fishRegistry.getModelClass().getConstructor(ModelPart.class);
                return constructor.newInstance(context.getPart(fishRegistry.getModelLayer()));
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Failed to create model for " + fishRegistry.getFishName(), e);
            }
        }

        @Override
        public Identifier getTexture(CustomBreedableSchoolingFishEntity entity) {
            return texture;
        }

        @Override
        public void render(CustomBreedableSchoolingFishEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
            if (livingEntity.isBaby()) {
                matrixStack.scale((float) (1f * fishRegistry.getBabySizeMultiplier()), (float) (1f * fishRegistry.getBabySizeMultiplier()), (float) (1f * fishRegistry.getBabySizeMultiplier()));
            } else {
                matrixStack.scale(1f, 1f, 1f);
            }
            super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
        }
    }
}
