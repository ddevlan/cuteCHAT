package me.ohvalsgod.cutechat.listener;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

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

}
