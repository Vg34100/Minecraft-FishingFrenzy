package net.vg.fishingfrenzy.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.vg.fishingfrenzy.Constants;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.client.ModClientEvents;

@Mod(Constants.MOD_ID)
public final class FishingFrenzyNeoForge {
    public FishingFrenzyNeoForge(IEventBus modEventBus) {
        // Run our common setup.
        FishingFrenzy.init();

        // Register client stuff
        // Register the client setup event
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(this::clientSetup);
        }    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ModClientEvents::registerCastTexture);
    }
}
