package net.vg.fishingfrenzy.config;

import net.vg.fishingfrenzy.FishingFrenzy;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static String[] necessaryConfigs = {

    };

    public static void registerConfigs() {
        FishingFrenzy.LOGGER.info("Registering mod configurations.");
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(FishingFrenzy.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        FishingFrenzy.LOGGER.debug("Creating default configuration values.");
    }

    private static void assignConfigs() {
        FishingFrenzy.LOGGER.info("Assigning configuration values.");


        FishingFrenzy.LOGGER.info("All {} configurations have been set properly.", configs.getConfigsList().size());
    }

    public static void saveConfigs() {
        FishingFrenzy.LOGGER.info("Saving current configuration values.");

        CONFIG.save();
        FishingFrenzy.LOGGER.info("Configuration values saved successfully.");
    }
}