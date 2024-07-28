package net.vg.fishingfrenzy.client.gui.screen;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.vg.fishingfrenzy.FishingFrenzy;
import net.vg.fishingfrenzy.client.gui.screen.option.ClientOptionScreen;
import net.vg.fishingfrenzy.client.gui.screen.option.ServerOptionScreen;

import java.util.ArrayList;
import java.util.List;

public class FishingFrenzyModMenu implements ModMenuApi {

    /**
     * Returns the factory to create the configuration screen for the mod.
     *
     * @return The factory for the main configuration screen.
     */
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        FishingFrenzy.LOGGER.debug("Providing main configuration screen factory");
        return MainConfigScreen::new;
    }

    /**
     * The main configuration screen for the mod.
     */
    public class MainConfigScreen extends GameOptionsScreen {
        private static final Text TITLE_TEXT = Text.translatable("config.general.title");
        private static final Text SERVER_SETTINGS_TEXT = Text.translatable("config.server.title");
        private static final Text CLIENT_SETTINGS_TEXT = Text.translatable("config.client.title");

        /**
         * Constructor for the main configuration screen.
         *
         * @param parent The parent screen.
         */
        public MainConfigScreen(Screen parent) {
            super(parent, MinecraftClient.getInstance().options, TITLE_TEXT);
            FishingFrenzy.LOGGER.debug("Initializing MainConfigScreen with title: {}", TITLE_TEXT.getString());
        }

        /**
         * Adds the options (buttons) to the screen.
         */
        @Override
        protected void addOptions() {
            FishingFrenzy.LOGGER.debug("Adding options to MainConfigScreen");

            // List to hold clickable widgets
            List<ClickableWidget> clickableWidgets = new ArrayList<>();

            // Add server settings button
            ButtonWidget serverSettingsButton = ButtonWidget.builder(SERVER_SETTINGS_TEXT, button -> {
                FishingFrenzy.LOGGER.debug("Server settings button clicked");
                this.client.setScreen(new ServerOptionScreen(this));
            }).width(150).build();
            clickableWidgets.add(serverSettingsButton);

            // Add client settings button
            ButtonWidget clientSettingsButton = ButtonWidget.builder(CLIENT_SETTINGS_TEXT, button -> {
                FishingFrenzy.LOGGER.debug("Client settings button clicked");
                this.client.setScreen(new ClientOptionScreen(this));
            }).width(150).build();
            clickableWidgets.add(clientSettingsButton);

            this.body.addAll(clickableWidgets);
            FishingFrenzy.LOGGER.debug("Added {} clickable widgets", clickableWidgets.size());

        }

        /**
         * Helper method to get the generic value text.
         *
         * @param prefix The prefix text.
         * @param value  The value text.
         * @return The combined text.
         */
        private static Text getGenericValueText(Text prefix, Text value) {
            return Text.translatable("options.generic_value", prefix, value);
        }
    }
}
