package me.ohvalsgod.cutechat.player.data.settings.menu;

import me.ohvalsgod.cutechat.menu.Button;
import me.ohvalsgod.cutechat.menu.Menu;
import me.ohvalsgod.cutechat.player.data.settings.menu.button.ChatStatusButton;
import me.ohvalsgod.cutechat.player.data.settings.menu.button.FoundDiamondsButton;
import me.ohvalsgod.cutechat.player.data.settings.menu.button.ToggleTipsButton;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SettingsMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return ChatColor.GRAY + "Edit settings";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new ChatStatusButton());
        buttons.put(4, new FoundDiamondsButton());
        buttons.put(8, new ToggleTipsButton());

        return buttons;
    }

}
