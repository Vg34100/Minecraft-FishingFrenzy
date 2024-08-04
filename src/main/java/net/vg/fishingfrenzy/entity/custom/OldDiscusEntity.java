package net.vg.fishingfrenzy.entity.custom;

import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
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
import net.vg.fishingfrenzy.entity.ai.goal.CustomFishMateGoal;
import net.vg.fishingfrenzy.entity.mob.PassiveFishEntity;
import net.vg.fishingfrenzy.entity.mob.PassiveSchoolingFishEntity;
import net.vg.fishingfrenzy.entity.mob.PassiveWaterCreatureEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class OldDiscusEntity extends PassiveSchoolingFishEntity {
    public OldDiscusEntity(EntityType<? extends PassiveSchoolingFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new TemptGoal(this, 1.250, Ingredient.ofItems(Items.BEETROOT), false));
        this.goalSelector.add(3, new CustomFishMateGoal(this, 1.0));

        this.goalSelector.add(3, new WanderAroundFarGoal(this, 10));
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


//    @Nullable
//    public DiscusEntity  createChild(ServerWorld world, PassiveSchoolingFishEntity entity) {
//        FishingFrenzy.LOGGER.info("Creating child");
//        return (DiscusEntity) ModEntities.DISCUS.create(world);
//    }

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
    public void breed(ServerWorld world, PassiveFishEntity other) {
        FishingFrenzy.LOGGER.info("Breeding process started");
        PassiveSchoolingFishEntity baby = (PassiveSchoolingFishEntity) this.createChild(world, other);
        if (baby != null) {
            FishingFrenzy.LOGGER.info("Baby created");
            baby.setBaby(true);
            baby.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            this.breed(world, other, baby);
            world.spawnEntityAndPassengers(baby);
        }
    }

    @Override
    public void breed(ServerWorld world, PassiveFishEntity other, @Nullable PassiveWaterCreatureEntity baby) {
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


    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (this.isBreedingItem(itemStack)) {
            if (!this.getWorld().isClient && this.getBreedingAge() == 0 && this.canEat()) {
                this.eat(player, hand, itemStack);
                this.lovePlayer(player);
                return ActionResult.SUCCESS;
            }
            if (this.isBaby()) {
                this.eat(player, hand, itemStack);
                this.growUp(toGrowUpAge(-this.getBreedingAge()), true);
                return ActionResult.success(this.getWorld().isClient);
            }
            if (this.getWorld().isClient) {
                return ActionResult.CONSUME;
            }
        }
        return super.interactMob(player, hand);
    }

    @Override
    public @Nullable PassiveWaterCreatureEntity createChild(ServerWorld world, PassiveWaterCreatureEntity entity) {
//        FishingFrenzy.LOGGER.info("Creating child");
//        return ModEntities.DISCUS.create(world);
        return null;
    }


//    @Override
//    public @Nullable PassiveSchoolingFishEntity createChild(ServerWorld world, PassiveSchoolingFishEntity entity) {
//        return null;
//    }
}
