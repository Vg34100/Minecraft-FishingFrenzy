package net.vg.fishingfrenzy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.vg.fishingfrenzy.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BAIT, Models.GENERATED);


        // Ocean
        itemModelGenerator.register(ModItems.RAW_ANCHOVY, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SARDINE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_RED_MULLET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_HERRING, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_RED_SNAPPER, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SQUID, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_ALBACORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_HALIBUT, Models.GENERATED);

        itemModelGenerator.register(ModItems.RAW_TUNA, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_OCTOPUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_FLOUNDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TILAPIA, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_LIONFISH, Models.GENERATED);

        // Night Market
        itemModelGenerator.register(ModItems.MIDNIGHT_SQUID, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPOOK_FISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLOBFISH, Models.GENERATED);

        // Ocean (continued)
        itemModelGenerator.register(ModItems.RAW_EEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEA_CUCUMBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUPER_CUCUMBER, Models.GENERATED);

        // River & Mountain
        itemModelGenerator.register(ModItems.RAW_WALLEYE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PERCH, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_CHUB, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_LINGCOD, Models.GENERATED);

        // River (Salmon)
        itemModelGenerator.register(ModItems.RAW_BREAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SMALLMOUTH_BASS, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_RAINBOW_TROUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_PIKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SUNFISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TIGER_TROUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_DORADO, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SHAD, Models.GENERATED);

        itemModelGenerator.register(ModItems.RAW_BLUE_DISCUS, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_CATFISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_WOODSKIP, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_GOBY, Models.GENERATED);

        // Mountain
        itemModelGenerator.register(ModItems.RAW_CARP, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_LARGEMOUTH_BASS, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_STURGEON, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_BULLHEAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_MIDNIGHT_CARP, Models.GENERATED);

        // Desert
        itemModelGenerator.register(ModItems.RAW_SANDFISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCORPION_CARP, Models.GENERATED);

        // Underground
        itemModelGenerator.register(ModItems.RAW_GHOSTFISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBBLESTONE_FISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICE_PIP, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVA_EEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.VOID_SALMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.SLIMEJACK, Models.GENERATED);
    }

}
