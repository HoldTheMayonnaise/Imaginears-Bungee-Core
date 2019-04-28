package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.Permissions;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;

public class Bungee extends Command {
    public Bungee(String name) {
        super(name);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            TextComponent ver = new TextComponent("This server is running BungeeCord version git:BungeeCord-Bootstrap:1.13-Imaginears by md_5 & Gatario");
            ver.setColor(ChatColor.BLUE);
            p.sendMessage(ver);
        }
    }
}
