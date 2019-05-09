package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.Permissions;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand(String name) {
        super(name);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (Permissions.checkPermissionMsg(p, "core.staff")) {

                ArrayList<String> wdwList = new ArrayList<>();
                ArrayList<String> creativeList = new ArrayList<>();

                for (ProxiedPlayer all : Main.getInstance().getProxy().getPlayers()) {
                    if (all.getServer().getInfo().getName().equalsIgnoreCase("wdw")) {
                        String player = Permissions.getRankPrefix(all) + " &7" + all.getName();
                        wdwList.add(player);
                    }
                    if (all.getServer().getInfo().getName().equalsIgnoreCase("creative")) {
                        String player = Permissions.getRankPrefix(all) + " &7" + all.getName();
                        creativeList.add(player);
                    }
                }

                Chat.sendMessage(p, "Servers", "Online Players [&b" + Main.getInstance().getProxy().getPlayers().size() + "&a]:");
                p.sendMessage(TextComponent.fromLegacyText(Chat.sendColorFree("&b&lWDW &r&b[&a" + wdwList.size() + "&b]&r&a: " + String.join("&a, " + wdwList))));
                p.sendMessage(TextComponent.fromLegacyText(Chat.sendColorFree("&b&lCreative &r&b[&a" + creativeList.size() + "&b]&a: " + String.join("&a, " + creativeList))));

            }
        }
    }
}
