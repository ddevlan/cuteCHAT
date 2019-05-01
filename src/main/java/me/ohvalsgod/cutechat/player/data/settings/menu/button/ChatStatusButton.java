package me.ohvalsgod.cutechat.player.data.settings.menu.button;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.menu.Button;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import me.ohvalsgod.cutechat.player.data.settings.ChatStatus;
import me.ohvalsgod.cutechat.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ChatStatusButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(player.getUniqueId());

        return new ItemBuilder(Material.PAPER)
                .name(ChatColor.LIGHT_PURPLE + "Chat Options")
                .lore(Arrays.asList("",
                        ChatColor.BLUE + "How would you like",
                        ChatColor.BLUE + "to see your chat?",
                        (data.getSettings().getChatStatus() == ChatStatus.BOTH ? " ➤":" ") + ChatColor.YELLOW + "Show both",
                        (data.getSettings().getChatStatus() == ChatStatus.DISCORD_ONLY ? " ➤":" ") + ChatColor.YELLOW + "Show discord only",
                        (data.getSettings().getChatStatus() == ChatStatus.GLOBAL_ONLY ? " ➤":" ") + ChatColor.YELLOW + "Show global only")).build();
    }

}
