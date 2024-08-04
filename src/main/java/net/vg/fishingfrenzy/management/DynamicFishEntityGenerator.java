package net.vg.fishingfrenzy.management;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.item.custom.FishRegistry;

public class DynamicFishEntityGenerator {

    public static EntityType<SchoolingFishEntity> generateFishEntity(FishRegistry fishRegistry) {
        String entityName = fishRegistry.getFishName() + "_entity";

        EntityType<SchoolingFishEntity> entityType = Registry.register(
                Registries.ENTITY_TYPE,
                Identifier.of(FishingFrenzy.MOD_ID, entityName),
                EntityType.Builder.<SchoolingFishEntity>create(
                                (EntityType<SchoolingFishEntity> type, World world) ->
                                        new SchoolingFishEntity(type, world) {

                                            @Override
                                            protected void initGoals() {
                                                this.goalSelector.add(2, new TemptGoal(this, 1.250, Ingredient.ofItems(Items.SEAGRASS), false));
                                                this.goalSelector.add(3, new FollowGroupLeaderGoal(this));
                                                this.goalSelector.add(3, new WanderAroundFarGoal(this, 10));
                                                this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
                                                this.goalSelector.add(5, new LookAroundGoal(this));
                                                this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0, false));
                                                this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));

                                            }


                                            @Override
                                            protected void dropLoot(DamageSource damageSource, boolean causedByPlayer) {
                                                super.dropLoot(damageSource, causedByPlayer);
                                                this.dropItem(fishRegistry.getFish());

                                            }

                                            @Override
                                            protected SoundEvent getFlopSound() {
                                                return null;
                                            }

                                            @Override
                                            public ItemStack getBucketItem() {
                                                return null;
                                            }
                                        },
                                SpawnGroup.WATER_AMBIENT
                        )
                        .dimensions(1f, 1f)
                        .build(entityName)
        );

        // Register the entity attributes
        FabricDefaultAttributeRegistry.register(entityType, createFishAttributes());

        return entityType;
    }

    private static DefaultAttributeContainer.Builder createFishAttributes() {
        return SchoolingFishEntity.createFishAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0D);
    }
}