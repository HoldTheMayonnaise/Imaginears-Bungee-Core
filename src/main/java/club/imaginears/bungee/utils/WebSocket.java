package club.imaginears.bungee.utils;

import club.imaginears.bungee.Main;
import com.neovisionaries.ws.client.*;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WebSocket {

    public static com.neovisionaries.ws.client.WebSocket websocket;
    public static boolean supposedToBeOn = false;
    public static void setupSocket() {
        supposedToBeOn = true;
        try {
            websocket = new WebSocketFactory()
                    .createSocket("wss://imaginears.club:4444")
                    .addListener(new WebSocketListener() {
                        @Override
                        public void onStateChanged(com.neovisionaries.ws.client.WebSocket websocket, WebSocketState newState) throws Exception {

                        }

                        @Override
                        public void onConnected(com.neovisionaries.ws.client.WebSocket websocket, Map<String, List<String>> headers) throws Exception {
                            Console.Log("Connected to imaginears websocket", Console.types.LOG);
                        }

                        @Override
                        public void onConnectError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause) throws Exception {
                            Console.Log("Error connecting to imaginears websocket", Console.types.ERROR);
                        }

                        @Override
                        public void onDisconnected(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
                            if (supposedToBeOn) {
                                Console.Log("Disconnected from imaginears websocket trying to reconnect", Console.types.LOG);
                                ProxyServer.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
                                    public void run() {
                                        setupSocket();
                                    }
                                }, 5, TimeUnit.SECONDS);
                            } else {
                                Console.Log("Disconnected from imaginears websocket", Console.types.LOG);
                            }
                        }

                        @Override
                        public void onFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onContinuationFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onTextFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onBinaryFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onCloseFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onPingFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onPongFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onTextMessage(com.neovisionaries.ws.client.WebSocket websocket, String text) throws Exception {
                            System.out.println("Received msg!!: " + text);
                            try {
                                JSONParser parser = new JSONParser();
                                JSONObject json = (JSONObject) parser.parse(text);
                                if (json.get("type").toString().equalsIgnoreCase("kick")) {
                                    Console.Log("its a kick loll", Console.types.DEBUG);
                                    Console.Log(json.get("uuid").toString(), Console.types.DEBUG);
                                    ProxiedPlayer tar = ProxyServer.getInstance().getPlayer(UUID.fromString(json.get("uuid").toString()));
                                    ProxiedPlayer op = ProxyServer.getInstance().getPlayer(UUID.fromString(json.get("by").toString()));
                                    String opName;
                                    if (op == null) {
                                        opName = "Offline Panel User";
                                    } else {
                                        opName = op.getName();
                                    }
                                    String reason;
                                    if (json.get("reason").toString().length() == 0) {
                                        reason = "Violating the Imaginears Club rules";
                                    } else {
                                        reason = json.get("reason").toString();
                                    }
                                    for (ProxiedPlayer all : Main.getInstance().getProxy().getPlayers()) {
                                        if (all.hasPermission("core.staffchat")) {
                                            all.sendMessage(Chat.sendColorFree("&cSTAFF &7» &c&lNew Kick: &r&7User: &b" + tar.getName() + " &7Reason: &b" + reason + " &7OP: &b" + opName));
                                        }
                                    }
                                    tar.disconnect(Chat.sendColorFree("&cPUNISHMENT &7» &aYou've been kicked from Imaginears Club!\n\n&cReason: &b" + reason + "\n\n&cDate: &a" + new Date()));
                                    Console.Log(tar.getName(), Console.types.LOG);

                                }
                                if (json.get("type").toString().equalsIgnoreCase("broadcast")) {
                                    Console.Log("its a broadcast loll", Console.types.DEBUG);
                                    Console.Log(json.get("msg").toString(), Console.types.DEBUG);
                                    switch(json.get("prefix").toString()) {
                                        case "0":
                                            Main.getInstance().getProxy().broadcast(Chat.sendColorFree("&bINFO &7» &a" + json.get("msg").toString()));
                                            break;
                                        case "1":
                                            Main.getInstance().getProxy().broadcast(Chat.sendColorFree("&f[&aMayor&f] &b** " + json.get("msg").toString()));
                                            break;
                                        case "2":
                                            Main.getInstance().getProxy().broadcast(Chat.sendColorFree("&bAnnouncement &7» &a" + json.get("msg").toString()));
                                            break;
                                    }

                                }
                                if (json.get("type").toString().equalsIgnoreCase("discordLinkToServer")) {
                                    Console.Log("its a discord link lolll", Console.types.DEBUG);


                                }
                            } catch (ParseException ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void onTextMessage(com.neovisionaries.ws.client.WebSocket websocket, byte[] data) throws Exception {

                        }

                        @Override
                        public void onBinaryMessage(com.neovisionaries.ws.client.WebSocket websocket, byte[] binary) throws Exception {

                        }

                        @Override
                        public void onSendingFrame(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onFrameSent(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onFrameUnsent(com.neovisionaries.ws.client.WebSocket websocket, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onThreadCreated(com.neovisionaries.ws.client.WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

                        }

                        @Override
                        public void onThreadStarted(com.neovisionaries.ws.client.WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

                        }

                        @Override
                        public void onThreadStopping(com.neovisionaries.ws.client.WebSocket websocket, ThreadType threadType, Thread thread) throws Exception {

                        }

                        @Override
                        public void onError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause) throws Exception {

                        }

                        @Override
                        public void onFrameError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onMessageError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause, List<WebSocketFrame> frames) throws Exception {

                        }

                        @Override
                        public void onMessageDecompressionError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause, byte[] compressed) throws Exception {

                        }

                        @Override
                        public void onTextMessageError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause, byte[] data) throws Exception {

                        }

                        @Override
                        public void onSendError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {

                        }

                        @Override
                        public void onUnexpectedError(com.neovisionaries.ws.client.WebSocket websocket, WebSocketException cause) throws Exception {

                        }

                        @Override
                        public void handleCallbackError(com.neovisionaries.ws.client.WebSocket websocket, Throwable cause) throws Exception {

                        }

                        @Override
                        public void onSendingHandshake(com.neovisionaries.ws.client.WebSocket websocket, String requestLine, List<String[]> headers) throws Exception {

                        }
                    })
                    .connect();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (WebSocketException ex) {
            ex.printStackTrace();
        }
    }

    public static void sendDiscordLinkRequest(String code) {
        JSONObject item = new JSONObject();
        Date date = new Date();
        item.put("time", Long.valueOf(date.getTime()));
        item.put("type", "discordLinkToDiscord");
        item.put("code", code);
        websocket.sendText(item.toString());
    }

    public static void disconnectSocket() {
        supposedToBeOn = false;
        websocket.disconnect();
    }

}
