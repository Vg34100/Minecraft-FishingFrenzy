package net.vg.fishingfrenzy.item.fish;

import com.mojang.datafixers.util.Pair;
import dev.architectury.platform.Platform;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.architectury.utils.Env;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.vg.fishingfrenzy.Constants;
import net.vg.fishingfrenzy.entity.mob.CustomBreedableSchoolingFishEntity;
import net.vg.fishingfrenzy.item.bait.BaitPropertiesBuilder;
import net.vg.fishingfrenzy.item.bait.targetbait.TargetBaitItem;
import net.vg.fishingfrenzy.registry.DynamicFishEntityGenerator;
import net.vg.fishingfrenzy.registry.DynamicFishSystem;
import net.vg.fishingfrenzy.registry.ModItems;
import net.vg.fishingfrenzy.util.StatusEffectEntry;

import java.util.List;

public class FishRegistry {
    private final String fish_name;
    private final RegistrySupplier<Item> fish;
    private final RegistrySupplier<Item> cookedfish;

    private final RegistrySupplier<Item> bait;

    private final RegistrySupplier<EntityType<CustomBreedableSchoolingFishEntity>> fishEntityType;
    private final ModelLayerLocation modelLayer;
    private final Class<? extends EntityModel<CustomBreedableSchoolingFishEntity>> modelClass;

    public FishRegistry(String name, FishProperties properties, Class<? extends EntityModel<CustomBreedableSchoolingFishEntity>> modelClass) {
        this.fish_name = name;
        this.modelClass = modelClass;

        FoodProperties foodProperties = createFoodComponent(properties.getFoodAttributes(), properties.isSnack(), properties.getStatusEffects());
        FoodProperties cookedFoodProperties = createFoodComponent(properties.getCookedFoodAttributes(), properties.isSnack(), properties.getCookedStatusEffects());

        this.fishEntityType = DynamicFishEntityGenerator.generateFishEntity(this);


        this.fish = ModItems.ITEMS.register(name, () -> new FishItem(new Item.Properties().food(foodProperties), properties));
        this.cookedfish  = ModItems.ITEMS.register("cooked_" + name, () -> new Item(new Item.Properties().food(cookedFoodProperties)));


        this.bait = ModItems.ITEMS.register(name + "_bait", () -> new TargetBaitItem(properties.getPrimaryColor(), properties.getSecondaryColor(), new Item.Properties(), new BaitPropertiesBuilder().setTargetedFish(fish.get())));

        if (Platform.getEnvironment() == Env.CLIENT) {
            ColorHandlerRegistry.registerItemColors((stack, tintIndex) -> {
                if (stack.getItem() instanceof TargetBaitItem targetBaitItem) {
                    return targetBaitItem.getColor(tintIndex) | 0xFF000000; // Full alpha
                }
                return 0xFFFFFFFF; // Default to white if not a TargetBaitItem
            }, this.bait);
        }

        if (modelClass != null) {
            this.modelLayer = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), "main");
            DynamicFishSystem.registerFish(this);
        } else {
            this.modelLayer = null;
            Constants.LOGGER.info("ModelClass found null for fish: {}", name);
        }

        FishManager.addFishRegistry(this);
    }

    // Handles ModFoodComponents
    private FoodProperties createFoodComponent(Pair<Integer, Float> foodAttributes, boolean snack, List<StatusEffectEntry> statusEffects) {
        FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(foodAttributes.getFirst()).saturationModifier(foodAttributes.getSecond());
        if (snack) {
            builder.fast();
        }
        for (StatusEffectEntry entry : statusEffects) {
            builder.effect(new MobEffectInstance(entry.effect(), entry.duration(), entry.amplifier()), entry.chance());
        }
        return builder.build();
    }
    public String getFishName() {
        return fish_name;
    }
    public RegistrySupplier<Item> getFishRegistry() {
        return fish;
    }
    public RegistrySupplier<Item> getCookedFishRegistry() {
        return cookedfish;
    }
    public RegistrySupplier<Item> getTargetBaitRegistry() {
        return bait;
    }

    public RegistrySupplier<EntityType<CustomBreedableSchoolingFishEntity>> getFishEntityType() {
        return fishEntityType;
    }
    public EntityType<CustomBreedableSchoolingFishEntity> getFishEntityTypeDirectly() {
        return fishEntityType.get();
    }


    public ModelLayerLocation getModelLayer() {
        return modelLayer;
    }

    public Class<? extends EntityModel<CustomBreedableSchoolingFishEntity>> getModelClass() {
        return modelClass;
    }
    public boolean hasFishEntityType() {
        return fishEntityType != null && fishEntityType.isPresent();
    }

}
