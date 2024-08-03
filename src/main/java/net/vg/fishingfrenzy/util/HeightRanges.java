package net.vg.fishingfrenzy.util;

import net.minecraft.predicate.NumberRange;

public enum HeightRanges {

    SKY_LEVEL(NumberRange.DoubleRange.atLeast(150.0)),
    HIGH_PLATEAUS(NumberRange.DoubleRange.between(90.0, 160.0)),
    MOUNTAIN_LEVEL(NumberRange.DoubleRange.between(65.0, 155.0)),
    SEA_LEVEL(NumberRange.DoubleRange.between(0.0, 70.0)),
    LOWLANDS(NumberRange.DoubleRange.between(30.0, 65.0)),
    UNDERGROUND_LEVEL(NumberRange.DoubleRange.between(10.0, 50.0)),
    UNDERWATER_CAVES(NumberRange.DoubleRange.between(-10.0, 30.0)),
    CAVERN(NumberRange.DoubleRange.atMost(10.0)),
    DEEP_DARK(NumberRange.DoubleRange.atMost(-40.0));

    private final NumberRange.DoubleRange range;

    HeightRanges(NumberRange.DoubleRange range) {
        this.range = range;
    }

    public NumberRange.DoubleRange getRange() {
        return range;
    }
}