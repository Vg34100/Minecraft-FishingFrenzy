package net.vg.fishingfrenzy.datagen;

import com.terraformersmc.modmenu.util.mod.Mod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.FISHING_ENCHANTABLE)
                .add(ModItems.DELUXE_FISHING_ROD);

        getOrCreateTagBuilder(ModTags.Items.BAITS)
                .add(ModItems.GENERIC_BAIT)
                .add(ModItems.DELUXE_BAIT)
                .add(ModItems.MAGNET)
                .add(ModItems.CHALLENGE_BAIT)
                .add(ModItems.WILD_BAIT);

        for (Item baitItem : ModItems.TARGETED_BAIT_ITEMS) {
            getOrCreateTagBuilder(ModTags.Items.BAITS).add(baitItem);
        }

        for (Item fishItem : ModItems.TARGETED_BAIT_ITEMS) {
            getOrCreateTagBuilder(ItemTags.FISHES).add(fishItem);
        }
    }
}
