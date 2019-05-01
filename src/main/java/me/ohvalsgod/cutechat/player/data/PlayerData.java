package me.ohvalsgod.cutechat.player.data;

import lombok.Getter;
import lombok.Setter;
import me.ohvalsgod.cutechat.player.data.mining.PlayerMiningData;
import me.ohvalsgod.cutechat.player.data.settings.PlayerSettings;

import java.util.UUID;

@Getter
@Setter
public class PlayerData {

    private UUID uuid;
    private String name;

    private int kills, deaths;
    private double kda;

    //  Data objects
    private PlayerSettings settings;
    private PlayerMiningData miningData;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;

        settings = new PlayerSettings();
        miningData = new PlayerMiningData();
    }

    public double getKda() {
        return kills / deaths;
    }

}
