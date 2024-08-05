package net.vg.fishingfrenzy;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.management.FishManager;

public class FishingFrenzyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        ModEntityClientInitializer.registerEntities();

//        for (Item baitItem : ModItems.TARGETED_BAIT_ITEMS) {
//            ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
//                    ColorHelper.Argb.fullAlpha(((TargetBaitItem)stack.getItem()).getColor(tintIndex)), baitItem);
//        }

        //ModItems.FISH_1.registerItemColorProviders();
        FishManager.registerItemColorProviders();


        // Render the cast texture
        ModelPredicateProviderRegistry.register(ModItems.DELUXE_FISHING_ROD, Identifier.of("cast"), (stack, world, entity, seed) -> {
            boolean bl2;
            if (entity == null) {
                return 0.0f;
            }
            boolean bl = entity.getMainHandStack() == stack;
            bl2 = entity.getOffHandStack() == stack;
            if (entity.getMainHandStack().getItem() instanceof FishingRodItem) {
                bl2 = false;
            }
            return (bl || bl2) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishHook != null ? 1.0f : 0.0f;
        });

    }
}
