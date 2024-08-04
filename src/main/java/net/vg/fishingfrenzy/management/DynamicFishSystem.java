package net.vg.fishingfrenzy.management;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.item.custom.FishRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class DynamicFishSystem {

    public static void registerFish(FishRegistry fishRegistry) {
        EntityType<SchoolingFishEntity> entityType = fishRegistry.getFishEntityType();

        // Register attributes
        FabricDefaultAttributeRegistry.register(entityType, createFishAttributes(fishRegistry));

        // Register model layer
        EntityModelLayerRegistry.registerModelLayer(fishRegistry.getModelLayer(), () -> {
            try {
                Method method = fishRegistry.getModelClass().getMethod("getTexturedModelData");
                return (TexturedModelData) method.invoke(null);
            } catch (Exception e) {
                throw new RuntimeException("Failed to get model data for " + fishRegistry.getFishName(), e);
            }
        });

        // Register renderer
        EntityRendererRegistry.register(entityType, (context) -> new FishRenderer(context, fishRegistry));
    }

    private static DefaultAttributeContainer.Builder createFishAttributes(FishRegistry fishRegistry) {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 2);
    }

    private static class FishRenderer extends MobEntityRenderer<SchoolingFishEntity, EntityModel<SchoolingFishEntity>> {
        private final Identifier texture;

        public FishRenderer(EntityRendererFactory.Context context, FishRegistry fishRegistry) {
            super(context, createModel(context, fishRegistry), 1);
            this.texture = Identifier.of(FishingFrenzy.MOD_ID, "textures/entity/" + fishRegistry.getFishName() + ".png");
        }

        private static EntityModel<SchoolingFishEntity> createModel(EntityRendererFactory.Context context, FishRegistry fishRegistry) {
            try {
                Constructor<?> constructor = fishRegistry.getModelClass().getConstructor(ModelPart.class);
                return (EntityModel<SchoolingFishEntity>) constructor.newInstance(context.getPart(fishRegistry.getModelLayer()));
            } catch (Exception e) {
                throw new RuntimeException("Failed to create model for " + fishRegistry.getFishName(), e);
            }
        }

        @Override
        public Identifier getTexture(SchoolingFishEntity entity) {
            return texture;
        }
    }
}