package net.vg.fishingfrenzy.item.custom;

import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class CustomSpawnEggItem extends Item {
    private final int primaryColor;
    private final int secondaryColor;

    public CustomSpawnEggItem(int primaryColor, int secondaryColor, Settings settings) {
        super(settings);
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
    }

    // You might want to add methods to get/set colors from/to NBT if needed
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt("PrimaryColor", this.primaryColor);
        nbt.putInt("SecondaryColor", this.secondaryColor);
        return nbt;
    }

    public static int getPrimaryColor(NbtCompound nbt) {
        return nbt.contains("PrimaryColor") ? nbt.getInt("PrimaryColor") : 0xFFFFFF;
    }

    public static int getSecondaryColor(NbtCompound nbt) {
        return nbt.contains("SecondaryColor") ? nbt.getInt("SecondaryColor") : 0xFFFFFF;
    }
}