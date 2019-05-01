package me.ohvalsgod.cutechat.player.data.settings.menu.button;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.menu.Button;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import me.ohvalsgod.cutechat.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ToggleTipsButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(player.getUniqueId());

        return new ItemBuilder(Material.LEVER)
                .name(ChatColor.LIGHT_PURPLE + "Tips")
                .lore(Arrays.asList("",
                        ChatColor.BLUE + "Do you want to see",
                        ChatColor.BLUE + "in-game tips?",
                        "",
                        (data.getSettings().isTips() ? " ➤":" ") + ChatColor.YELLOW + " Show tips",
                        (!data.getSettings().isTips() ? " ➤":" ") + ChatColor.YELLOW + " Hide tips")).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(player.getUniqueId());

        data.getSettings().setTips(!data.getSettings().isTips());
        player.sendMessage(ChatColor.YELLOW + "You are now " + (data.getSettings().isTips() ? ChatColor.GREEN + "able":ChatColor.RED + "unable") + ChatColor.YELLOW + " to see in-game tips.");
    }

    @Override
    public boolean shouldUpdate(Player player, int slot, ClickType clickType) {
        return true;
    }

}
