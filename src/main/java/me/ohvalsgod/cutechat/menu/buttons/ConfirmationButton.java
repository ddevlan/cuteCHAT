package me.ohvalsgod.cutechat.menu.buttons;

import lombok.AllArgsConstructor;
import me.ohvalsgod.cutechat.menu.Button;
import me.ohvalsgod.cutechat.util.TypeCallback;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@AllArgsConstructor
public class ConfirmationButton extends Button {

    private boolean confirm;
    private TypeCallback<Boolean> callback;
    private boolean closeAfterResponse;

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemStack itemStack = (confirm ? new ItemStack(Material.GREEN_STAINED_GLASS_PANE):new ItemStack(Material.RED_STAINED_GLASS_PANE));
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(this.confirm ? ChatColor.GREEN + "Confirm" : ChatColor.RED + "Cancel");
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        if (this.confirm) {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 20f, 0.1f);
        } else {
            player.playSound(player.getLocation(), Sound.BLOCK_GLASS_HIT, 20f, 0.1F);
        }

        if (this.closeAfterResponse) {
            player.closeInventory();
        }

        this.callback.callback(this.confirm);
    }

}
