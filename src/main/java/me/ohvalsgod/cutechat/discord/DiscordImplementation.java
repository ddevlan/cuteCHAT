package me.ohvalsgod.cutechat.discord;

import lombok.Getter;
import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.discord.listener.CCListener;
import me.ohvalsgod.cutechat.discord.listener.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;

import javax.security.auth.login.LoginException;

public class DiscordImplementation {

    @Getter private JDA jda;
    @Getter private TextChannel chatChannel;

    public DiscordImplementation(CuteCHAT instance) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, () -> {
            try {
                jda = new JDABuilder("NTcyMzY0NTYzNDg0MjQ2MDE4.XMbQCQ.k9ig_xN2SLO94GRox5bgS9GMxQY")
                        .addEventListeners(new ReadyListener())
                        .addEventListeners(new CCListener())
                        .build();
                jda.awaitReady();

                chatChannel = jda.getTextChannelById("572369692119924736");
            } catch (LoginException | InterruptedException e) {
                e.printStackTrace();
                instance.getServer().getPluginManager().disablePlugin(instance);
            }
        });
    }

    public void close() {
        jda.shutdown();
    }

    public User getUserByName(String name) {
        for (User user : jda.getUsers()) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public Message applyMentions(Message message) {
        int i = 0;

        StringBuilder sb = new StringBuilder();
        String[] msgraw = message.getContentRaw().split(" ");
        for (String string : msgraw) {
            if (string.startsWith("@")) {
                if (getUserByName(string.replace("@", "")) != null) {
                    msgraw[i] = "<@" + getUserByName(string.replace("@", "")).getId() + ">";
                }
            }
            sb.append(msgraw[i]);

            i++;

            if (i < msgraw.length) {
                sb.append(" ");
            }
        }

        return new MessageBuilder(sb.toString()).build();
    }

}
