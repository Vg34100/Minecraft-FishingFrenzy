package net.vg.fishingfrenzy.util;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

public class StatusEffectEntry {
    private final RegistryEntry<StatusEffect> effect;
    private final int duration;
    private final int amplifier;
    private final float chance;

    public StatusEffectEntry(RegistryEntry<StatusEffect> effect, int duration, int amplifier, float chance) {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.chance = chance;
    }

    public RegistryEntry<StatusEffect> getEffect() {
        return effect;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public float getChance() {
        return chance;
    }
}
