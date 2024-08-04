package net.vg.fishingfrenzy.entity.custom;

import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.entity.ModEntities;
import net.vg.fishingfrenzy.entity.ai.goal.BreedableSchoolFishMateGoal;
import net.vg.fishingfrenzy.entity.mob.BreedableSchoolingFishEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DiscusEntity extends BreedableSchoolingFishEntity {
    public DiscusEntity(EntityType<? extends BreedableSchoolingFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new TemptGoal(this, 1.250, Ingredient.ofItems(Items.BEETROOT), false));
        this.goalSelector.add(3, new BreedableSchoolFishMateGoal(this, 1.0));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createDiscusAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1f)
                .add(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, 0.2f);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.SEAGRASS);
    }

    @Nullable
    @Override
    public DiscusEntity createChild(ServerWorld world, BreedableSchoolingFishEntity mate) {
        FishingFrenzy.LOGGER.info("Creating child");
        DiscusEntity baby = (DiscusEntity) ModEntities.DISCUS.create(world);
        if (baby != null) {
            baby.setBaby(true);
            FishingFrenzy.LOGGER.info("Child created and set to baby");
        }
        return baby;
    }

    @Override
    public boolean isFromBucket() {
        return false;
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
    }

    @Override
    public ItemStack getBucketItem() {
        return null;
    }

    @Override
    public void breed(ServerWorld world, BreedableSchoolingFishEntity other) {
        BreedableSchoolingFishEntity baby = this.createChild(world, other);
        if (baby != null) {
            FishingFrenzy.LOGGER.info("Baby created");
            baby.setBaby(true);
            baby.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            this.breed(world, other, baby);
            world.spawnEntityAndPassengers(baby);
        } else {
            FishingFrenzy.LOGGER.info("Baby was null, not spawning");
        }
    }

    @Override
    public void breed(ServerWorld world, BreedableSchoolingFishEntity other, @Nullable BreedableSchoolingFishEntity baby) {
        if (baby != null) {
            FishingFrenzy.LOGGER.info("Spawning baby");
            Optional.ofNullable(this.getLovingPlayer()).or(() -> Optional.ofNullable(other.getLovingPlayer())).ifPresent(player -> {
                player.incrementStat(Stats.ANIMALS_BRED);
                // Criteria.BRED_ANIMALS.trigger(player, this, other, baby); // Uncomment if you have this criteria
            });
            this.setBreedingAge(6000);
            other.setBreedingAge(6000);
            this.resetLoveTicks();
            other.resetLoveTicks();
            world.sendEntityStatus(this, EntityStatuses.ADD_BREEDING_PARTICLES);
            if (world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
                world.spawnEntity(new ExperienceOrbEntity(world, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
            }
        } else {
            FishingFrenzy.LOGGER.info("Baby was null, not spawning");
        }
    }
}
