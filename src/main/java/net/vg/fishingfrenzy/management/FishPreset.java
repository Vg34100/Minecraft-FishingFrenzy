package net.vg.fishingfrenzy.management;

import net.minecraft.predicate.NumberRange;
import net.vg.fishingfrenzy.item.custom.FishPropertiesBuilder;

import java.util.function.Consumer;

public class FishPreset {
    public static Consumer<FishPropertiesBuilder> COMMON = builder -> {
        builder.setWeight(25).setQuality(0).setSpawningWeight(10);
    };

    public static Consumer<FishPropertiesBuilder> RARE = builder -> {
        builder.setWeight(10).setQuality(2).setSpawningWeight(5);
    };

    public static Consumer<FishPropertiesBuilder> LEGENDARY = builder -> {
        builder.setWeight(1).setQuality(5).setSpawningWeight(1);
    };

    public static Consumer<FishPropertiesBuilder> RAIN = builder -> {
        builder.setWeatherDependent(true).setRaining(true);
    };

    public static Consumer<FishPropertiesBuilder> LIGHT = builder -> {
        builder.setSnack(true).setFoodAttributes(1, 0.2f);
    };

    public static Consumer<FishPropertiesBuilder> TEST = builder -> {
        builder.setWeight(10).setQuality(0).setSpawningWeight(10);
    };

    @SafeVarargs
    public static FishPropertiesBuilder applyPresets(FishPropertiesBuilder builder, Consumer<FishPropertiesBuilder>... presets) {
        for (Consumer<FishPropertiesBuilder> preset : presets) {
            preset.accept(builder);
        }
        return builder;
    }
}