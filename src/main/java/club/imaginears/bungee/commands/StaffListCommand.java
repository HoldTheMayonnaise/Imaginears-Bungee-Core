package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.Permissions;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;

public class StaffListCommand extends Command {

    public StaffListCommand(String name) {
        super(name);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (Permissions.checkPermissionMsg(p, "core.staff")) {

                ArrayList<String> staffList = new ArrayList<>();

                for (ProxiedPlayer all : Main.getInstance().getProxy().getPlayers()) {
                    if (all.hasPermission("core.staff")) {
                        String staffMember = "&a(&b" + all.getServer().getInfo().getName() + "&a) " + Permissions.getRankPrefix(all) + " &7" + all.getName();
                        staffList.add(staffMember);
                    }
                }

                Chat.sendMessage(p, "Staff", "&aCurrent online staff: " + String.join("&a, ", staffList));

            }
        }
    }
}
