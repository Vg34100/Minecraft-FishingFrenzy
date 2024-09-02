package net.vg.fishingfrenzy;

import net.vg.fishingfrenzy.registry.DynamicFishEntityGenerator;
import net.vg.fishingfrenzy.registry.DynamicFishSystem;
import net.vg.fishingfrenzy.registry.ModItems;
import net.vg.fishingfrenzy.registry.ModTabRegsitry;

public final class FishingFrenzy {

    public static void init() {
        // Write common init code here.
        Constants.LOGGER.info("Initializing, {} {}", Constants.MOD_ID, Constants.MOD_VERSION);

        ModItems.register();
        DynamicFishEntityGenerator.init();
        DynamicFishEntityGenerator.registerEntityAttributes();
        DynamicFishSystem.registerAllFish();
        ModTabRegsitry.register();

    }
}
