package net.vg.fishingfrenzy.client;

import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.vg.fishingfrenzy.mixin.ItemPropertiesAccessor;
import net.vg.fishingfrenzy.registry.ModItems;

public class ModClientEvents {
    public static void registerCastTexture() {
        ItemPropertiesAccessor.invokeRegister(ModItems.DELUXE_FISHING_ROD.get(), ResourceLocation.withDefaultNamespace("cast"), (stack, level, entity, i) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                boolean isMainhand = entity.getMainHandItem() == stack;
                boolean isOffHand = entity.getOffhandItem() == stack;
                if (entity.getMainHandItem().getItem() instanceof FishingRodItem) {
                    isOffHand = false;
                }
                return (isMainhand || isOffHand) && entity instanceof Player && ((Player) entity).fishing != null ? 1.0F : 0.0F;
            }
        });
    }


}