package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.objects.DiscordUser;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.MySQL;
import club.imaginears.bungee.utils.WebSocket;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Discord extends Command {

    public Discord(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (args.length != 1) {
                Chat.sendMessage(p, "Discord", "&aTo link your discord account please DM the bot -link and copy the code it gives, then run /discord (Code). The bot will message you to confirm. You can join the discord here: &bhttp://iears.us/discord");
            } else {
                if (MySQL.checkDiscordCode(args[0])) {
                    DiscordUser duser = MySQL.getDiscordUser(args[0]);
                    Chat.sendMessage(p, "Discord", "Valid code, trying to link with: &b" + duser.getUsername() + "#" + duser.getIndentifier() + "&a, Please check your DMs to verify linking!");
                    MySQL.updateDiscordUser(args[0], p);
                    WebSocket.sendDiscordLinkRequest(args[0]);
                } else {
                    Chat.sendError(p, Chat.ChatErrors.COMMON, "That is an invalid code! Run /discord to learn more.");
                }
            }

        }

    }
}