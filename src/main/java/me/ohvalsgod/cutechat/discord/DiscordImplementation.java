package me.ohvalsgod.cutechat.discord;

import lombok.Getter;
import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.discord.listener.CCListener;
import me.ohvalsgod.cutechat.discord.listener.ReadyListener;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class DiscordImplementation {

    private JDA jda;
    private TextChannel chatChannel, configChannel;
    private Map<String, String> linking = new HashMap<>();

    public DiscordImplementation(CuteCHAT instance) {
        Bukkit.getScheduler().runTaskAsynchronously(instance, () -> {
            try {
                jda = new JDABuilder("NTcyMzY0NTYzNDg0MjQ2MDE4.XMbQCQ.k9ig_xN2SLO94GRox5bgS9GMxQY")
                        .addEventListeners(new ReadyListener())
                        .addEventListeners(new CCListener())
                        .build();
                jda.awaitReady();

                chatChannel = jda.getTextChannelById("572369692119924736");
                configChannel = jda.getTextChannelById("572191148643319819");
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

    public void applyColors(PlayerData data) {
        User user = jda.getUserById(data.getDiscordId());

        for (Role role : user.getJDA().getRoles()) {
            if (role.getName().equalsIgnoreCase("Royale Blue")) {
                data.setColor(ChatColor.BLUE);
            } else if (role.getName().equalsIgnoreCase("Cyan")) {
                data.setColor(ChatColor.AQUA);
            } else if (role.getName().equalsIgnoreCase("Aquamarine")) {
                data.setColor(ChatColor.AQUA);
            } else if (role.getName().equalsIgnoreCase("Glacier Blue")) {
                data.setColor(ChatColor.GRAY);
            } else if (role.getName().equalsIgnoreCase("Golden")) {
                data.setColor(ChatColor.GOLD);
            } else if (role.getName().equalsIgnoreCase("Pink")) {
                data.setColor(ChatColor.LIGHT_PURPLE);
            } else if (role.getName().equalsIgnoreCase("Deep Purple")) {
                data.setColor(ChatColor.DARK_PURPLE);
            } else if (role.getName().equalsIgnoreCase("Crimson")) {
                data.setColor(ChatColor.RED);
            } else if (role.getName().equalsIgnoreCase("Maroon")) {
                data.setColor(ChatColor.DARK_RED);
            } else if (role.getName().equalsIgnoreCase("Black")) {
                data.setColor(ChatColor.BLACK);
            }
        }
        if (Bukkit.getPlayer(data.getUuid()) != null) {
            Player player = Bukkit.getPlayer(data.getUuid());
            player.setDisplayName(data.getColor() + player.getName());
        }
    }

}
