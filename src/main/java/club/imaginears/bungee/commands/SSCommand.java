package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.Permissions;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SSCommand extends Command {

    public SSCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (Permissions.checkPermissionMsg(p, "core.socialspy")) {
                if (Main.configuration.getBoolean("ss." + p.getUniqueId().toString())) {
                    Main.configuration.set("ss." + p.getUniqueId().toString(), false);
                    try {
                        ConfigurationProvider.getProvider(YamlConfiguration.class).save(Main.configuration, new File(Main.getInstance().getDataFolder(), "config.yml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Chat.sendMessage(p, "Staff", "Your social spy has been disabled");
                } else {
                    Main.configuration.set("ss." + p.getUniqueId().toString(), true);
                    try {
                        ConfigurationProvider.getProvider(YamlConfiguration.class).save(Main.configuration, new File(Main.getInstance().getDataFolder(), "config.yml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    Chat.sendMessage(p, "Staff", "Your social spy has been enabled");
                }
            }
        }

    }
}
