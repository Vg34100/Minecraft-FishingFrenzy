package net.vg.fishingfrenzy;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.component.ComponentType;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.config.ModConfigs;
import net.vg.fishingfrenzy.entity.ModEntities;
import net.vg.fishingfrenzy.entity.custom.BonefishEntity;
import net.vg.fishingfrenzy.entity.custom.CarpEntity;
import net.vg.fishingfrenzy.item.ModItemGroups;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.loot.ModLootTableModifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class FishingFrenzy implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("fishingfrenzy");

	// Constants for mod information.
	public static final String MOD_ID = "fishingfrenzy";
	public static final String MOD_NAME = "Fishing Frenzy";
	public static final String MOD_VERSION = fetchModVersion();
//	public static final LootConditionType BIOME_CHECK_LOOT_CONDITION_TYPE = new LootConditionType(BiomeCheckLootCondition.CODEC);


	/**
	 * This method is called when Minecraft is ready to load mods.
	 * It initializes the mod by registering configurations and block entities.
	 */
	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();
		ModItems.registerItems();
		ModItemGroups.registerItemGroups();

//		Registry.register(Registries.LOOT_CONDITION_TYPE, Identifier.of(MOD_ID, "biome_check"), BIOME_CHECK_LOOT_CONDITION_TYPE);

		ModEntities.registerEntities();
		ModLootTableModifiers.modifyLootTables();

		FabricDefaultAttributeRegistry.register(ModEntities.CARP, CarpEntity.createCarpAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BONEFISH, BonefishEntity.createBonefishAttributes());


		// Log the initialization message with mod name and version
		LOGGER.info("Initialized Mod: {} v{}", MOD_NAME, MOD_VERSION);	}

	/**
	 * Fetches the mod version from the mod metadata.
	 *
	 * @return The version of the mod as a String.
	 */
	private static String fetchModVersion() {
		// Attempt to get the mod container from the FabricLoader
		Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(MOD_ID);

		// If the mod container is found, return the version from the metadata.
		// Otherwise, return a default version "1.0.0".
		return modContainer.map(container -> container.getMetadata().getVersion().getFriendlyString()).orElse("1.0.0");
	}
}