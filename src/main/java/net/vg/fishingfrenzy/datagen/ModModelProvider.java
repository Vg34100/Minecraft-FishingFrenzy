package net.vg.fishingfrenzy.datagen;

import com.terraformersmc.modmenu.util.mod.Mod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.item.ModItems;

import java.util.Optional;

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

//        itemModelGenerator.register(ModItems.MANUAL_DUMMY_2_SPAWN_EGG,
//                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
//        for (Item fishSpawnEgg : ModItems.FISH_SPAWN_EGGS) {
//            itemModelGenerator.register(fishSpawnEgg,
//                    new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
//        }
//        // Manually add models for the dummy spawn eggs
//        itemModelGenerator.register(ModItems.MANUAL_DUMMY_1_SPAWN_EGG,
//                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
        // Create a single Model instance for the spawn egg template
        Model spawnEggModel = new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty());

        // Register each spawn egg with the same model instance
        for (Item fishSpawnEgg : ModItems.FISH_SPAWN_EGGS) {
            Identifier spawnEggId = Registries.ITEM.getId(fishSpawnEgg);
            itemModelGenerator.register(fishSpawnEgg, spawnEggModel);
            FishingFrenzy.LOGGER.info("Registered model for spawn egg: " + spawnEggId.getPath());
        }


        itemModelGenerator.register(ModItems.TESTBAIT,
                    new Model(Optional.of(Identifier.of(FishingFrenzy.MOD_ID, "item/template_target_bait")), Optional.empty()));

    }

}
