package net.vg.fishingfrenzy.config;

import net.vg.fishingfrenzy.FishingFrenzy;
import com.mojang.datafixers.util.Pair;

import java.util.Arrays;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static boolean EASY_MODE;

    public static String[] necessaryConfigs = {
        "config.easy.mode"
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

        configs.addKeyValuePair(new Pair<>("config.easy.mode", false), Arrays.asList(
                "Makes the fish easier to get by removing the weather, time, and height conditions",
                "Default: false"
        ));
    }

    private static void assignConfigs() {
        FishingFrenzy.LOGGER.info("Assigning configuration values.");

        EASY_MODE = CONFIG.getOrDefault("config.easy.mode", false);


        FishingFrenzy.LOGGER.info("All {} configurations have been set properly.", configs.getConfigsList().size());
    }

    public static void saveConfigs() {
        FishingFrenzy.LOGGER.info("Saving current configuration values.");

        CONFIG.set("config.easy.mode", EASY_MODE);

        CONFIG.save();
        FishingFrenzy.LOGGER.info("Configuration values saved successfully.");
    }
}