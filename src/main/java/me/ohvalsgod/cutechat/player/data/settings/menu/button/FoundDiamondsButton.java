package me.ohvalsgod.cutechat.player.data.settings.menu.button;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.menu.Button;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import me.ohvalsgod.cutechat.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class FoundDiamondsButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(player.getUniqueId());

        return new ItemBuilder(Material.DIAMOND)
                .name(ChatColor.LIGHT_PURPLE + "Found Diamonds")
                .lore(Arrays.asList("",
                        ChatColor.BLUE + "Do you want to see",
                        ChatColor.BLUE + "found-diamond messages?",
                        "",
                        (data.getSettings().isFoundDiamonds() ? " ➤":" ") + ChatColor.YELLOW + " Show messages",
                        (!data.getSettings().isFoundDiamonds() ? " ➤":" ") + ChatColor.YELLOW + " Hide messages")).build();
    }

}
