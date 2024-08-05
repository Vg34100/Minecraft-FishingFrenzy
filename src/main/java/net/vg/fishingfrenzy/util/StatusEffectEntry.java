package net.vg.fishingfrenzy.util;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

public record StatusEffectEntry(RegistryEntry<StatusEffect> effect, int duration, int amplifier, float chance) {
}
