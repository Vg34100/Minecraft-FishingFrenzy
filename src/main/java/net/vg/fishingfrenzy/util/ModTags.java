package net.vg.fishingfrenzy.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.vg.fishingfrenzy.FishingFrenzy;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(FishingFrenzy.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> BAIT =
                createTag("bait");

        public static final TagKey<Item> TARGET_BAIT =
                createTag("target_bait");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(FishingFrenzy.MOD_ID, name));
        }
    }
}