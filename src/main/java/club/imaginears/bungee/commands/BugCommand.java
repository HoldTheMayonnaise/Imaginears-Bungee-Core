package club.imaginears.bungee.commands;

import club.imaginears.bungee.objects.DiscordUser;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.MySQL;
import club.imaginears.bungee.utils.WebSocket;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BugCommand extends Command {

    public BugCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            Chat.sendMessage(p, "Bug Report", "Please submit all bug reports to our Jira: &bhttps://imaginears.atlassian.net &a| Simply create an account & then submit the bug to the proper project");

        }

    }
}