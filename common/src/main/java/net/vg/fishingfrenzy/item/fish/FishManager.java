package net.vg.fishingfrenzy.item.fish;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Item;
import net.vg.fishingfrenzy.entity.client.AnchovyModel;

import java.util.*;

public class FishManager {

    public static final List<FishRegistry> FISH_REGISTRIES = new ArrayList<>();
    public static final Map<String, FishRegistry> FISH_REGISTRY_MAP = new HashMap<>();
    public enum ItemType {
        FISH,
        COOKED_FISH,
        BAIT,
        SPAWN_EGG
    }

    public static final EnumMap<ItemType, List<RegistrySupplier<Item>>> ALL_FISH_ITEMS = new EnumMap<>(ItemType.class);
    static {
        ALL_FISH_ITEMS.put(ItemType.FISH, new ArrayList<>());
        ALL_FISH_ITEMS.put(ItemType.COOKED_FISH, new ArrayList<>());
        ALL_FISH_ITEMS.put(ItemType.BAIT, new ArrayList<>());
        ALL_FISH_ITEMS.put(ItemType.SPAWN_EGG, new ArrayList<>());
    }

    public static void registerAllFish() {
        FishRegistry ANCHOVY = new FishRegistry(
                "anchovy",
                new FishPropertiesBuilder()
                        .setPrimaryColor(0x28ccf0)
                        .setSecondaryColor(0x93ffb6), AnchovyModel.class);



    }

    public static void addFishRegistry(FishRegistry fishRegistry) {
        FISH_REGISTRIES.add(fishRegistry);
        FISH_REGISTRY_MAP.put(fishRegistry.getFishName(), fishRegistry);

        RegistrySupplier<Item> fish = fishRegistry.getFishRegistry();
        RegistrySupplier<Item> cookedFish = fishRegistry.getCookedFishRegistry();
        RegistrySupplier<Item> targetedBait = fishRegistry.getTargetBaitRegistry();

//        Item cookedFish = fishRegistry.getCookedFish();
//        Item bait = fishRegistry.getBait();
//        Item spawnEgg = fishRegistry.getSpawnEgg();
//        Item bucketItem = fishRegistry.getBucketItem();

        if (fish != null) {
            ALL_FISH_ITEMS.get(ItemType.FISH).add(fish);
        }
        if (targetedBait != null) {
            ALL_FISH_ITEMS.get(ItemType.BAIT).add(targetedBait);
        }
//        if (spawnEgg != null) {
//            ALL_ITEMS.get(ItemType.SPAWN_EGG).add(spawnEgg);
//        }
        if (cookedFish != null) {
            ALL_FISH_ITEMS.get(ItemType.COOKED_FISH).add(cookedFish);
        }
//        if (bucketItem != null) {
//            ALL_ITEMS.get(ItemType.BUCKET).add(bucketItem);
//        }
    }


    public static List<RegistrySupplier<Item>> getItemsByType(ItemType type) {
        return ALL_FISH_ITEMS.get(type);
    }

}
