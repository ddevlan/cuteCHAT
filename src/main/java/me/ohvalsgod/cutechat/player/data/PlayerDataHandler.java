package me.ohvalsgod.cutechat.player.data;

import lombok.Getter;
import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.player.data.settings.ChatStatus;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class PlayerDataHandler {

    private final CuteCHAT instance;
    private Set<PlayerData> dataSet;
    private final File dataFolder;

    public PlayerDataHandler(CuteCHAT instance) {
        this.instance = instance;

        dataFolder = new File(CuteCHAT.getInstance().getDataFolder() + "player_data/");

        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        dataSet = new HashSet<>();
    }

    public void savePlayer(UUID uuid) {
        /*
            Saves specific player data to file
         */

        PlayerData data = getPlayerDataFromUUID(uuid);

        File file = new File(dataFolder + uuid.toString() + ".yml");
        YamlConfiguration config = new YamlConfiguration();

        config.set("name", data.getName());
        config.set("kills", data.getKills());
        config.set("deaths", data.getDeaths());
        config.set("settings.found_diamonds", data.getSettings().isFoundDiamonds());
        config.set("settings.chat_status", data.getSettings().getChatStatus());
        config.set("settings.tips", data.getSettings().isTips());

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        /*
            Loads all player data from file.
         */

        for (File file : dataFolder.listFiles()) {
            if (file.getName().contains(".yml")) {
                UUID uuid = UUID.fromString(file.getName().replace(".yml", ""));

                PlayerData data = new PlayerData(uuid);

                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

                data.setName(config.getString("name"));     //TODO: grab from some uuid -> name api
                data.setKills(config.getInt("kills"));
                data.setDeaths(config.getInt("deaths"));

                data.getSettings().setFoundDiamonds(config.getBoolean("settings.found_diamonds"));
                data.getSettings().setChatStatus(ChatStatus.valueOf(config.getString("settings.chat_status")));
                data.getSettings().setTips(config.getBoolean("settings.tips"));

                dataSet.add(data);
            }
        }

    }

    public PlayerData getPlayerDataFromUUID(UUID uuid) {
        for (PlayerData data : dataSet) {
            if (data.getUuid().equals(uuid)) {
                return data;
            }
        }
        return null;
    }

}
