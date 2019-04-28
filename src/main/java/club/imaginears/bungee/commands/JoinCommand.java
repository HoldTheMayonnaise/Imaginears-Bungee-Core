package club.imaginears.bungee.commands;

import club.imaginears.bungee.Main;
import club.imaginears.bungee.utils.Chat;
import club.imaginears.bungee.utils.Permissions;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class JoinCommand extends Command {

    public JoinCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (args.length == 0) {
                Chat.sendMessage(p, "Servers", "Click any of the following servers to connect:");
                TextComponent WDW = new TextComponent();
                WDW.setText(Chat.sendColorFree("&bWDW &a(Click to connect)"));
                WDW.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/join wdw"));
                WDW.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( Chat.sendColorFree("&aClick to connect to &bWDW") ).create() ));
                p.sendMessage(WDW);
                TextComponent Creative = new TextComponent();
                Creative.setText(Chat.sendColorFree("&bCreative &a(Click to connect)"));
                Creative.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/join creative"));
                Creative.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( Chat.sendColorFree("&aClick to connect to &bCreative") ).create() ));
                p.sendMessage(Creative);
            }
            if (args.length > 0) {
                String server = args[0].toLowerCase();
                switch (server) {
                    case "wdw":
                        if (p.getServer().getInfo().getName().equalsIgnoreCase("wdw")) {
                            Chat.sendMessage(p, "Servers", "&cYou're already connected to &bWDW");
                        } else {
                            p.connect(Main.getInstance().getProxy().getServerInfo("wdw"));
                            Chat.sendMessage(p, "Servers", "Sending you to &bWDW");
                        }
                        break;
                    case "creative":
                        if (p.getServer().getInfo().getName().equalsIgnoreCase("creative")) {
                            Chat.sendMessage(p, "Servers", "&cYou're already connected to &bCreative");
                        } else {
                            Chat.sendMessage(p, "Servers", "Sending you to &bCreative");
                            p.connect(Main.getInstance().getProxy().getServerInfo("creative"));
                        }
                        break;
                    default:
                        Chat.sendMessage(p, "Servers", "&cThat server does not exist!");
                        break;
                }

            }

        }

    }

}
