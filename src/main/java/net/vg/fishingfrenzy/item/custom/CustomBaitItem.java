package net.vg.fishingfrenzy.item.custom;

import net.minecraft.item.Item;

public class CustomBaitItem extends Item {
    private final int primaryColor;
    private final int secondaryColor;

    public CustomBaitItem(int primaryColor, int secondaryColor, Item.Settings settings) {
        super(settings);
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
    }
}