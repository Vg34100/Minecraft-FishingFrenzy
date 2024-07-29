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
                        entries.add(ModItems.BAIT);
                        entries.add(ModItems.DELUXE_FISHING_ROD);

                        // Ocean
                        entries.add(ModItems.RAW_ANCHOVY);
                        entries.add(ModItems.RAW_SARDINE);
                        entries.add(ModItems.RAW_RED_MULLET);
                        entries.add(ModItems.RAW_HERRING);
                        entries.add(ModItems.RAW_RED_SNAPPER);
                        entries.add(ModItems.RAW_SQUID);
                        entries.add(ModItems.RAW_ALBACORE);
                        entries.add(ModItems.RAW_HALIBUT);
                        entries.add(ModItems.RAW_TUNA);
                        entries.add(ModItems.RAW_OCTOPUS);
                        entries.add(ModItems.RAW_FLOUNDER);
                        entries.add(ModItems.RAW_TILAPIA);
                        entries.add(ModItems.RAW_LIONFISH);

                        // Night Market
                        entries.add(ModItems.MIDNIGHT_SQUID);
                        entries.add(ModItems.SPOOK_FISH);
                        entries.add(ModItems.BLOBFISH);

                        // Ocean (continued)
                        entries.add(ModItems.RAW_EEL);
                        entries.add(ModItems.SEA_CUCUMBER);
                        entries.add(ModItems.SUPER_CUCUMBER);

                        // River & Mountain
                        entries.add(ModItems.RAW_WALLEYE);
                        entries.add(ModItems.RAW_PERCH);
                        entries.add(ModItems.RAW_CHUB);
                        entries.add(ModItems.RAW_LINGCOD);

                        // River (Salmon)
                        entries.add(ModItems.RAW_BREAM);
                        entries.add(ModItems.RAW_SMALLMOUTH_BASS);
                        entries.add(ModItems.RAW_RAINBOW_TROUT);
                        entries.add(ModItems.RAW_PIKE);
                        entries.add(ModItems.RAW_SUNFISH);
                        entries.add(ModItems.RAW_TIGER_TROUT);
                        entries.add(ModItems.RAW_DORADO);
                        entries.add(ModItems.RAW_SHAD);

                        entries.add(ModItems.RAW_BLUE_DISCUS);
                        entries.add(ModItems.RAW_CATFISH);
                        entries.add(ModItems.RAW_WOODSKIP);
                        entries.add(ModItems.RAW_GOBY);

                        // Mountain
                        entries.add(ModItems.RAW_CARP);
                        entries.add(ModItems.RAW_LARGEMOUTH_BASS);
                        entries.add(ModItems.RAW_STURGEON);
                        entries.add(ModItems.RAW_BULLHEAD);
                        entries.add(ModItems.RAW_MIDNIGHT_CARP);

                        // Desert
                        entries.add(ModItems.RAW_SANDFISH);
                        entries.add(ModItems.SCORPION_CARP);

                        // Underground
                        entries.add(ModItems.RAW_GHOSTFISH);
                        entries.add(ModItems.COBBLESTONE_FISH);
                        entries.add(ModItems.ICE_PIP);
                        entries.add(ModItems.LAVA_EEL);
                        entries.add(ModItems.VOID_SALMON);
                        entries.add(ModItems.SLIMEJACK);                    }).build());

    public static void registerItemGroups() {
        FishingFrenzy.LOGGER.info("Registering Item Groups for " + FishingFrenzy.MOD_ID);
    }
}
