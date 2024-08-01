package net.vg.fishingfrenzy.item;

import com.terraformersmc.modmenu.util.mod.Mod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;

public class ModItemGroups {

    public static final ItemGroup FISHES_SPAWN_GROUP;
    public static final ItemGroup BAIT_GROUP;
    public static final ItemGroup FISHES_GROUP;
    public static final ItemGroup FISHING_FRENZY_GROUP;



    static {
        FISHING_FRENZY_GROUP = Registry.register(Registries.ITEM_GROUP,
                Identifier.of(FishingFrenzy.MOD_ID, "fishing_frenzy"),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fishing"))
                        .icon(() -> new ItemStack(Items.FISHING_ROD)).entries((displayContext, entries) -> {
                            // Rods
                            entries.add(ModItems.DELUXE_FISHING_ROD);

                            // Bait
                            entries.add(ModItems.GENERIC_BAIT);
                            entries.add(ModItems.DELUXE_BAIT);
                            entries.add(ModItems.MAGNET);
                            entries.add(ModItems.CHALLENGE_BAIT);
                            entries.add(ModItems.WILD_BAIT);

                            for (Item baitItem : ModItems.TARGETED_BAIT_ITEMS) {
                                entries.add(baitItem);
                            }

                            for (Item fishItem : ModItems.FISH_ITEMS) {
                                entries.add(fishItem);
                            }

                            for (Item fishSpawnEgg : ModItems.FISH_SPAWN_EGGS) {
                                entries.add(fishSpawnEgg);
                            }

                        }).build());

        FISHES_GROUP = Registry.register(Registries.ITEM_GROUP,
                Identifier.of(FishingFrenzy.MOD_ID, "fishes"),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fishes"))
                        .icon(() -> new ItemStack(ModItems.RAW_CARP)).entries((displayContext, entries) -> {

                            for (Item fishItem : ModItems.FISH_ITEMS) {
                                entries.add(fishItem);
                            }

                        }).build());

        FISHES_SPAWN_GROUP = Registry.register(Registries.ITEM_GROUP,
                Identifier.of(FishingFrenzy.MOD_ID, "fish_spawn_eggs"),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fish_spawn_eggs"))
                        .icon(() -> new ItemStack(ModItems.FISH_SPAWN_EGGS.getFirst())).entries((displayContext, entries) -> {

                            for (Item fishSpawnEgg : ModItems.FISH_SPAWN_EGGS) {
                                entries.add(fishSpawnEgg);
                            }

                        }).build());
        BAIT_GROUP = Registry.register(Registries.ITEM_GROUP,
                Identifier.of(FishingFrenzy.MOD_ID, "bait"),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup.bait"))
                        .icon(() -> new ItemStack(ModItems.DELUXE_BAIT)).entries((displayContext, entries) -> {

                            // Bait
                            entries.add(ModItems.GENERIC_BAIT);
                            entries.add(ModItems.DELUXE_BAIT);
                            entries.add(ModItems.MAGNET);
                            entries.add(ModItems.CHALLENGE_BAIT);
                            entries.add(ModItems.WILD_BAIT);

                            for (Item baitItem : ModItems.TARGETED_BAIT_ITEMS) {
                                entries.add(baitItem);
                            }

                        }).build());
    }

    public static void registerItemGroups() {
        FishingFrenzy.LOGGER.info("Registering Item Groups for " + FishingFrenzy.MOD_ID);
    }
}
