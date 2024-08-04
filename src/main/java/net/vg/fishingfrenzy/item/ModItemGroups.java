package net.vg.fishingfrenzy.item;

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
import net.vg.fishingfrenzy.management.FishManager;

import java.util.List;

public class ModItemGroups {
    public static final ItemGroup FISHING_FRENZY_GROUP;
    public static final ItemGroup FISHES_GROUP;
    public static final ItemGroup BAIT_GROUP;
    public static final ItemGroup FISHES_SPAWN_GROUP;

    static {
        FISHING_FRENZY_GROUP = registerGroup("fishing_frenzy_all", ModItems.DELUXE_FISHING_ROD,
                ModItems.FISHING_RODS,
                ModItems.FISH_ITEMS, FishManager.getItemsByType(FishManager.ItemType.FISH),
                ModItems.BAIT_ITEMS, FishManager.getItemsByType(FishManager.ItemType.BAIT),
                ModItems.FISH_SPAWN_EGGS, FishManager.getItemsByType(FishManager.ItemType.SPAWN_EGG));
        FISHES_GROUP = registerGroup("fishing_frenzy_fishes", ModItems.RAW_CARP,
                ModItems.FISH_ITEMS, FishManager.getItemsByType(FishManager.ItemType.FISH));
        BAIT_GROUP = registerGroup("fishing_frenzy_bait", ModItems.DELUXE_BAIT,
                ModItems.BAIT_ITEMS, FishManager.getItemsByType(FishManager.ItemType.BAIT));
        FISHES_SPAWN_GROUP = registerGroup("fishing_frenzy_fish_spawn_eggs", ModItems.FISH_SPAWN_EGGS.getFirst(),
                ModItems.FISH_SPAWN_EGGS, FishManager.getItemsByType(FishManager.ItemType.SPAWN_EGG));

    }

    public static void registerItemGroups() {
        FishingFrenzy.LOGGER.info("Registering Item Groups for " + FishingFrenzy.MOD_ID);
    }

    /**
     * Registers an item group with the given name, icon, and multiple lists of items.
     *
     * @param name  the name of the item group
     * @param icon  the item to be used as the icon for the item group
     * @param itemLists the lists of items to be included in the item group
     * @return the registered ItemGroup
     */
    @SafeVarargs
    private static ItemGroup registerGroup(String name, Item icon, List<Item>... itemLists) {
        return Registry.register(Registries.ITEM_GROUP,
                Identifier.of(FishingFrenzy.MOD_ID, name),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + name))
                        .icon(() -> new ItemStack(icon))
                        .entries((displayContext, entries) -> {
                            for (List<Item> itemList : itemLists) {
                                for (Item item : itemList) {
                                    entries.add(new ItemStack(item));
                                }
                            }
                        })
                        .build());
    }

    private static ItemGroup registerGroup(String name, Item icon, EntriesAdder adder) {
        return Registry.register(Registries.ITEM_GROUP,
                Identifier.of(FishingFrenzy.MOD_ID, name),
                FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + name))
                        .icon(() -> new ItemStack(icon))
                        .entries(adder::addEntries)
                        .build());
    }

    @FunctionalInterface
    private interface EntriesAdder {
        void addEntries(ItemGroup.DisplayContext displayContext, ItemGroup.Entries entries);
    }

}
