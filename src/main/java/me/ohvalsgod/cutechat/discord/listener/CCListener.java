package me.ohvalsgod.cutechat.discord.listener;

import me.ohvalsgod.cutechat.CuteCHAT;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.entity.Player;

public class CCListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getId().equalsIgnoreCase("572369692119924736")) {
            String message = event.getMessage().getContentRaw();

            if (message.startsWith("!")) {
                //TODO: @me.ohvalsgod.cutechat.command
                String command = message.replaceFirst("!", "").split(" ")[0];
                String[] args = message.replace("!" + command + " ", "").split(" ");

                if (command.equalsIgnoreCase("sayserver")) {
                    if (args.length == 0) {
                        return;
                    }

                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    for (String string : args) {
                        sb.append(string);
                        if (i < args.length) {
                            sb.append(" ");
                        }
                        i++;
                    }

                    String out = sb.toString();

                    for (Player player : CuteCHAT.getInstance().getServer().getOnlinePlayers()) {
                        //TODO: yada yada check settings
                        //TODO: get color for rank
                        player.sendMessage(event.getAuthor().getName() + ": " + out);
                    }
                }
            }
        }
    }

}
