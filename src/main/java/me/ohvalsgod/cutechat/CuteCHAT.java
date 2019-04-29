package me.ohvalsgod.cutechat;

import lombok.Getter;
import me.ohvalsgod.cutechat.discord.DiscordImplementation;
import org.bukkit.plugin.java.JavaPlugin;

public class CuteCHAT extends JavaPlugin {

    @Getter private static CuteCHAT instance;
    @Getter private DiscordImplementation discordImplementation;

    @Override
    public void onEnable() {
        instance = this;

        discordImplementation = new DiscordImplementation(instance);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

}
