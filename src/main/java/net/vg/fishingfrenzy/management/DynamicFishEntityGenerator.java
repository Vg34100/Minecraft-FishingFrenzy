package net.vg.fishingfrenzy.management;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.datafixer.Schemas;
import net.minecraft.entity.*;
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
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.entity.ai.goal.BreedableSchoolFishMateGoal;
import net.vg.fishingfrenzy.entity.mob.BreedableSchoolingFishEntity;
import net.vg.fishingfrenzy.item.custom.FishRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DynamicFishEntityGenerator {



    public static EntityType<CustomBreedableSchoolingFishEntity> generateFishEntity(FishRegistry fishRegistry) {
        FishingFrenzy.LOGGER.info("Registering dynamic entity for: {}", fishRegistry.getFishName());
        String entityName = fishRegistry.getFishName();

        EntityType<CustomBreedableSchoolingFishEntity> entityType = Registry.register(
                Registries.ENTITY_TYPE,
                Identifier.of(FishingFrenzy.MOD_ID, entityName),
                EntityType.Builder.create(
                                (EntityType<CustomBreedableSchoolingFishEntity> type, World world) ->
                                        new CustomBreedableSchoolingFishEntity(type, world, fishRegistry),
                                SpawnGroup.WATER_AMBIENT
                        )
                        .dimensions(1f, 1f)
                        .build(entityName)
        );

//        EntityType<BreedableSchoolingFishEntity> entityType = Registry.register(
//                Registries.ENTITY_TYPE,
//                Identifier.of(FishingFrenzy.MOD_ID, entityName),
//                EntityType.Builder.create(
//                                (EntityType<BreedableSchoolingFishEntity> type, World world) ->
//                                        new BreedableSchoolingFishEntity(type, world) {
//                                            public final AnimationState idleAnimationState = new AnimationState();
//                                            private int idleAnimationTimeout = 0;
//
//                                            @Override
//                                            protected void initGoals() {
//                                                this.goalSelector.add(2, new TemptGoal(this, 1.250, Ingredient.ofItems(Items.SEAGRASS), false));
//                                                this.goalSelector.add(1, new BreedableSchoolFishMateGoal(this, 1.0));
//                                                this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
//                                                this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
//                                                this.goalSelector.add(5, new LookAroundGoal(this));
//                                                this.goalSelector.add(4, new MeleeAttackGoal(this, 1.0, false));
//                                                this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
//                                            }
//
//                                            @Override
//                                            protected void dropLoot(DamageSource damageSource, boolean causedByPlayer) {
//                                                super.dropLoot(damageSource, causedByPlayer);
//                                                this.dropItem(fishRegistry.getFish());
//                                            }
//
//                                            @Override
//                                            protected SoundEvent getFlopSound() {
//                                                return null;
//                                            }
//
//                                            @Override
//                                            public ItemStack getBucketItem() {
//                                                return null;
//                                            }
//
//                                            private void setupAnimationStates() {
//                                                if (this.idleAnimationTimeout <= 0) {
//                                                    this.idleAnimationTimeout = this.random.nextInt(40) + 80;
//                                                    this.idleAnimationState.start(this.age);
//                                                } else {
//                                                    --this.idleAnimationTimeout;
//                                                }
//                                            }
//
//                                            @Override
//                                            protected void updateLimbs(float posDelta) {
//                                                float f = this.getPose() == EntityPose.SWIMMING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
//                                                this.limbAnimator.updateLimbs(f, 0.2f);
//                                            }
//
//                                            @Override
//                                            public void tick() {
//                                                super.tick();
//                                                if(this.getWorld().isClient()) {
//                                                    setupAnimationStates();
//                                                }
//                                            }
//
//                                            @Override
//                                            public boolean isBreedingItem(ItemStack stack) {
//                                                return stack.isOf(Items.SEAGRASS);
//                                            }
//
//                                            @Override
//                                            @Nullable
//                                            public BreedableSchoolingFishEntity createChild(ServerWorld world, BreedableSchoolingFishEntity mate) {
//                                                BreedableSchoolingFishEntity baby = (BreedableSchoolingFishEntity) this.getType().create(world);
//                                                if (baby != null) {
//                                                    baby.setBaby(true);
//                                                }
//                                                return baby;
//                                            }
//
//                                            @Override
//                                            public void breed(ServerWorld world, BreedableSchoolingFishEntity other) {
//                                                BreedableSchoolingFishEntity baby = this.createChild(world, other);
//                                                if (baby != null) {
//                                                    baby.setBaby(true);
//                                                    baby.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
//                                                    this.breed(world, other, baby);
//                                                    world.spawnEntityAndPassengers(baby);
//                                                }
//                                            }
//
//                                            @Override
//                                            public void breed(ServerWorld world, BreedableSchoolingFishEntity other, @Nullable BreedableSchoolingFishEntity baby) {
//                                                if (baby != null) {
//                                                    Optional.ofNullable(this.getLovingPlayer()).or(() -> Optional.ofNullable(other.getLovingPlayer())).ifPresent(player -> {
//                                                        player.incrementStat(Stats.ANIMALS_BRED);
//                                                    });
//                                                    this.setBreedingAge(6000);
//                                                    other.setBreedingAge(6000);
//                                                    this.resetLoveTicks();
//                                                    other.resetLoveTicks();
//                                                    world.sendEntityStatus(this, EntityStatuses.ADD_BREEDING_PARTICLES);
//                                                    if (world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
//                                                        world.spawnEntity(new ExperienceOrbEntity(world, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
//                                                    }
//                                                }
//                                            }
//                                        },
//                                SpawnGroup.WATER_AMBIENT
//                        )
//                        .dimensions(1f, 1f)
//                        .build(entityName)
//        );

        // Register the entity attributes
        FabricDefaultAttributeRegistry.register(entityType, createFishAttributes());
        FishingFrenzy.LOGGER.info("Registered dynamic entity: {}", entityName);

        return entityType;
    }

    private static DefaultAttributeContainer.Builder createFishAttributes() {
        return SchoolingFishEntity.createFishAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1f)
                .add(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, 0.2f);
    }
}
