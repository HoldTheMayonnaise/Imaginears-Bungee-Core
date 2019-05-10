package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.Permissions;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (Permissions.checkPermissionMsg(p, "core.find")) {
                if (args.length > 0) {
                    ProxiedPlayer target = Main.getInstance().getProxy().getPlayer(args[0]);
                    if (target == null) {
                        Chat.sendError(p, Chat.ChatErrors.COMMON, "That player is currently not on Imaginears!");
                        return;
                    }

                    ServerInfo currentServ = target.getServer().getInfo();

                    Chat.sendMessage(p, "Staff", Permissions.getRankPrefix(target) + " &b" + target.getName() + " &ais currently on &b" + currentServ.getName());
                    ArrayList<String> sameIP = new ArrayList<String>();
                    for (ProxiedPlayer all : Main.getInstance().getProxy().getPlayers()) {
                        if (all.getAddress().getHostString() == target.getAddress().getHostString()) {
                            sameIP.add(all.getName());
                        }
                    }
                    if (sameIP.size() >= 0) {
                        p.sendMessage(TextComponent.fromLegacyText(Chat.sendColorFree("&aThere is also &b" + sameIP.size() + " &aother players on with the same IP address (&b" + target.getAddress().getHostString() + "&a): &b" + String.join("&a, &b", sameIP))));
                    }
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/find (User)");
                }
            }
        }

    }

}
