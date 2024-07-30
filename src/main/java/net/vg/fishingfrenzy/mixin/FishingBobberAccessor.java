package net.vg.fishingfrenzy.mixin;

import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FishingBobberEntity.class)
public interface FishingBobberAccessor {
    @Accessor("hookCountdown")
    int getHookCountdown();

    @Accessor("hookCountdown")
    void setHookCountdown(int hookCountdown);

}
