package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.utils.Chat;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReplyCommand extends Command {
    public ReplyCommand(String name) {
        super(name);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (args.length < 1) {
                Chat.sendError(p, Chat.ChatErrors.ARGCOUNT, "/r (Message)");
                return;
            }
            if (!Main.replyList.containsKey(p)) {
                Chat.sendError(p, Chat.ChatErrors.COMMON, "No one has messaged you since you logged on");
                return;
            }
            ProxiedPlayer target = Main.replyList.get(p);
            if (target == null) {
                Chat.sendError(p, Chat.ChatErrors.COMMON, "That player is no longer online");
                Main.replyList.remove(p);
                return;
            }
            StringBuilder msg = new StringBuilder();

            for(int i = 0; i < args.length; i++) {

                msg.append(args[i]).append(" ");

            }

            msg.deleteCharAt(msg.length()-1);

            target.sendMessage(Chat.sendColorFree("&b" + p.getName() + " &7to &aYou&7: " + msg));
            p.sendMessage(Chat.sendColorFree("&aYou &7to &b" + target.getName() + "&7: " + msg));

            Main.replyList.put(target, p);

            for (ProxiedPlayer pl : Main.getInstance().getProxy().getPlayers()) {
                if (Main.configuration.getBoolean("ss." + pl.getUniqueId().toString()) == true) {
                    if (pl.hasPermission("core.socialspy")) {
                        if (pl != null) {
                            if (pl != p && pl != target) {
                                pl.sendMessage(Chat.sendColorFree("&b(SS) &f" + p.getName() + " &7to &f" + target.getName() + "&7: " + msg));
                            }
                        }
                    }
                }
            }
        }
    }
}
