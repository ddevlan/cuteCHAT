package me.ohvalsgod.cutechat.discord.listener;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import me.ohvalsgod.cutechat.player.data.settings.ChatStatus;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CCListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getId().equalsIgnoreCase("572369692119924736")) {
            if (!event.getAuthor().isBot()) {
                String message = event.getMessage().getContentRaw();

                for (Player player : Bukkit.getOnlinePlayers()) {
                    PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(player.getUniqueId());

                    if (data.getSettings().getChatStatus() == ChatStatus.BOTH || data.getSettings().getChatStatus()== ChatStatus.DISCORD_ONLY) {
                        player.sendMessage(event.getAuthor().getName() + ": " + message);
                    }
                }
            }
        }
    }

}
