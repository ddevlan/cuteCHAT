package me.ohvalsgod.cutechat.listener;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class LoginHandler implements Listener {

    @EventHandler
    public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();

        if (CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(uuid) == null) {
            PlayerData data = new PlayerData(uuid);
            data.setName(event.getName());

            CuteCHAT.getInstance().getPlayerDataHandler().getDataSet().add(data);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(event.getPlayer().getUniqueId());

        if (data.isLinked()) {
            CuteCHAT.getInstance().getDiscordImplementation().applyColors(data);
        } else {
            event.getPlayer().sendMessage(ChatColor.RED + "Could not set your color because you don't have your discord linked, if you'd like to match color in discord and ingame, type /link!");
        }

        event.setJoinMessage(event.getPlayer().getDisplayName() + " has joined the game.");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(event.getPlayer().getDisplayName() + " has left the game.");
    }

}
