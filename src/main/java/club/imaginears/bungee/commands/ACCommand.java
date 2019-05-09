package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.Permissions;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ACCommand extends Command {

    public ACCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (Permissions.checkPermissionMsg(p, "core.leaderchat")) {
                if (args.length > 0) {
                    String message = "";
                    for (int i=0;i < args.length; i++) {
                        message = message + " " + args[i];
                    }

                    for (ProxiedPlayer all : Main.getInstance().getProxy().getPlayers()) {
                        if (all.hasPermission("core.leaderchat")) {
                            TextComponent msg = new TextComponent(new TextComponent(Chat.sendColorFree("&dLEADERSHIP &7» &a(&b" + p.getServer().getInfo().getName() + "&a) " + Permissions.getRankPrefix(p) + " &7" + p.getName() + ChatColor.GRAY + ":" + message)));
                            msg.setColor(ChatColor.GRAY);
                            all.sendMessage(msg);
                        }
                    }
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/lc (message)");
                }
            }
        }

    }

}
