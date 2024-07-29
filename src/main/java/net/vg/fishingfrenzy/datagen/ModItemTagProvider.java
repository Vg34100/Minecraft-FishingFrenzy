package net.vg.fishingfrenzy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.vg.fishingfrenzy.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.FISHING_ENCHANTABLE)
                .add(ModItems.DELUXE_FISHING_ROD);


        getOrCreateTagBuilder(ItemTags.FISHES)
                .add(ModItems.RAW_ANCHOVY)
                .add(ModItems.RAW_SARDINE)
                .add(ModItems.RAW_RED_MULLET)
                .add(ModItems.RAW_HERRING)
                .add(ModItems.RAW_RED_SNAPPER)
                .add(ModItems.RAW_SQUID)
                .add(ModItems.RAW_ALBACORE)
                .add(ModItems.RAW_HALIBUT)
                .add(ModItems.RAW_TUNA)
                .add(ModItems.RAW_OCTOPUS)
                .add(ModItems.RAW_FLOUNDER)
                .add(ModItems.RAW_TILAPIA)
                .add(ModItems.RAW_LIONFISH)
                .add(ModItems.MIDNIGHT_SQUID)
                .add(ModItems.SPOOK_FISH)
                .add(ModItems.BLOBFISH)
                .add(ModItems.RAW_EEL)
                .add(ModItems.SEA_CUCUMBER)
                .add(ModItems.SUPER_CUCUMBER)
                .add(ModItems.RAW_WALLEYE)
                .add(ModItems.RAW_PERCH)
                .add(ModItems.RAW_CHUB)
                .add(ModItems.RAW_LINGCOD)
                .add(ModItems.RAW_BREAM)
                .add(ModItems.RAW_SMALLMOUTH_BASS)
                .add(ModItems.RAW_RAINBOW_TROUT)
                .add(ModItems.RAW_PIKE)
                .add(ModItems.RAW_SUNFISH)
                .add(ModItems.RAW_TIGER_TROUT)
                .add(ModItems.RAW_DORADO)
                .add(ModItems.RAW_SHAD)
                .add(ModItems.RAW_BLUE_DISCUS)
                .add(ModItems.RAW_CATFISH)
                .add(ModItems.RAW_WOODSKIP)
                .add(ModItems.RAW_GOBY)
                .add(ModItems.RAW_CARP)
                .add(ModItems.RAW_LARGEMOUTH_BASS)
                .add(ModItems.RAW_STURGEON)
                .add(ModItems.RAW_BULLHEAD)
                .add(ModItems.RAW_MIDNIGHT_CARP)
                .add(ModItems.RAW_SANDFISH)
                .add(ModItems.SCORPION_CARP)
                .add(ModItems.RAW_GHOSTFISH)
                .add(ModItems.COBBLESTONE_FISH)
                .add(ModItems.ICE_PIP)
                .add(ModItems.LAVA_EEL)
                .add(ModItems.VOID_SALMON)
                .add(ModItems.SLIMEJACK);
    }
}
