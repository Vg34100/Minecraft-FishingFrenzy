package net.vg.fishingfrenzy.registry;

import dev.architectury.platform.Mod;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.vg.fishingfrenzy.Constants;
import net.vg.fishingfrenzy.item.fish.FishManager;

public class ModTabRegsitry {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Constants.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> FISHING_FRENZY_GROUP = TABS.register(
            "fishing_frenzy_all",
            () -> CreativeTabRegistry.create(builder -> {
                builder.title(Component.translatable("category.fishing_frenzy_all"));
                builder.icon(() -> new ItemStack(Items.FISHING_ROD));
                builder.displayItems((parameters, output) -> {
                    ModItems.FISHING_RODS.forEach(rod -> output.accept(rod.get()));
                    ModItems.BAIT_ITEMS.forEach(bait -> output.accept(bait.get()));
                    FishManager.getItemsByType(FishManager.ItemType.FISH).forEach(fish -> output.accept(fish.get()));
                    FishManager.getItemsByType(FishManager.ItemType.COOKED_FISH).forEach(fish -> output.accept(fish.get()));

                    FishManager.getItemsByType(FishManager.ItemType.BAIT).forEach(bait -> output.accept(bait.get()));


                });
            })
    );

    public static final RegistrySupplier<CreativeModeTab> FISHES_GROUP = TABS.register(
            "fishing_frenzy_fishes",
            () -> CreativeTabRegistry.create(builder -> {
                builder.title(Component.translatable("category.fishing_frenzy_fishes"));
                builder.icon(() -> new ItemStack(Items.COD));
                builder.displayItems((parameters, output) -> {
                    FishManager.getItemsByType(FishManager.ItemType.FISH).forEach(fish -> output.accept(fish.get()));
                });
            })
    );

    public static final RegistrySupplier<CreativeModeTab> BAIT_GROUP = TABS.register(
            "fishing_frenzy_bait",
            () -> CreativeTabRegistry.create(builder -> {
                builder.title(Component.translatable("category.fishing_frenzy_bait"));
                builder.icon(() -> new ItemStack(Items.STICK));
                builder.displayItems((parameters, output) -> {
                    ModItems.BAIT_ITEMS.forEach(bait -> output.accept(bait.get()));
                    FishManager.getItemsByType(FishManager.ItemType.BAIT).forEach(bait -> output.accept(bait.get()));

                });
            })
    );

//    public static final RegistrySupplier<CreativeModeTab> BAIT_GROUP = TABS.register(
//            "fishing_frenzy_bait", // Tab ID
//            () -> CreativeTabRegistry.create(
//                    Component.translatable("category.fishing_frenzy_bait"), // Tab Name
//                    () -> new ItemStack(ModItems.WORM_BAIT) // Icon
//            )
//    );

//    public static final RegistrySupplier<CreativeModeTab> FISHES_SPAWN_GROUP = TABS.register(
//            "fishing_frenzy_fish_spawn_eggs", // Tab ID
//            () -> CreativeTabRegistry.create(
//                    Component.translatable("category.fishing_frenzy_fish_spawn_eggs"), // Tab Name
//                    () -> new ItemStack(Items.SALMON_SPAWN_EGG) // Icon
//            )
//    );

    public static void register() {
        TABS.register();
    }
}
