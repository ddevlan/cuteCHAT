package me.ohvalsgod.cutechat.player.data;

import lombok.Getter;
import lombok.Setter;
import me.ohvalsgod.cutechat.player.data.settings.PlayerSettings;

import java.util.UUID;

@Getter
@Setter
public class PlayerData {

    private UUID uuid;
    private String name;

    private int kills, deaths;
    private double kda;

    private PlayerSettings settings;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;

        settings = new PlayerSettings();
    }

    public double getKda() {
        return kills / deaths;
    }

}
