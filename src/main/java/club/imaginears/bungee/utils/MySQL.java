package club.imaginears.bungee.utils;

import club.imaginears.bungee.objects.Ban;
import club.imaginears.bungee.objects.DiscordUser;
import club.imaginears.bungee.objects.IPBan;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.*;
import java.util.Random;

public class MySQL {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://198.24.160.26:3306/mysql",
                    "root",
                    "A9l0objZudcmslq9!");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void userJoin(ProxiedPlayer p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql2 = connection.prepareStatement("UPDATE server.player_data SET online = '1', latest_ip = ? WHERE uuid = ?");
            String IP = p.getAddress().getHostString();
            sql2.setString(1, IP);
            sql2.setString(2, p.getUniqueId().toString());
            sql2.execute();
            sql2.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void userLeave(ProxiedPlayer p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql2 = connection.prepareStatement("UPDATE server.player_data SET online = 0 WHERE uuid = ?");
            sql2.setString(1, p.getUniqueId().toString());
            sql2.execute();
            sql2.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static Boolean checkIPBanned(String ip) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT id FROM server.ip_bans WHERE ip=? AND active = 1");
            sql.setString(1, ip);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            String item = result.getString("id");
            result.close();
            sql.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static IPBan getIPBan(String ip) {
        try (Connection connection = getConnection()) {
            PreparedStatement ban = connection.prepareStatement("SELECT * FROM server.ip_bans WHERE ip=? AND active = 1");
            ban.setString(1, ip);
            ResultSet result = ban.executeQuery();
            if (!result.next()) {
                result.close();
                ban.close();
                return null;
            }
            String ip2 = result.getString("ip");
            String reason = result.getString("reason");
            String length = result.getString("length");
            String time = result.getString("time");
            String op = result.getString("op");
            String punishid = result.getString("punishid");
            IPBan ipBan = new IPBan(ip2, reason, length, time, op, punishid);
            result.close();
            ban.close();
            return ipBan;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean checkBanned(String uuid) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT id FROM server.bans WHERE uuid=? AND active = 1");
            sql.setString(1, uuid);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            String item = result.getString("id");
            result.close();
            sql.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Ban getBan(String uuid) {
        try (Connection connection = getConnection()) {
            PreparedStatement ban = connection.prepareStatement("SELECT * FROM server.bans WHERE uuid=? AND active = 1");
            ban.setString(1, uuid);
            ResultSet result = ban.executeQuery();
            if (!result.next()) {
                result.close();
                ban.close();
                return null;
            }
            String username = result.getString("username");
            String reason = result.getString("reason");
            String length = result.getString("length");
            String time = result.getString("time");
            String op = result.getString("op");
            String punishid = result.getString("punishid");
            Ban userBan = new Ban(uuid, username, reason, length, time, op, punishid);
            result.close();
            ban.close();
            return userBan;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean checkDiscordCode(String code) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM panel.discord_link_attempts WHERE code=? AND step = '0'");
            sql.setString(1, code);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            String mc = result.getString("minecraft_uuid");
            result.close();
            sql.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean checkDiscordLinked(String uuid) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM panel.discord_links WHERE minecraft_uuid=?");
            sql.setString(1, uuid);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            String mc = result.getString("minecraft_uuid");
            result.close();
            sql.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static DiscordUser getDiscordUser(String code) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM panel.discord_link_attempts WHERE code=?");
            sql.setString(1, code);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return null;
            }
            String id = result.getString("discord_id");
            String username = result.getString("discord_username");
            String indentifier = result.getString("discord_identifier");
            DiscordUser duser = new DiscordUser(id, username, indentifier, code);
            result.close();
            sql.close();
            return duser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateDiscordUser(String code, ProxiedPlayer p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("UPDATE panel.discord_link_attempts SET minecraft_uuid = ?, minecraft_username = ?, step = 1 WHERE code = ?");
            sql.setString(1, p.getUniqueId().toString());
            sql.setString(2, p.getName());
            sql.setString(3, code);
            sql.execute();
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void manualSetLastServer(String server, ProxiedPlayer p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("UPDATE server.player_data SET last_server = ? WHERE uuid = ?");
            sql.setString(1, server);
            sql.setString(2, p.getUniqueId().toString());
            sql.execute();
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static String checkLastServer(ProxiedPlayer p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM server.player_data WHERE uuid=?");
            sql.setString(1, p.getUniqueId().toString());
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return "creative";
            }
            String mc = result.getString("last_server");
            result.close();
            sql.close();
            return mc;
        } catch (SQLException e) {
            e.printStackTrace();
            return "creative";
        }
    }

    public static Boolean checkIfLastServerExists(ProxiedPlayer p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM server.player_data WHERE uuid=?");
            sql.setString(1, p.getUniqueId().toString());
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean checkApplicationToken(String uuid) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM panel.application_tokens WHERE uuid=?");
            sql.setString(1, uuid);
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return false;
            }
            String mc = result.getString("uuid");
            result.close();
            sql.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getApplicationCode(ProxiedPlayer p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM panel.application_tokens WHERE uuid=?");
            sql.setString(1, p.getUniqueId().toString());
            ResultSet result = sql.executeQuery();
            if (!result.next()) {
                result.close();
                sql.close();
                return null;
            }
            String token = result.getString("token");
            result.close();
            sql.close();
            return token;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getNewApplicationLinkCode(ProxiedPlayer p) {
        try (Connection connection = getConnection()) {
            PreparedStatement sql = connection.prepareStatement("DELETE FROM panel.application_tokens WHERE uuid=?");
            sql.setString(1, p.getUniqueId().toString());
            sql.execute();
            sql.close();
            char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
            Random rnd = new Random();
            StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) + "-");
            for (int i = 0; i < 15; i++)
                sb.append(chars[rnd.nextInt(chars.length)]);
            String token = sb.toString();
            PreparedStatement sql2 = connection.prepareStatement("INSERT INTO panel.application_tokens (token, uuid, username) VALUES (?, ?, ?)");
            sql2.setString(1, token);
            sql2.setString(2, p.getUniqueId().toString());
            sql2.setString(3, p.getName());
            sql2.execute();
            sql2.close();
            return token;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
