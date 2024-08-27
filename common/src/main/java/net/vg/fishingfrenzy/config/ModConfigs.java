package net.vg.fishingfrenzy.config;

import com.mojang.datafixers.util.Pair;
import net.vg.fishingfrenzy.Constants;

import java.util.Arrays;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static boolean EASY_MODE;



    public static String[] necessaryConfigs = {
        "config.easy.mode"
    };

    public static void registerConfigs() {
        Constants.LOGGER.info("Registering mod configurations.");
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Constants.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        Constants.LOGGER.debug("Creating default configuration values.");
        configs.addKeyValuePair(new Pair<>("config.easy.mode", false), Arrays.asList(
                "Makes the fish easier to get by removing the weather, time, and height conditions",
                "Default: false"
        ));

    }

    private static void assignConfigs() {
        Constants.LOGGER.info("Assigning configuration values.");
        // Assign configuration values to fields
        EASY_MODE = CONFIG.getOrDefault("config.easy.mode", false);


        Constants.LOGGER.info("All {} configurations have been set properly.", configs.getConfigsList().size());
    }

    public static void saveConfigs() {
        Constants.LOGGER.info("Saving current configuration values.");
        CONFIG.set("config.easy.mode", EASY_MODE);


        CONFIG.save();
        Constants.LOGGER.info("Configuration values saved successfully.");
    }
}