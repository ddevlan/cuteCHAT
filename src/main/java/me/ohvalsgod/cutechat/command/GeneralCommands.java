package me.ohvalsgod.cutechat.command;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import me.ohvalsgod.cutechat.player.data.mining.PlayerMiningData;
import me.ohvalsgod.cutechat.player.data.settings.menu.SettingsMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GeneralCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Sorry this command is only available to players >_< !");
            return false;
        }

        Player player = (Player) commandSender;
        PlayerData data = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerDataFromUUID(player.getUniqueId());

        if (command.getName().equalsIgnoreCase("settings")) {
            new SettingsMenu().openMenu(player);
            return true;
        } else if (command.getName().equalsIgnoreCase("ores")) {
            if (args.length < 1) {
                if (CuteCHAT.getInstance().getPlayerDataHandler().getPlayerFromName(args[0]) == null) {
                    player.sendMessage(ChatColor.RED + "'" + args[0] + "' has never joined the server.");
                    return false;
                }

                PlayerData targetData = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerFromName(args[0]);
                PlayerMiningData miningData = targetData.getMiningData();

                player.sendMessage(" ");
                player.sendMessage(ChatColor.BLUE + "Lapis: " + ChatColor.WHITE + miningData.getLapis());
                player.sendMessage(ChatColor.RED + "Redstone: " + ChatColor.WHITE + miningData.getRedstone());
                player.sendMessage(ChatColor.AQUA + "Diamonds: " + ChatColor.WHITE + miningData.getDiamonds());
                player.sendMessage(ChatColor.GOLD + "Gold: " + ChatColor.WHITE + miningData.getGold());
                player.sendMessage(ChatColor.GRAY + "Iron: " + ChatColor.WHITE + miningData.getIron());
                player.sendMessage(ChatColor.GRAY + "Stone : " + ChatColor.WHITE + miningData.getStone());
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /ores <player>");
                return false;
            }
        }

        return false;
    }

    //TODO: write command parsing system


}
