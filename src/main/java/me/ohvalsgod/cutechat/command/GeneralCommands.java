package me.ohvalsgod.cutechat.command;

import me.ohvalsgod.cutechat.player.data.settings.menu.SettingsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GeneralCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Sorry this command is only available to players >_< !");
            return false;
        }

        Player player = (Player) commandSender;

        if (command.getName().equalsIgnoreCase("settings")) {
            new SettingsMenu().openMenu(player);
            return true;
        }

        return false;
    }

    //TODO: write command parsing system


}
