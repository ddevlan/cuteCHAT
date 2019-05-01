package me.ohvalsgod.cutechat.discord.listener;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import me.ohvalsgod.cutechat.player.data.settings.ChatStatus;
import me.ohvalsgod.cutechat.util.MapUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

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
        } else if (event.getChannel().getId().equalsIgnoreCase("572191148643319819")) {
            for (String string : CuteCHAT.getInstance().getDiscordImplementation().getLinking().values()) {
                if (event.getMessage().equals(string)) {
                    //TODO: learn how to use entries instead of dis vv >_<
                    PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(UUID.fromString(Objects.requireNonNull(MapUtil.getKeyByValue(CuteCHAT.getInstance().getDiscordImplementation().getLinking(), string))));

                    data.setLinked(true);
                    data.setDiscordId(event.getAuthor().getId());

                    CuteCHAT.getInstance().getDiscordImplementation().getLinking().remove(data.getUuid().toString());
                }
            }
        }
    }

}
