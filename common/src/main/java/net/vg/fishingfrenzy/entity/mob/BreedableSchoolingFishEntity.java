package net.vg.fishingfrenzy.entity.mob;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.vg.fishingfrenzy.Constants;

import java.util.UUID;

public abstract class BreedableSchoolingFishEntity extends AbstractSchoolingFish {
    private static final EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(BreedableSchoolingFishEntity.class, EntityDataSerializers.BOOLEAN);
    private static final int BABY_START_AGE = -24000;
    private UUID lovingPlayer;


    private int loveTicks;
    public void setLoveTicks(int loveTicks) {
        this.loveTicks = loveTicks;
    }
    public int getLoveTicks() {
        return this.loveTicks;
    }



    private int breedingAge;
    public int getBreedingAge() {
        return this.breedingAge;
    }
    public void setBreedingAge(int age) {
        Constants.LOGGER.debug("Setting breeding age: " + age);
        this.breedingAge = age;
        this.entityData.set(DATA_BABY_ID, age < 0);
    }
    private int forcedAge;
    private int happyTicksRemaining;

    public BreedableSchoolingFishEntity(EntityType<? extends AbstractSchoolingFish> entityType, Level level) {
        super(entityType, level);
    }

//    public static DefaultAttributeContainer.Builder createBreedableFishAttributes() {
//        return PathAwareEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0);
//    }
    public abstract boolean isBreedingItem(ItemStack stack);


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder arg) {
        super.defineSynchedData(arg);
        arg.define(DATA_BABY_ID, false);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.getBreedingAge() != 0) {
            this.loveTicks = 0;
        }

        if (this.loveTicks > 0) {
            this.loveTicks--;
            if (this.loveTicks % 10 == 0) {
                double d = this.random.nextGaussian() * 0.02;
                double e = this.random.nextGaussian() * 0.02;
                double f = this.random.nextGaussian() * 0.02;

                this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), d, e, f);
            }
        }
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (this.isBreedingItem(itemStack)) {
            if (!this.level().isClientSide && this.getBreedingAge() == 0 && this.canEat()) {
                this.eat(player, interactionHand, itemStack);
                this.lovePlayer(player);
                return InteractionResult.SUCCESS;
            }
            if (this.isBaby()) {
                this.eat(player, interactionHand, itemStack);
                this.growUp(toGrowUpAge(-this.getBreedingAge()), true);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            if (this.level().isClientSide) {
                return InteractionResult.CONSUME;
            }
        }
        return super.mobInteract(player, interactionHand);
    }

    protected void eat(Player player, InteractionHand hand, ItemStack stack) {
        stack.consume(1, player);
    }
    public boolean isBaby() {
        return this.entityData.get(DATA_BABY_ID);
    }
    public boolean canEat() {
        return this.loveTicks <= 0;
    }
    public void lovePlayer(Player player) {
        this.loveTicks = 600;
        if (player != null) {
            this.lovingPlayer = player.getUUID();
        }

        this.level().broadcastEntityEvent(this, (byte)18);
    }
    public static int toGrowUpAge(int breedingAge) {
        return (int)((float)(breedingAge / 20) * 0.1F);
    }
    public void growUp(int age, boolean overGrow) {
        int i = this.getBreedingAge();
        i += age * 20;
        if (i > 0) {
            i = 0;
        }

        int k = i - i;
        this.setBreedingAge(i);
        if (overGrow) {
            this.forcedAge += k;
            if (this.happyTicksRemaining == 0) {
                this.happyTicksRemaining = 40;
            }
        }

        if (this.getBreedingAge() == 0) {
            this.setBreedingAge(this.forcedAge);
        }
    }
    public void growUp(int age) {
        this.growUp(age, false);
    }




}
