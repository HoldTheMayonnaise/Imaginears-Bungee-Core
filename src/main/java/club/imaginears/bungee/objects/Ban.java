package club.imaginears.bungee.objects;

public class Ban {
    private String uuid;
    private String username;
    private String reason;
    private String length;
    private String time;
    private String op;
    private String pid;


    public Ban(String UUID, String username, String reason, String length, String time, String op, String pid) {
        this.uuid = uuid;
        this.username = username;
        this.reason = reason;
        this.length = length;
        this.time = time;
        this.op = op;
        this.pid = pid;
    }

    public String getUUID() {
        return this.uuid;
    }

    public String getUsername() {
        return this.username;
    }

    public String getReason() {
        return this.reason;
    }

    public String getLength() {
        return this.length;
    }

    public String getTime() {
        return this.time;
    }

    public String getOp() {
        return this.op;
    }

    public String getPid() {
        return this.pid;
    }


}
