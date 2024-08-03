package net.vg.fishingfrenzy.item.custom;

import net.minecraft.nbt.NbtCompound;

public class TargetBaitItem extends BaitItem implements TargetBaitProperties {
    private final int primaryColor;
    private final int secondaryColor;

    public TargetBaitItem(int primaryColor, int secondaryColor, Settings settings, BaitProperties properties) {
        super(settings, properties);
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
    }

    public static int getPrimaryColor(NbtCompound nbt) {
        return nbt.contains("PrimaryColor") ? nbt.getInt("PrimaryColor") : 0xFFFFFF;
    }

    public static int getSecondaryColor(NbtCompound nbt) {
        return nbt.contains("SecondaryColor") ? nbt.getInt("SecondaryColor") : 0xFFFFFF;
    }
}
