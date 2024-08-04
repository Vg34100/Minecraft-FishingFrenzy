package net.vg.fishingfrenzy.entity.mob;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public abstract class PassiveFishEntity extends PassiveWaterCreatureEntity implements Bucketable {
//    private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(FishEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    protected static final int BREEDING_COOLDOWN = 6000;
    private int loveTicks;
    @Nullable
    private UUID lovingPlayer;

    public PassiveFishEntity(EntityType<? extends PassiveFishEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FishMoveControl(this);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    public static DefaultAttributeContainer.Builder createFishAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0);
    }

    @Override
    public boolean cannotDespawn() {
        return super.cannotDespawn() || this.isFromBucket();
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isFromBucket() && !this.hasCustomName();
    }

    @Override
    public int getLimitPerChunk() {
        return 8;
    }

//    @Override
//    protected void initDataTracker(DataTracker.Builder builder) {
//        super.initDataTracker(builder);
//        builder.add(FROM_BUCKET, false);
//    }
//
//    @Override
//    public boolean isFromBucket() {
//        return this.dataTracker.get(FROM_BUCKET);
//    }
//
//    @Override
//    public void setFromBucket(boolean fromBucket) {
//        this.dataTracker.set(FROM_BUCKET, fromBucket);
//    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("FromBucket", this.isFromBucket());
        nbt.putInt("InLove", this.loveTicks);
        if (this.lovingPlayer != null) {
            nbt.putUuid("LoveCause", this.lovingPlayer);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setFromBucket(nbt.getBoolean("FromBucket"));
        this.loveTicks = nbt.getInt("InLove");
        this.lovingPlayer = nbt.containsUuid("LoveCause") ? nbt.getUuid("LoveCause") : null;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, PlayerEntity.class, 8.0F, 1.6, 1.4));
        this.goalSelector.add(4, new SwimToRandomPlaceGoal(this));
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new SwimNavigation(this, world);
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(0.01F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(movementInput);
        }
    }

//    @Override
//    public void tickMovement() {
//
//    }

//    @Override
//    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
//        return Bucketable.tryBucket(player, hand, this).orElse(super.interactMob(player, hand));
//    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
    }

    @Override
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_FISH;
    }

    protected boolean hasSelfControl() {
        return true;
    }

    protected abstract SoundEvent getFlopSound();

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_FISH_SWIM;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {}

    @Override
    protected void mobTick() {
        if (this.getBreedingAge() != 0) {
            this.loveTicks = 0;
        }

        super.mobTick();
    }

    @Override
    public void tickMovement() {
        if (!this.isTouchingWater() && this.isOnGround() && this.verticalCollision) {
            this.setVelocity(this.getVelocity().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), 0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.setOnGround(false);
            this.velocityDirty = true;
            this.playSound(this.getFlopSound());
        }
        super.tickMovement();

        if (this.getBreedingAge() != 0) {
            this.loveTicks = 0;
        }

        if (this.loveTicks > 0) {
            this.loveTicks--;
            if (this.loveTicks % 10 == 0) {
                double d = this.random.nextGaussian() * 0.02;
                double e = this.random.nextGaussian() * 0.02;
                double f = this.random.nextGaussian() * 0.02;
                this.getWorld().addParticle(ParticleTypes.HEART, this.getParticleX(1.0), this.getRandomBodyY() + 0.5, this.getParticleZ(1.0), d, e, f);
            }
        }
    }

    @Override
    protected void applyDamage(DamageSource source, float amount) {
        this.resetLoveTicks();
        super.applyDamage(source, amount);
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos.down()).isOf(Blocks.GRASS_BLOCK) ? 10.0F : world.getPhototaxisFavor(pos);
    }

    public abstract boolean isBreedingItem(ItemStack stack);

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (this.isBreedingItem(itemStack)) {
            int i = this.getBreedingAge();
            if (!this.getWorld().isClient && i == 0 && this.canEat()) {
                this.eat(player, hand, itemStack);
                this.lovePlayer(player);
                return ActionResult.SUCCESS;
            }

            if (this.isBaby()) {
                this.eat(player, hand, itemStack);
                this.growUp(toGrowUpAge(-i), true);
                return ActionResult.success(this.getWorld().isClient);
            }

            if (this.getWorld().isClient) {
                return ActionResult.CONSUME;
            }
        }

        return super.interactMob(player, hand);
    }

    protected void eat(PlayerEntity player, Hand hand, ItemStack stack) {
        stack.decrementUnlessCreative(1, player);
    }

    public boolean canEat() {
        return this.loveTicks <= 0;
    }

    public void lovePlayer(@Nullable PlayerEntity player) {
        this.loveTicks = 600;
        if (player != null) {
            this.lovingPlayer = player.getUuid();
        }

        this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_BREEDING_PARTICLES);
    }

    public void setLoveTicks(int loveTicks) {
        this.loveTicks = loveTicks;
    }

    public int getLoveTicks() {
        return this.loveTicks;
    }

    @Nullable
    public ServerPlayerEntity getLovingPlayer() {
        if (this.lovingPlayer == null) {
            return null;
        } else {
            PlayerEntity playerEntity = this.getWorld().getPlayerByUuid(this.lovingPlayer);
            return playerEntity instanceof ServerPlayerEntity ? (ServerPlayerEntity)playerEntity : null;
        }
    }

    public boolean isInLove() {
        return this.loveTicks > 0;
    }

    public void resetLoveTicks() {
        this.loveTicks = 0;
    }

    public boolean canBreedWith(PassiveFishEntity other) {
        if (other == this) {
            return false;
        } else {
            return other.getClass() != this.getClass() ? false : this.isInLove() && other.isInLove();
        }
    }

//    @Nullable
//    public abstract PassiveFishEntity createChild(ServerWorld world, PassiveFishEntity entity);


    public void breed(ServerWorld world, PassiveFishEntity other) {
        PassiveWaterCreatureEntity baby = this.createChild(world, other);
//        this.createChild(world, other);
        if (baby != null) {
            baby.setBaby(true);
            baby.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F);
            this.breed(world, other, baby);
            world.spawnEntityAndPassengers(baby);
        }
    }

    public void breed(ServerWorld world, PassiveFishEntity other, @Nullable PassiveWaterCreatureEntity baby) {
        Optional.ofNullable(this.getLovingPlayer()).or(() -> Optional.ofNullable(other.getLovingPlayer())).ifPresent(player -> {
            player.incrementStat(Stats.ANIMALS_BRED);
//            Criteria.BRED_ANIMALS.trigger(player, this, other, baby);
        });
        this.setBreedingAge(6000);
        other.setBreedingAge(6000);
        this.resetLoveTicks();
        other.resetLoveTicks();
        world.sendEntityStatus(this, EntityStatuses.ADD_BREEDING_PARTICLES);
        if (world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            world.spawnEntity(new ExperienceOrbEntity(world, this.getX(), this.getY(), this.getZ(), this.getRandom().nextInt(7) + 1));
        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.ADD_BREEDING_PARTICLES) {
            for (int i = 0; i < 7; i++) {
                double d = this.random.nextGaussian() * 0.02;
                double e = this.random.nextGaussian() * 0.02;
                double f = this.random.nextGaussian() * 0.02;
                this.getWorld().addParticle(ParticleTypes.HEART, this.getParticleX(1.0), this.getRandomBodyY() + 0.5, this.getParticleZ(1.0), d, e, f);
            }
        } else {
            super.handleStatus(status);
        }
    }

    static class FishMoveControl extends MoveControl {
        private final PassiveFishEntity fish;

        FishMoveControl(PassiveFishEntity owner) {
            super(owner);
            this.fish = owner;
        }

        @Override
        public void tick() {
            if (this.fish.isSubmergedIn(FluidTags.WATER)) {
                this.fish.setVelocity(this.fish.getVelocity().add(0.0, 0.005, 0.0));
            }

            if (this.state == State.MOVE_TO && !this.fish.getNavigation().isIdle()) {
                float f = (float)(this.speed * this.fish.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                this.fish.setMovementSpeed(MathHelper.lerp(0.125F, this.fish.getMovementSpeed(), f));
                double d = this.targetX - this.fish.getX();
                double e = this.targetY - this.fish.getY();
                double g = this.targetZ - this.fish.getZ();
                if (e != 0.0) {
                    double h = Math.sqrt(d * d + e * e + g * g);
                    this.fish.setVelocity(this.fish.getVelocity().add(0.0, (double)this.fish.getMovementSpeed() * (e / h) * 0.1, 0.0));
                }

                if (d != 0.0 || g != 0.0) {
                    float i = (float)(MathHelper.atan2(g, d) * 180.0F / (float)Math.PI) - 90.0F;
                    this.fish.setYaw(this.wrapDegrees(this.fish.getYaw(), i, 90.0F));
                    this.fish.bodyYaw = this.fish.getYaw();
                }
            } else {
                this.fish.setMovementSpeed(0.0F);
            }
        }
    }

    static class SwimToRandomPlaceGoal extends SwimAroundGoal {
        private final PassiveFishEntity fish;

        public SwimToRandomPlaceGoal(PassiveFishEntity fish) {
            super(fish, 1.0, 40);
            this.fish = fish;
        }

        @Override
        public boolean canStart() {
            return this.fish.hasSelfControl() && super.canStart();
        }
    }
}
