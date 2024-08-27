package net.vg.fishingfrenzy.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.vg.fishingfrenzy.client.ModClientEvents;

public final class FishingFrenzyFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        ModClientEvents.registerCastTexture();


    }
}
