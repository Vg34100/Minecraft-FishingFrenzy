package net.vg.fishingfrenzy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.item.custom.FishRegistry;
import net.vg.fishingfrenzy.management.FishManager;

import java.util.concurrent.CompletableFuture;

public class ModLangProvider extends FabricLanguageProvider {
    public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {

        // Item groups
        translationBuilder.add("itemgroup.fishing_frenzy_all", "Fishing Frenzy");
        translationBuilder.add("itemgroup.fishing_frenzy_fishes", "Frenzy Fishes");
        translationBuilder.add("itemgroup.fishing_frenzy_bait", "Frenzy Baits");
        translationBuilder.add("itemgroup.fishing_frenzy_fish_spawn_eggs", "Frenzy Spawn Eggs");

        // Configuration screens
        translationBuilder.add("config.client.title", "Client Settings");
        translationBuilder.add("config.server.title", "Server Settings");
        translationBuilder.add("config.general.title", "Fishing Frenzy Settings");

        // Tags
        translationBuilder.add("tag.item.fishingfrenzy.bait", "Bait");
        translationBuilder.add("tag.item.fishingfrenzy.target_bait", "Targeted Bait");


        // ModItems
        for (Item fishing_rod : ModItems.FISHING_RODS) {
            String fishingRodName = Registries.ITEM.getId(fishing_rod).getPath();
            translationBuilder.add(fishing_rod.getTranslationKey(), capitalize(fishingRodName.replace("_", " ")));
        }

        for (Item bait : ModItems.BAIT_ITEMS) {
            String baitName = Registries.ITEM.getId(bait).getPath();
            translationBuilder.add(bait.getTranslationKey(), capitalize(baitName.replace("_", " ")));
        }

        // Fish Manager Items
        FishManager.registerTranslations(translationBuilder);
    }

    // Helper function to capitalize strings
    private String capitalize(String str) {
        if (str.isEmpty()) {
            return str;
        }
        String[] words = str.split(" ");
        StringBuilder capitalizedWords = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedWords.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return capitalizedWords.toString().trim();
    }
}
