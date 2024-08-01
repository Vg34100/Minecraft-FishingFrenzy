package net.vg.fishingfrenzy.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.vg.fishingfrenzy.item.ModItems;

public class BonefishEntity extends SchoolingFishEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public BonefishEntity(EntityType<? extends SchoolingFishEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void dropLoot(DamageSource damageSource, boolean causedByPlayer) {
        super.dropLoot(damageSource, causedByPlayer);

        this.dropItem(ModItems.BONEFISH);

        // Chance to drop bones
        // 10% chance to drop 1-3 bones
        if (this.random.nextFloat() < 0.1f) {
            this.dropStack(new ItemStack(Items.BONE, this.random.nextInt(3) + 1));
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.SWIMMING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new TemptGoal(this, 1.250, Ingredient.ofItems(Items.BEETROOT), false));

        this.goalSelector.add(3, new WanderAroundFarGoal(this, 10));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(5, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createBonefishAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 5)
                .add(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, 0.2f);
    }


    @Override
    public int getMaxGroupSize() {
        return 2;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SALMON_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SALMON_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SALMON_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_SALMON_FLOP;
    }

    @Override
    public ItemStack getBucketItem() {
        return null;
    }
}
