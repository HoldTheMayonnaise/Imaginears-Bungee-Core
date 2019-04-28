package club.imaginears.bungee.utils;

import me.lucko.luckperms.LuckPerms;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Permissions {

    public static boolean checkPermission(ProxiedPlayer p, String perm) {
        if (p.hasPermission(perm) || p.hasPermission("core.all")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPermissionMsg(ProxiedPlayer p, String perm) {
        if (p.hasPermission(perm) || p.hasPermission("core.all")) {
            return true;
        } else {
            Chat.sendError(p, Chat.ChatErrors.PERMS, null);
            return false;
        }
    }

    public static String getRankPrefix(ProxiedPlayer p) {
        String group = LuckPerms.getApi().getUser(p.getUniqueId()).getPrimaryGroup();
        if (group.equalsIgnoreCase("default")) {
            return "&f[&eGuest&f]";
        } else if (group.equalsIgnoreCase("silver")) {
            return "&f[&6Silver&f]";
        } else if (group.equalsIgnoreCase("gold")) {
            return "&f[&6Gold&f]";
        } else if (group.equalsIgnoreCase("platinum")) {
            return "&f[&6Platinum&f]";
        } else if (group.equalsIgnoreCase("platinum+")) {
            return "&f[&6Platinum+&f]";
        } else if (group.equalsIgnoreCase("specialguest")) {
            return "&0[&4Special Guest&0]";
        } else if (group.equalsIgnoreCase("security")) {
            return "&f[&4Security&f]";
        } else if (group.equalsIgnoreCase("photopass")) {
            return "&f[&3Photopass&f]";
        } else if (group.equalsIgnoreCase("character")) {
            return "&f[&6Character&f]";
        } else if (group.equalsIgnoreCase("castmember")) {
            return "&f[&3Cast Member&f]";
        } else if (group.equalsIgnoreCase("coordinator")) {
            return "&f[&cCoordinator&f]";
        } else if (group.equalsIgnoreCase("manager")) {
            return "&f[&aManager&f]";
        } else if (group.equalsIgnoreCase("developer")) {
            return "&f[&5Developer&f]";
        } else {
            return "&f[&eGuest&f]";
        }
    }

}
