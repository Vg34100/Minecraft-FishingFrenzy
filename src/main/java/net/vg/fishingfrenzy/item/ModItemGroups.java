package net.vg.fishingfrenzy.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;

public class ModItemGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FishingFrenzy.MOD_ID, "fishing"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.fishing"))
                    .icon(() -> new ItemStack(Items.FISHING_ROD)).entries((displayContext, entries) -> {

                        entries.add(Items.DIAMOND);
                        entries.add(ModItems.BAIT);
                        entries.add(ModItems.DELUXE_FISHING_ROD);
                    }).build());

    public static void registerItemGroups() {
        FishingFrenzy.LOGGER.info("Registering Item Groups for " + FishingFrenzy.MOD_ID);
    }
}
