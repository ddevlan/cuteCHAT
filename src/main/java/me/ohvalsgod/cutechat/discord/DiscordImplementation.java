package me.ohvalsgod.cutechat.discord;

import lombok.Getter;
import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.discord.listener.CCListener;
import me.ohvalsgod.cutechat.discord.listener.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DiscordImplementation {

    @Getter private JDA jda;

    public DiscordImplementation(CuteCHAT instance) {
        try {
            jda = new JDABuilder("NTcyMzY0NTYzNDg0MjQ2MDE4.XMbQCQ.k9ig_xN2SLO94GRox5bgS9GMxQY")
                    .addEventListeners(new ReadyListener())
                    .addEventListeners(new CCListener())
                    .build();
            jda.awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
            instance.getServer().getPluginManager().disablePlugin(instance);
        }
    }

}
