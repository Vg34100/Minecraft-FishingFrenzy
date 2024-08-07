package net.vg.fishingfrenzy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.item.ModItems;
import net.vg.fishingfrenzy.management.FishManager;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DELUXE_FISHING_ROD, 1)
                .pattern("  i")
                .pattern(" is")
                .pattern("i s")
                .input('i', Items.IRON_INGOT)
                .input('s', Items.STRING)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.DELUXE_FISHING_ROD)));

        // Generic
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GENERIC_BAIT, 4)
                .input(Items.DIRT)
                .input(Items.STRING)
                .input(ItemTags.LEAVES)
                .criterion(RecipeProvider.hasItem(Items.DIRT), RecipeProvider.conditionsFromItem(Items.DIRT))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.GENERIC_BAIT)));

        // Deluxe
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DELUXE_BAIT, 2)
                .input(ModItems.GENERIC_BAIT)
                .input(Items.GOLD_NUGGET)
                .input(Items.GOLD_NUGGET)
                .input(ItemTags.FLOWERS)
                .criterion(RecipeProvider.hasItem(ModItems.GENERIC_BAIT), RecipeProvider.conditionsFromItem(ModItems.GENERIC_BAIT))
                .criterion(RecipeProvider.hasItem(Items.GOLD_NUGGET), RecipeProvider.conditionsFromItem(Items.GOLD_NUGGET))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.DELUXE_BAIT)));

        // Wild
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WILD_BAIT, 2)
                .input(ModItems.GENERIC_BAIT)
                .input(Items.STRING)
                .input(Items.SWEET_BERRIES)
                .input(ItemTags.LEAVES)
                .criterion(RecipeProvider.hasItem(ModItems.GENERIC_BAIT), RecipeProvider.conditionsFromItem(ModItems.GENERIC_BAIT))
                .criterion(RecipeProvider.hasItem(Items.STRING), RecipeProvider.conditionsFromItem(Items.STRING))
                .criterion(RecipeProvider.hasItem(Items.SWEET_BERRIES), RecipeProvider.conditionsFromItem(Items.SWEET_BERRIES))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.WILD_BAIT)));

        // Magic
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MAGIC_BAIT, 2)
                .input(ModItems.GENERIC_BAIT)
                .input(Items.GLOWSTONE_DUST)
                .input(Items.GLOWSTONE_DUST)
                .input(Items.GLOW_BERRIES)
                .criterion(RecipeProvider.hasItem(ModItems.GENERIC_BAIT), RecipeProvider.conditionsFromItem(ModItems.GENERIC_BAIT))
                .criterion(RecipeProvider.hasItem(Items.GLOWSTONE_DUST), RecipeProvider.conditionsFromItem(Items.GLOWSTONE_DUST))
                .criterion(RecipeProvider.hasItem(Items.GLOW_BERRIES), RecipeProvider.conditionsFromItem(Items.GLOW_BERRIES))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.MAGIC_BAIT)));

        // Magnet
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MAGNET, 2)
                .input(Items.IRON_NUGGET)
                .input(Items.IRON_NUGGET)
                .input(Items.STRING)
                .input(Items.REDSTONE)
                .criterion(RecipeProvider.hasItem(Items.IRON_NUGGET), RecipeProvider.conditionsFromItem(Items.IRON_NUGGET))
                .criterion(RecipeProvider.hasItem(Items.STRING), RecipeProvider.conditionsFromItem(Items.STRING))
                .criterion(RecipeProvider.hasItem(Items.REDSTONE), RecipeProvider.conditionsFromItem(Items.REDSTONE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.MAGNET)));

        // Challenge
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHALLENGE_BAIT, 2)
                .input(ModItems.GENERIC_BAIT)
                .input(Items.BONE)
                .input(Items.BONE)
                .input(Items.SPIDER_EYE)
                .criterion(RecipeProvider.hasItem(ModItems.GENERIC_BAIT), RecipeProvider.conditionsFromItem(ModItems.GENERIC_BAIT))
                .criterion(RecipeProvider.hasItem(Items.BONE), RecipeProvider.conditionsFromItem(Items.BONE))
                .criterion(RecipeProvider.hasItem(Items.SPIDER_EYE), RecipeProvider.conditionsFromItem(Items.SPIDER_EYE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.CHALLENGE_BAIT)));


        FishManager.registerRecipes(this, exporter);
    }
}
