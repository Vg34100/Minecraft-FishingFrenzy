package net.vg.fishingfrenzy.fabric;

import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.impl.client.indigo.renderer.helper.ColorHelper;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.level.ColorResolver;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.item.fish.FishManager;
import net.vg.fishingfrenzy.registry.ModItems;

public final class FishingFrenzyFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

//        FishManager.getItemsByType(FishManager.ItemType.BAIT).forEach(targetBait ->
//                ColorProviderRegistry.ITEM.register(((itemStack, i) -> ColorHandlerRegistry)));
//        ColorProviderRegistry.ITEM.register();

        // Run our common setup.
        FishingFrenzy.init();
    }
}
