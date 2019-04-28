package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.Permissions;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Info extends Command {

    public Info(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (Permissions.checkPermissionMsg(p, "core.info")) {
                if (args.length > 0) {
                    String message = "";
                    for (int i=0;i < args.length; i++) {
                        message = message + " " + args[i];
                    }

                    for (ProxiedPlayer all : Main.getInstance().getProxy().getPlayers()) {
                        TextComponent msg = new TextComponent(new TextComponent(Chat.sendColorFree("&bINFO &7» &a" + message)));
                        msg.setColor(ChatColor.GREEN);
                        all.sendMessage(msg);
                    }
                } else {
                    Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/info (message)");
                }
            }
        }

    }

}
