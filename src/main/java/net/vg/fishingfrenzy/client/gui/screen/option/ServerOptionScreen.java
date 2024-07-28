package net.vg.fishingfrenzy.client.gui.screen.option;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.vg.fishingfrenzy.config.ModConfigs;

public class ServerOptionScreen extends GameOptionsScreen {

    public ServerOptionScreen(Screen parent) {
        super(parent, MinecraftClient.getInstance().options, Text.translatable("config.server.title"));
    }

    @Override
    protected void addOptions() {

    }
    @Override
    public void close() {
        ModConfigs.saveConfigs();
        this.client.setScreen(this.parent);
    }

    private static Text getGenericValueText(Text prefix, Text value) {
        return Text.translatable("options.generic_value", prefix, value);
    }
}
