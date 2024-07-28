package net.vg.fishingfrenzy.item;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.item.custom.BaitItem;
import net.vg.fishingfrenzy.item.custom.DeluxeFishingRodItem;

public class ModItems {

    public static final Item BAIT = new BaitItem(new Item.Settings().maxCount(64));
    public static final Item DELUXE_FISHING_ROD = new DeluxeFishingRodItem(new Item.Settings().maxDamage(64));



    public static void registerItems() {
        FishingFrenzy.LOGGER.info("Registering Items for: " + FishingFrenzy.MOD_ID);

        Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, "bait"), BAIT);
        Registry.register(Registries.ITEM, Identifier.of(FishingFrenzy.MOD_ID, "deluxe_fishing_rod"), DELUXE_FISHING_ROD);

    }
}
