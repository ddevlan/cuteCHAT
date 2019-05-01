package me.ohvalsgod.cutechat.util;

import me.ohvalsgod.cutechat.CuteCHAT;
import me.ohvalsgod.cutechat.CuteCHAT;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskUtil {

    public static void run(Runnable runnable) {
        CuteCHAT.getInstance().getServer().getScheduler().runTask(CuteCHAT.getInstance(), runnable);
    }

    public static void runTimer(Runnable runnable, long delay, long timer) {
        CuteCHAT.getInstance().getServer().getScheduler().runTaskTimer(CuteCHAT.getInstance(), runnable, delay, timer);
    }

    public static void runTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(CuteCHAT.getInstance(), delay, timer);
    }

    public static void runLater(Runnable runnable, long delay) {
        CuteCHAT.getInstance().getServer().getScheduler().runTaskLater(CuteCHAT.getInstance(), runnable, delay);
    }

    public static void runAsync(Runnable runnable) {
        CuteCHAT.getInstance().getServer().getScheduler().runTaskAsynchronously(CuteCHAT.getInstance(), runnable);
    }

}
