package me.ohvalsgod.cutechat.listener;

import me.ohvalsgod.cutechat.CuteCHAT;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled()) {
            event.setFormat(event.getPlayer().getDisplayName() + ": " + event.getMessage());

            MessageBuilder mb = new MessageBuilder(event.getPlayer().getName() + ": " + event.getMessage());
            Message message = CuteCHAT.getInstance().getDiscordImplementation().applyMentions(mb.build());

            CuteCHAT.getInstance().getDiscordImplementation().getChatChannel().sendMessage(message).queue();
        }
    }

}
