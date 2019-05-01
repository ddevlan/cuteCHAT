package me.ohvalsgod.cutechat.command;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.player.data.PlayerData;
import me.ohvalsgod.cutechat.player.data.mining.PlayerMiningData;
import me.ohvalsgod.cutechat.player.data.settings.menu.SettingsMenu;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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
            if (args.length == 1) {
                if (CuteCHAT.getInstance().getPlayerDataHandler().getPlayerFromName(args[0]) == null) {
                    player.sendMessage(ChatColor.RED + "'" + args[0] + "' has never joined the server.");
                    return false;
                }

                PlayerData targetData = CuteCHAT.getInstance().getPlayerDataHandler().getPlayerFromName(args[0]);
                PlayerMiningData miningData = targetData.getMiningData();

                player.sendMessage(ChatColor.BLUE + "Lapis: " + ChatColor.WHITE + miningData.getLapis());
                player.sendMessage(ChatColor.RED + "Redstone: " + ChatColor.WHITE + miningData.getRedstone());
                player.sendMessage(ChatColor.AQUA + "Diamonds: " + ChatColor.WHITE + miningData.getDiamonds());
                player.sendMessage(ChatColor.GOLD + "Gold: " + ChatColor.WHITE + miningData.getGold());
                player.sendMessage(ChatColor.GRAY + "Iron: " + ChatColor.WHITE + miningData.getIron());
                player.sendMessage(ChatColor.BLACK + "Coal: " + ChatColor.WHITE + miningData.getCoal());
                player.sendMessage(ChatColor.DARK_GRAY + "Stone : " + ChatColor.WHITE + miningData.getStone());
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /ores <player>");
                return false;
            }
        } else if (command.getName().equalsIgnoreCase("link")) {
            if (!data.isLinked()) {
                CuteCHAT.getInstance().getDiscordImplementation().getLinking().put(player.getUniqueId().toString(), RandomStringUtils.randomAlphanumeric(6));
                player.sendMessage(ChatColor.YELLOW + "Please type the following code in #config: " + ChatColor.GREEN + CuteCHAT.getInstance().getDiscordImplementation().getLinking().get(player.getUniqueId().toString()));

                MessageBuilder mb = new MessageBuilder("If your name is " + player.getName() + " ingame, please type the following code: " + CuteCHAT.getInstance().getDiscordImplementation().getLinking().get(player.getUniqueId().toString()));
                Message message = CuteCHAT.getInstance().getDiscordImplementation().applyMentions(mb.build());

                CuteCHAT.getInstance().getDiscordImplementation().getConfigChannel().sendMessage(message).queue();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        CuteCHAT.getInstance().getDiscordImplementation().getLinking().remove(player.getUniqueId().toString());
                        player.sendMessage(ChatColor.RED + "Code has expired, please type /link to try again.");
                    }
                }.runTaskLater(CuteCHAT.getInstance(), 20 * 60);
            } else {
                player.sendMessage(ChatColor.RED + "You silly miner! You already have your discord ID linked!");
            }
        }

        return false;
    }

    //TODO: write command parsing system


}
