package net.vg.fishingfrenzy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.vg.fishingfrenzy.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {
    public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {

        translationBuilder.add("itemgroup.fishing", "Fishing Frenzy");
        translationBuilder.add("config.client.title", "Client Settings");
        translationBuilder.add("config.server.title", "Server Settings");
        translationBuilder.add("config.general.title", "Fishing Frenzy Settings");



        // Example for generated items
        for (Item fishItem : ModItems.FISH_ITEMS) {
            String fishName = Registries.ITEM.getId(fishItem).getPath();
            translationBuilder.add("item.fishingfrenzy." + fishName, capitalize(fishName.replace("_", " ")));
        }


        // Add translations for fish spawn eggs
        for (Item fishSpawnEgg : ModItems.FISH_SPAWN_EGGS) {
            String eggName = Registries.ITEM.getId(fishSpawnEgg).getPath();
            String fishName = eggName.replace("_spawn_egg", "").replace("raw_", "");
            String formattedName = capitalize(fishName.replace("_", " ")) + " Spawn Egg";
            translationBuilder.add("item.fishingfrenzy." + eggName, formattedName);
        }

        for (Item baitItem : ModItems.TARGETED_BAIT_ITEMS) {
            String baitName = Registries.ITEM.getId(baitItem).getPath();
            translationBuilder.add("item.fishingfrenzy." + baitName, capitalize(baitName.replace("_", " ")));
        }

    }

    private String capitalize(String input) {
        String[] words = input.split(" ");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        return capitalized.toString().trim();
    }
}
