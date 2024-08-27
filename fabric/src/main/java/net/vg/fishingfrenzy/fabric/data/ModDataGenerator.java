package net.vg.fishingfrenzy.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.vg.fishingfrenzy.Constants;
import net.vg.fishingfrenzy.item.fish.FishManager;
import net.vg.fishingfrenzy.registry.ModItems;
import net.vg.fishingfrenzy.util.ModTags;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelGenerator::new);

        pack.addProvider(ModItemTagProvider::new);

        pack.addProvider(ModRecipeProvider::new);
    }

    private static class ModModelGenerator extends FabricModelProvider {

        public ModModelGenerator(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

        }

        @Override
        public void generateItemModels(ItemModelGenerators itemModelGenerator) {
            // Baits
            ModItems.BAIT_ITEMS.forEach(bait -> itemModelGenerator.generateFlatItem(bait.get(), ModelTemplates.FLAT_ITEM));

            // Fishes
            FishManager.getItemsByType(FishManager.ItemType.FISH).forEach(fish -> itemModelGenerator.generateFlatItem(fish.get(), ModelTemplates.FLAT_ITEM));
            FishManager.getItemsByType(FishManager.ItemType.COOKED_FISH).forEach(cookedfish -> itemModelGenerator.generateFlatItem(cookedfish.get(), ModelTemplates.FLAT_ITEM));

            ModelTemplate baitModel = new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "item/template_target_bait")), Optional.empty());
            FishManager.getItemsByType(FishManager.ItemType.BAIT).forEach(bait -> itemModelGenerator.generateFlatItem(bait.get(), baitModel));
        }
    }

    public static class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
        public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
            super(output, completableFuture);
        }



        @Override
        protected void addTags(HolderLookup.Provider wrapperLookup) {
            getOrCreateTagBuilder(ItemTags.FISHING_ENCHANTABLE)
                    .add(ModItems.DELUXE_FISHING_ROD.get());
            ModItems.BAIT_ITEMS.forEach(bait ->
                    getOrCreateTagBuilder(ModTags.Items.BAIT).add(bait.get()));

            FishManager.getItemsByType(FishManager.ItemType.FISH).forEach(fish ->
                    getOrCreateTagBuilder(ItemTags.FISHES).add(fish.get()));
            FishManager.getItemsByType(FishManager.ItemType.COOKED_FISH).forEach(cookedfish ->
                    getOrCreateTagBuilder(ItemTags.FISHES).add(cookedfish.get()));
        }
    }

    public static class ModRecipeProvider extends FabricRecipeProvider {

        public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        public void buildRecipes(RecipeOutput exporter) {
            FishManager.FISH_REGISTRIES.forEach(fishRegistry -> {
                RecipeProvider.oreSmelting(exporter, List.of(fishRegistry.getFishRegistry().get()), RecipeCategory.FOOD, fishRegistry.getCookedFishRegistry().get(), 0.35F, 200, fishRegistry.getFishName());
                RecipeProvider.simpleCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, fishRegistry.getFishRegistry().get(), fishRegistry.getCookedFishRegistry().get(), 0.35F);
                RecipeProvider.simpleCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, fishRegistry.getFishRegistry().get(), fishRegistry.getCookedFishRegistry().get(), 0.35F);
            });

        }
    }


}
