package net.vg.fishingfrenzy.world;

import net.vg.fishingfrenzy.management.FishManager;

import java.util.List;

public class ModEntitySpawns {

    public static void addEntitySpawns() {
        FishManager.registerAllFishSpawns();
    }

    private static boolean isWithinTimeRange(long currentTime, int minTime, int maxTime) {
        currentTime = currentTime % 24000;
        if (minTime <= maxTime) {
            // Normal case: minTime to maxTime within the same day
            return currentTime >= minTime && currentTime <= maxTime;
        } else {
            // Across midnight case: from minTime to 24000 OR from 0 to maxTime
            return currentTime >= minTime || currentTime <= maxTime;
        }
    }
}
