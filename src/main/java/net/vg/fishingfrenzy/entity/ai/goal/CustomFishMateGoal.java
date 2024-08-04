package net.vg.fishingfrenzy.entity.ai.goal;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.entity.mob.PassiveSchoolingFishEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

public class CustomFishMateGoal extends Goal {
    private static final TargetPredicate VALID_MATE_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(8.0).ignoreVisibility();
    protected final PassiveSchoolingFishEntity fish;
    private final Class<? extends PassiveSchoolingFishEntity> entityClass;
    protected final World world;
    @Nullable
    protected PassiveSchoolingFishEntity mate;
    private int timer;
    private final double speed;

    public CustomFishMateGoal(PassiveSchoolingFishEntity fish, double speed) {
        this(fish, speed, fish.getClass());
    }

    public CustomFishMateGoal(PassiveSchoolingFishEntity fish, double speed, Class<? extends PassiveSchoolingFishEntity> entityClass) {
        this.fish = fish;
        this.world = fish.getWorld();
        this.entityClass = entityClass;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (!this.fish.isInLove()) {
            return false;
        } else {
            this.mate = this.findMate();
            return this.mate != null;
        }
    }

    @Override
    public boolean shouldContinue() {
        return this.mate != null && this.mate.isAlive() && this.mate.isInLove() && this.timer < 60;
    }

    @Override
    public void stop() {
        this.mate = null;
        this.timer = 0;
    }

    @Override
    public void tick() {
        this.fish.getLookControl().lookAt(this.mate, 10.0F, (float) this.fish.getMaxLookPitchChange());
        this.fish.getNavigation().startMovingTo(this.mate, this.speed);
        this.timer++;
        if (this.timer >= this.getTickCount(60) && this.fish.squaredDistanceTo(this.mate) < 9.0) {
            this.breed();
        }
    }

    @Nullable
    private PassiveSchoolingFishEntity findMate() {
        List<? extends PassiveSchoolingFishEntity> list = this.world.getTargets(this.entityClass, VALID_MATE_PREDICATE, this.fish, this.fish.getBoundingBox().expand(8.0));
        double d = Double.MAX_VALUE;
        PassiveSchoolingFishEntity closestMate = null;

        for (PassiveSchoolingFishEntity potentialMate : list) {
            if (this.fish.canBreedWith(potentialMate) && this.fish.squaredDistanceTo(potentialMate) < d) {
                closestMate = potentialMate;
                d = this.fish.squaredDistanceTo(potentialMate);
            }
        }

        FishingFrenzy.LOGGER.info("Found mate: " + (closestMate != null));
        return closestMate;
    }

    protected void breed() {
        FishingFrenzy.LOGGER.info("Breeding fish");
        this.fish.breed((ServerWorld) this.world, this.mate);
    }
}
