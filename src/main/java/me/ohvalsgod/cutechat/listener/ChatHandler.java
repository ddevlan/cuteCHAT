package me.ohvalsgod.cutechat.listener;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import me.ohvalsgod.cutechat.player.data.settings.ChatStatus;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled()) {
            event.setCancelled(true);

            for (Player player : Bukkit.getOnlinePlayers()) {
                PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(player.getUniqueId());

                if (data.getSettings().getChatStatus() != ChatStatus.DISCORD_ONLY) {
                    player.sendMessage(player.getDisplayName() + ChatColor.WHITE + ": " + event.getMessage());
                }
            }

            MessageBuilder mb = new MessageBuilder(event.getPlayer().getName() + ": " + event.getMessage());
            Message message = CuteCHAT.getInstance().getDiscordImplementation().applyMentions(mb.build());

            CuteCHAT.getInstance().getDiscordImplementation().getChatChannel().sendMessage(message).queue();
        }
    }

}
