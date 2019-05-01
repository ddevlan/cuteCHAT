package me.ohvalsgod.cutechat.discord.listener;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import me.ohvalsgod.cutechat.player.data.settings.ChatStatus;
import me.ohvalsgod.cutechat.util.MapUtil;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class CCListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().equals(CuteCHAT.getInstance().getDiscordImplementation().getChatChannel())) {
            if (!event.getAuthor().isBot()) {
                String message = event.getMessage().getContentRaw();

                for (Player player : Bukkit.getOnlinePlayers()) {
                    PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(player.getUniqueId());

                    if (data.getSettings().getChatStatus() == ChatStatus.BOTH || data.getSettings().getChatStatus()== ChatStatus.DISCORD_ONLY) {
                        player.sendMessage(event.getAuthor().getName() + ": " + message);
                    }
                }
            }
        } else if (event.getChannel().equals(CuteCHAT.getInstance().getDiscordImplementation().getConfigChannel())) {
            System.out.println(event.getMessage().getContentRaw());
            if (CuteCHAT.getInstance().getDiscordImplementation().getLinking().containsValue(event.getMessage().getContentRaw())) {
                UUID uuid = UUID.fromString(MapUtil.getKeyByValue(CuteCHAT.getInstance().getDiscordImplementation().getLinking(), event.getMessage().getContentRaw()));
                PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(uuid);

                data.setLinked(true);
                data.setDiscordId(event.getAuthor().getId());

                Bukkit.getPlayer(data.getUuid()).sendMessage(ChatColor.GREEN + "Your discord has been linked!");
                MessageBuilder mb = new MessageBuilder("Your Discord account has been linked to your Minecraft account '" + data.getName() + "'.");
                Message message = CuteCHAT.getInstance().getDiscordImplementation().applyMentions(mb.build());
                CuteCHAT.getInstance().getDiscordImplementation().getConfigChannel().sendMessage(message).queue();

                CuteCHAT.getInstance().getDiscordImplementation().applyColors(data);
                CuteCHAT.getInstance().getDiscordImplementation().getLinking().remove(data.getUuid().toString());
            }
        }
    }

}
