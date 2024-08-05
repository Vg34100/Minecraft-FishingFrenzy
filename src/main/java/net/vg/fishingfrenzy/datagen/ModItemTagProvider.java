package net.vg.fishingfrenzy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.management.FishManager;
import net.vg.fishingfrenzy.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    public void addToTag(TagKey<Item> tagKey, Item item) {
        getOrCreateTagBuilder(tagKey)
                .add(item);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.FISHING_ENCHANTABLE)
                .add(ModItems.DELUXE_FISHING_ROD);

        getOrCreateTagBuilder(ModTags.Items.BAIT)
                .add(ModItems.GENERIC_BAIT)
                .add(ModItems.DELUXE_BAIT)
                .add(ModItems.MAGNET)
                .add(ModItems.CHALLENGE_BAIT)
                .add(ModItems.WILD_BAIT)
                .add(ModItems.MAGIC_BAIT);

        FishManager.registerItemTags(this);

    }
}
