package me.ohvalsgod.cutechat;

import lombok.Getter;
import me.ohvalsgod.cutechat.command.GeneralCommands;
import me.ohvalsgod.cutechat.discord.DiscordImplementation;
import me.ohvalsgod.cutechat.listener.ChatHandler;
import me.ohvalsgod.cutechat.listener.LoginHandler;
import me.ohvalsgod.cutechat.listener.MiningHandler;
import me.ohvalsgod.cutechat.menu.ButtonListener;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import me.ohvalsgod.cutechat.player.data.PlayerDataHandler;
import me.ohvalsgod.cutechat.player.data.settings.menu.SettingsMenu;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class CuteCHAT extends JavaPlugin {

    @Getter private static CuteCHAT instance;
    private DiscordImplementation discordImplementation;
    private PlayerDataHandler playerDataHandler;

    //  Menus
    private SettingsMenu settingsMenu = new SettingsMenu();

    @Override
    public void onEnable() {
        instance = this;

        discordImplementation = new DiscordImplementation(instance);
        playerDataHandler = new PlayerDataHandler(instance);

        playerDataHandler.init();

        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        for (PlayerData data : playerDataHandler.getDataSet()) {
            playerDataHandler.savePlayer(data.getUuid());
        }

        discordImplementation.close();

        instance = null;
    }

    private void registerCommands() {
        instance.getCommand("settings").setExecutor(new GeneralCommands());
        instance.getCommand("ores").setExecutor(new GeneralCommands());
    }

    private void registerListeners() {
        instance.getServer().getPluginManager().registerEvents(new ChatHandler(), instance);
        instance.getServer().getPluginManager().registerEvents(new LoginHandler(), instance);
        instance.getServer().getPluginManager().registerEvents(new ButtonListener(), instance);
        instance.getServer().getPluginManager().registerEvents(new MiningHandler(), instance);
    }

}
