package net.vg.fishingfrenzy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.GENERIC_BAIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.DELUXE_BAIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGNET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHALLENGE_BAIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WILD_BAIT, Models.GENERATED);

        for (Item baitItem : ModItems.TARGETED_BAIT_ITEMS) {
            itemModelGenerator.register(baitItem, Models.GENERATED);
        }
        for (Item fishItem : ModItems.FISH_ITEMS) {
            itemModelGenerator.register(fishItem, Models.GENERATED);
        }
    }

}
