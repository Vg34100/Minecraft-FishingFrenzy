package net.vg.fishingfrenzy.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;

public class ModTags {
    public static class Blocks {

//        public static final TagKey<Block> METAL_DETECTOR_DETECTABLE_BLOCKS =
//                createTag("metal_detector_detectable_blocks");
//        public static final TagKey<Block> RUBY_ORES =
//                createTag("ruby_ores");



        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(FishingFrenzy.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> BAITS =
                createTag("baits");

//        public static final TagKey<Item> SHOVELS_ABOVE_DIAMOND =
//                createTag("shovels_above_diamond");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name));
        }
    }
}