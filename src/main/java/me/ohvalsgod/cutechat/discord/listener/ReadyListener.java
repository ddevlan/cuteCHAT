package me.ohvalsgod.cutechat.discord.listener;

import me.ohvalsgod.cutechat.CuteCHAT;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.logging.Level;

public class ReadyListener implements EventListener {

    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent) {
            CuteCHAT.getInstance().getServer().getLogger().log(Level.FINEST, "API is ready!");
        }
    }

}
