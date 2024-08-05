package net.vg.fishingfrenzy;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.vg.fishingfrenzy.config.ModConfigs;
import net.vg.fishingfrenzy.item.ModItemGroups;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.loot.ModLootTableModifiers;

import net.vg.fishingfrenzy.world.ModEntitySpawns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class FishingFrenzy implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("fishingfrenzy");

	// Constants for mod information.
	public static final String MOD_ID = "fishingfrenzy";
	public static final String MOD_NAME = "Fishing Frenzy";
	public static final String MOD_VERSION = fetchModVersion();


	/**
	 * This method is called when Minecraft is ready to load mods.
	 * It initializes the mod by registering configurations and block entities.
	 */
	@Override
	public void onInitialize() {
		// Registers Configs
		ModConfigs.registerConfigs();

		// Registers Items and their Groups
		ModItems.registerItems();
		ModItemGroups.registerItemGroups();

		// Modifies Loot Tables and Spawning
		ModLootTableModifiers.modifyLootTables();
		ModEntitySpawns.addEntitySpawns();

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