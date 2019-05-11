package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.objects.DiscordUser;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.MySQL;
import club.imaginears.bungee.utils.Permissions;
import club.imaginears.bungee.utils.WebSocket;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.concurrent.TimeUnit;

public class RestartCommand extends Command {

    public RestartCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (Permissions.checkPermissionMsgNoOverride(p, "core.restart")) {
                Main.getInstance().getProxy().broadcast(TextComponent.fromLegacyText(Chat.sendColorFree("&bINFO &7»&a The bungeecord server is now &c&lrestarting&r&a.. Please rejoin!")));
                ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
                    public void run() {
                        for (ProxiedPlayer pl : Main.getInstance().getProxy().getPlayers()) {
                            pl.disconnect(TextComponent.fromLegacyText(Chat.sendColorFree("&bINFO &7»&a The parks proxy server is now restarting, please rejoin!")));
                        }
                    }
                }, 5, TimeUnit.SECONDS);
            }

        }

    }
}