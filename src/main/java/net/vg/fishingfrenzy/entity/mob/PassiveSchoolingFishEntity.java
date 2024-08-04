package net.vg.fishingfrenzy.entity.mob;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.vg.fishingfrenzy.entity.ai.goal.FollowGroupLeaderGoal;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public abstract class PassiveSchoolingFishEntity extends PassiveFishEntity {
    @Nullable
    private PassiveSchoolingFishEntity leader;
    private int groupSize = 1;

    public PassiveSchoolingFishEntity(EntityType<? extends PassiveSchoolingFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(5, new FollowGroupLeaderGoal(this));
    }

    @Override
    public int getLimitPerChunk() {
        return this.getMaxGroupSize();
    }

    public int getMaxGroupSize() {
        return super.getLimitPerChunk();
    }

    @Override
    protected boolean hasSelfControl() {
        return !this.hasLeader();
    }

    public boolean hasLeader() {
        return this.leader != null && this.leader.isAlive();
    }

    public PassiveSchoolingFishEntity joinGroupOf(PassiveSchoolingFishEntity groupLeader) {
        this.leader = groupLeader;
        groupLeader.increaseGroupSize();
        return groupLeader;
    }

    public void leaveGroup() {
        this.leader.decreaseGroupSize();
        this.leader = null;
    }

    private void increaseGroupSize() {
        this.groupSize++;
    }

    private void decreaseGroupSize() {
        this.groupSize--;
    }

    public boolean canHaveMoreFishInGroup() {
        return this.hasOtherFishInGroup() && this.groupSize < this.getMaxGroupSize();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.hasOtherFishInGroup() && this.getWorld().random.nextInt(200) == 1) {
            List<? extends PassiveSchoolingFishEntity> list = this.getWorld().getNonSpectatingEntities(this.getClass(), this.getBoundingBox().expand(8.0, 8.0, 8.0));
            if (list.size() <= 1) {
                this.groupSize = 1;
            }
        }
    }

    public boolean hasOtherFishInGroup() {
        return this.groupSize > 1;
    }

    public boolean isCloseEnoughToLeader() {
        return this.squaredDistanceTo(this.leader) <= 121.0;
    }

    public void moveTowardLeader() {
        if (this.hasLeader()) {
            this.getNavigation().startMovingTo(this.leader, 1.0);
        }
    }

    public void pullInOtherFish(Stream<? extends PassiveSchoolingFishEntity> fish) {
        fish.limit((long)(this.getMaxGroupSize() - this.groupSize)).filter(fishx -> fishx != this).forEach(fishx -> fishx.joinGroupOf(this));
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        super.initialize(world, difficulty, spawnReason, entityData);
        if (entityData == null) {
            entityData = new FishData(this);
        } else {
            this.joinGroupOf(((FishData) entityData).leader);
        }

        return entityData;
    }

//    @Nullable
//    public abstract PassiveSchoolingFishEntity createChild(ServerWorld world, PassiveSchoolingFishEntity entity);

    public static class FishData implements EntityData {
        public final PassiveSchoolingFishEntity leader;

        public FishData(PassiveSchoolingFishEntity leader) {
            this.leader = leader;
        }
    }
}
