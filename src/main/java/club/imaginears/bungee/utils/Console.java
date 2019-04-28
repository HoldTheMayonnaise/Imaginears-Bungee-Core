package club.imaginears.bungee.utils;

import club.imaginears.bungee.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;

import java.util.logging.Logger;

public class Console {

    static Logger console = ProxyServer.getInstance().getLogger();

    public enum types {
        LOG, SUCCESS, WARNING, ERROR, CRITICAL, DEBUG;
    }

    public static void Log(String message, types type) {
        if (type == types.LOG) {
            console.info(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &f" + message));
            return;
        }
        if (type == types.DEBUG) {
            if (Main.debug) {
                console.info(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &f" + message));
                return;
            }
        }
        if (type == types.SUCCESS) {
            console.info(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &a" + message));
            return;
        }
        if (type == types.WARNING) {
            console.info(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &e" + message));
            return;
        }
        if (type == types.ERROR) {
            console.info(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &c" + message));
            return;
        }
        if (type == types.CRITICAL) {
            console.info(ChatColor.translateAlternateColorCodes('&', "&b[Imaginears] &f> &4" + message));
            return;
        }


    }
}
