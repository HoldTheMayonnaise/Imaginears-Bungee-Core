package club.imaginears.bungee;

import club.imaginears.bungee.commands.*;
import club.imaginears.bungee.events.PlayerJoin;
import club.imaginears.bungee.utils.WebSocket;
import me.lucko.luckperms.LuckPerms;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Plugin {

    private static Main instance;
    private static Main m;
    public static boolean debug = true;
    public static HashMap<ProxiedPlayer, ProxiedPlayer> replyList = new HashMap<ProxiedPlayer, ProxiedPlayer>();
    public static Configuration configuration;

    @Override
    public void onEnable() {
        instance = this;
        WebSocket.setupSocket();
        getLogger().info("Loading ImaginearsBungee");
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        getProxy().getPluginManager().registerCommand(this, new SCCommand("sc"));
        getProxy().getPluginManager().registerCommand(this, new MessageCommand("msg"));
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand("r"));
        getProxy().getPluginManager().registerCommand(this, new ReplyCommand("reply"));
        getProxy().getPluginManager().registerCommand(this, new SSCommand("ss"));
        getProxy().getPluginManager().registerCommand(this, new SSCommand("socialspy"));
        getProxy().getPluginManager().registerCommand(this, new SCCommand("staffchat"));
        getProxy().getPluginManager().registerCommand(this, new SCCommand("ac"));
        getProxy().getPluginManager().registerCommand(this, new ACCommand("lc"));
        getProxy().getPluginManager().registerCommand(this, new ACCommand("leader"));
        getProxy().getPluginManager().registerCommand(this, new ACCommand("leaderchat"));
        getProxy().getPluginManager().registerCommand(this, new DCCommand("dc"));
        getProxy().getPluginManager().registerCommand(this, new DCCommand("devchat"));
        getProxy().getPluginManager().registerCommand(this, new StaffListCommand("stafflist"));
        getProxy().getPluginManager().registerCommand(this, new StaffListCommand("sl"));
        getProxy().getPluginManager().registerCommand(this, new ListCommand("list"));
        getProxy().getPluginManager().registerCommand(this, new ListCommand("glist"));
        getProxy().getPluginManager().registerCommand(this, new JoinCommand("join"));
        getProxy().getPluginManager().registerCommand(this, new JoinCommand("server"));
        getProxy().getPluginManager().registerCommand(this, new Bungee("bungee"));
        getProxy().getPluginManager().registerCommand(this, new MK("mk"));
        getProxy().getPluginManager().registerCommand(this, new MK("wdw"));
        getProxy().getPluginManager().registerCommand(this, new MK("parks"));
        getProxy().getPluginManager().registerCommand(this, new Discord("discord"));
        getProxy().getPluginManager().registerCommand(this, new Apply("apply"));
        getProxy().getPluginManager().registerCommand(this, new Info("info"));
        getProxy().getPluginManager().registerCommand(this, new Info("alert"));
        getProxy().getPluginManager().registerCommand(this, new FindCommand("find"));
        getProxy().getPluginManager().registerCommand(this, new FindCommand("locate"));
        getProxy().getPluginManager().registerCommand(this, new BugCommand("bug"));
        getProxy().getPluginManager().registerCommand(this, new BugCommand("bugreport"));
        getProxy().getPluginManager().registerCommand(this, new RestartCommand("bungeerestart"));
        getProxy().getPluginManager().registerListener(this, new PlayerJoin());
    }

    @Override
    public void onDisable() {
        instance = null;
        WebSocket.disconnectSocket();
    }

    public static Main getInstance() {
        return instance;
    }


}
