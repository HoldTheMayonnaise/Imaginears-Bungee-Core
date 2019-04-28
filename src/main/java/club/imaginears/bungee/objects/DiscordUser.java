package club.imaginears.bungee.objects;


public class DiscordUser {
    private String id;
    private String username;
    private String indentifier;
    private String code;


    public DiscordUser(String id, String username, String indentifier, String code) {
        this.id = id;
        this.username = username;
        this.indentifier = indentifier;
        this.code = code;
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getIndentifier() {
        return this.indentifier;
    }

    public String getCode() {
        return this.code;
    }

}
