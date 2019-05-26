package club.imaginears.bungee.events;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.objects.IPBan;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.MySQL;
import club.imaginears.bungee.utils.Permissions;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(net.md_5.bungee.api.event.ChatEvent e) {
        if (!(e.isCommand())) {
            ProxiedPlayer p = (ProxiedPlayer) e.getSender();
            MySQL.logMessage(p, e.getMessage(), p.getServer().getInfo().getName());
        }
    }

}
