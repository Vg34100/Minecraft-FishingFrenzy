package net.vg.fishingfrenzy.loot.condition;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.vg.fishingfrenzy.FishingFrenzy;

import java.util.Optional;

public class BiomeCheckLootCondition implements LootCondition {
    public static final MapCodec<BiomeCheckLootCondition> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    RegistryKey.createCodec(RegistryKeys.BIOME).optionalFieldOf("biome").forGetter(BiomeCheckLootCondition::getBiome),
                    RegistryCodecs.entryList(RegistryKeys.BIOME).optionalFieldOf("biome_tags").forGetter(BiomeCheckLootCondition::getBiomeTags)
            ).apply(instance, BiomeCheckLootCondition::new)
    );

    private final Optional<RegistryKey<Biome>> biome;
    private final Optional<RegistryEntryList<Biome>> biomeTags;

    public BiomeCheckLootCondition(Optional<RegistryKey<Biome>> biome, Optional<RegistryEntryList<Biome>> biomeTags) {
        this.biome = biome;
        this.biomeTags = biomeTags;
    }

    public Optional<RegistryKey<Biome>> getBiome() {
        return biome;
    }

    public Optional<RegistryEntryList<Biome>> getBiomeTags() {
        return biomeTags;
    }

    @Override
    public LootConditionType getType() {
        return FishingFrenzy.BIOME_CHECK_LOOT_CONDITION_TYPE;
    }

    @Override
    public boolean test(LootContext lootContext) {
        ServerWorld world = lootContext.getWorld();
        Vec3d origin = lootContext.get(LootContextParameters.ORIGIN);
        BlockPos pos = new BlockPos((int) origin.getX(), (int) origin.getY(), (int) origin.getZ());
        RegistryEntry<Biome> currentBiome = world.getBiome(pos);

        if (biome.isPresent() && !currentBiome.matchesKey(biome.get())) {
            return false;
        }

        if (biomeTags.isPresent() && !biomeTags.get().contains(currentBiome)) {
            return false;
        }

        return true;
    }
}

