package club.imaginears.bungee.commands;

import club.imaginears.bungee.objects.DiscordUser;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.MySQL;
import club.imaginears.bungee.utils.WebSocket;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Apply extends Command {

    public Apply(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (MySQL.checkDiscordLinked(p.getUniqueId().toString())) {
                Chat.sendMessage(p, "Applications", "Creating Application Hub Token..");
                String code = MySQL.getNewApplicationLinkCode(p);
                String url = "https://imaginears.club/hub/apply.php?login=1&token=" + code;
                Chat.sendMessage(p, "Applications", "Application token created! Use this link to login to the application hub: &b" + url);
            } else {
                Chat.sendMessage(p, "Applications", "You must link your discord before applying! Run &b/discord &ato learn more about linking!");
            }

        }

    }
}