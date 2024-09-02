package net.vg.fishingfrenzy.entity.goal;

import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.vg.fishingfrenzy.Constants;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.entity.mob.BreedableSchoolingFishEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

public class BreedableSchoolFishMateGoal extends Goal {
    private static final TargetingConditions VALID_MATE_PREDICATE = TargetingConditions.forNonCombat().range(8.0).ignoreLineOfSight();
    protected final BreedableSchoolingFishEntity fish;
    private final Class<? extends BreedableSchoolingFishEntity> entityClass;
    protected final Level level;
    @Nullable
    protected BreedableSchoolingFishEntity mate;
    private int timer;
    private final double speedModifier;

    public BreedableSchoolFishMateGoal(BreedableSchoolingFishEntity fish, double speedModifier) {
        this(fish, speedModifier, fish.getClass());
    }

    public BreedableSchoolFishMateGoal(BreedableSchoolingFishEntity fish, double speedModifier, Class<? extends BreedableSchoolingFishEntity> entityClass) {
        this.fish = fish;
        this.level = fish.level();
        this.entityClass = entityClass;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.fish.isInLove()) {
            return false;
        } else {
            this.mate = this.findMate();
            return this.mate != null;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.mate != null && this.mate.isAlive() && this.mate.isInLove() && this.timer < 60;
    }

    @Override
    public void stop() {
        this.mate = null;
        this.timer = 0;
    }

    @Override
    public void tick() {
        this.fish.getLookControl().setLookAt(this.mate, 10.0F, (float) this.fish.getMaxHeadXRot());
        this.fish.getNavigation().moveTo(this.mate, this.speedModifier);
        this.timer++;
        if (this.timer >= this.adjustedTickDelay(60) && this.fish.distanceToSqr(this.mate) < 9.0) {
            this.breed();
        }
    }

    @Nullable
    private BreedableSchoolingFishEntity findMate() {
        List<? extends BreedableSchoolingFishEntity> list = this.level.getNearbyEntities(this.entityClass, VALID_MATE_PREDICATE, this.fish, this.fish.getBoundingBox().inflate(8.0));
        double d = Double.MAX_VALUE;
        BreedableSchoolingFishEntity closestMate = null;

        for (BreedableSchoolingFishEntity potentialMate : list) {
            if (this.fish.canBreedWith(potentialMate) && this.fish.distanceToSqr(potentialMate) < d) {
                closestMate = potentialMate;
                d = this.fish.distanceToSqr(potentialMate);
            }
        }

        Constants.LOGGER.info("Found mate: " + (closestMate != null));
        return closestMate;
    }

    protected void breed() {
        Constants.LOGGER.info("Breeding fish");
        this.fish.breed((ServerLevel) this.level, this.mate);
    }
}