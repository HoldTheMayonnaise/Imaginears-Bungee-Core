package club.imaginears.bungee.utils;

import club.imaginears.bungee.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Chat {

    public enum ChatErrors {
        PERMS, ARGCOUNT, INVALIDARG, COMMON;
    }

    public static String sendColorFree(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void sendMessage(ProxiedPlayer player, String topic, String msg) {
        if (topic == "Staff") {
            player.sendMessage(TextComponent.fromLegacyText(sendColorFree("&cSTAFF" + "&7 » &a" + msg)));
        } else {
            player.sendMessage(TextComponent.fromLegacyText(sendColorFree("&b" + topic + "&7 » &a" + msg)));
        }
    }

    public static void sendError(ProxiedPlayer player, ChatErrors error, String extra) {
        if (error == ChatErrors.PERMS) {
            player.sendMessage(TextComponent.fromLegacyText(sendColorFree("&bPermissions" + "&7 » &cYou do not have access to that command.")));
        } else if (error == ChatErrors.ARGCOUNT) {
            player.sendMessage(TextComponent.fromLegacyText(sendColorFree("&bCommand" + "&7 » &cPlease use: " + extra)));
        } else if (error == ChatErrors.INVALIDARG) {
            player.sendMessage(TextComponent.fromLegacyText(sendColorFree("&bCommand" + "&7 » &cPlease use: " + extra)));
        } else if (error == ChatErrors.COMMON) {
            player.sendMessage(TextComponent.fromLegacyText(sendColorFree("&bCommand" + "&7 » &cSomething went wrong: " + extra)));
        }
    }

    public static void permsError(ProxiedPlayer player) {
        player.sendMessage(TextComponent.fromLegacyText(sendColorFree("&bPermissions" + "&7 » &cYou do not have access to that command.")));
    }

    public static void broadcastMessage(String topic, String msg) {
        Main.getInstance().getProxy().broadcast(TextComponent.fromLegacyText(sendColorFree("&b" + topic + "&7 » &f" + msg)));
    }

}
