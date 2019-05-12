package club.imaginears.bungee.events;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.objects.Ban;
import club.imaginears.bungee.objects.IPBan;
import club.imaginears.bungee.utils.*;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ServerConnectRequest;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import javax.xml.soap.Text;
import java.util.concurrent.TimeUnit;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();
        if (MySQL.checkIPBanned(p.getAddress().getHostString())) {
            IPBan ipBan = MySQL.getIPBan(p.getAddress().getHostString());

        }
        MySQL.userJoin(p);
        if (Permissions.checkPermission(p, "core.staff")) {
            for (ProxiedPlayer all : Main.getInstance().getProxy().getPlayers()) {
                if (all.hasPermission("core.staff")) {
                    Chat.sendMessage(all, "Staff", "&b" + p.getName() + " &ahas clocked in");
                }
            }
        }
        if (MySQL.checkIfLastServerExists(p)) {
            String lastServer = MySQL.checkLastServer(p);
            Chat.sendMessage(p, "Servers", "You have been reconnected to your previous server: &b" + lastServer);
            ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
                public void run() {
                    if (p.getServer().getInfo() != ProxyServer.getInstance().getServerInfo(lastServer)) {
                        p.connect(ProxyServer.getInstance().getServerInfo(lastServer));
                    }
                }
            }, 3, TimeUnit.SECONDS);

        }
    }


    @EventHandler
    public void onConnect(LoginEvent e) {
        if (MySQL.checkBanned(e.getConnection().getUniqueId().toString())) {
            Ban ban = MySQL.getBan(e.getConnection().getUniqueId().toString());
            String length = ban.getLength();
            if (ban.getLength().equals("0")) {
                length = "Permanent";
            }
            e.setCancelReason(new TextComponent(TextComponent.fromLegacyText(Chat.sendColorFree("\n&cPUNISHMENT &7» &aYou are currently banned from Imaginears Club!\n\n&cReason: &b" + ban.getReason() + "\n&cLength: &b" + length + "\n&cDate Banned: &b" + CalendarUtils.ConvertMilliSecondsToFormattedDate(ban.getTime()) + "\n\n&aTo appeal visit: https://imaginears.club/hub/appeal\n\n&cPunishment ID: &b" + ban.getPid()))));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void serverDis(ServerKickEvent e) {
        if (e.getKickReason().contains("We are experiencing some pixie dust on")) {
            if (e.getKickedFrom() == Main.getInstance().getProxy().getServerInfo("wdw")) {
                if (MySQL.checkLastServer(e.getPlayer()).equalsIgnoreCase("creative")) {
                    ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
                        public void run() {
                            Chat.sendMessage(e.getPlayer(), "Servers", "Could not connect you to &b" + e.getKickedFrom().getName() + "&a, WDW is currently only open to Passholders. Donate at &bhttp://imaginearsclub.buycraft.net");
                        }
                    }, 3, TimeUnit.SECONDS);
                } else {
                    e.setCancelled(true);
                    //e.getPlayer().connect(Main.getInstance().getProxy().getServerInfo("creative"));
                    MySQL.manualSetLastServer("creative", e.getPlayer());
                }
            }
        }

        if ( e.getKickReason().contains("Outdated client!")) {
            if (e.getKickedFrom() == Main.getInstance().getProxy().getServerInfo("wdw")) {
                if (MySQL.checkLastServer(e.getPlayer()).equalsIgnoreCase("creative")) {
                    ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
                        public void run() {
                            Chat.sendMessage(e.getPlayer(), "Servers", "Could not connect you to &b" + e.getKickedFrom().getName() + "&a, WDW is currently running on 1.14!");
                        }
                    }, 3, TimeUnit.SECONDS);
                } else {
                    MySQL.manualSetLastServer("creative", e.getPlayer());
                    //e.getPlayer().connect(Main.getInstance().getProxy().getServerInfo("creative"));
                    ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
                        public void run() {
                            Chat.sendMessage(e.getPlayer(), "Servers", "Could not connect you to &b" + e.getKickedFrom().getName() + "&a, you have been redirected to &bcreative&a! (WDW is currently running on 1.14)");
                        }
                    }, 3, TimeUnit.SECONDS);
                }
            }
        }
    }



    @EventHandler
    public void onPreJoin(PreLoginEvent e) {
        Connection conn = e.getConnection();
        String ip = e.getConnection().getAddress().getHostString();
        if (MySQL.checkIPBanned(ip)) {
            IPBan ban = MySQL.getIPBan(ip);
            String length = ban.getLength();
            if (ban.getLength().equals("0")) {
                length = "Permanent";
            }
            e.setCancelReason(new TextComponent(TextComponent.fromLegacyText(Chat.sendColorFree("\n&cPUNISHMENT &7» &aYour IP (&b" + ban.getIP() + "&a) is currently banned from Imaginears Club!\n\n&cReason: &b" + ban.getReason() + "\n&cLength: &b" + length + "\n&cDate Banned: &b" + CalendarUtils.ConvertMilliSecondsToFormattedDate(ban.getTime()) + "\n\n&aTo appeal visit: https://imaginears.club/hub/appeal\n\n&cPunishment ID: &b" + ban.getPid()))));
            e.setCancelled(true);
        }


    }


    @EventHandler
    public void onCrash(ServerKickEvent e) {
        ProxiedPlayer p = e.getPlayer();
        MySQL.userLeave(p);
    }

    @EventHandler
    public void onLeave(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        MySQL.userLeave(p);
        if (Permissions.checkPermission(p, "core.staff")) {
            for (ProxiedPlayer all : Main.getInstance().getProxy().getPlayers()) {
                if (all.hasPermission("core.staff")) {
                    Chat.sendMessage(all, "Staff", "&b" + p.getName() + " &ahas clocked out, last server: &b" + p.getServer().getInfo().getName());
                }
            }
        }
    }

}
