package club.imaginears.bungee.objects;

public class IPBan {

    private String ip;
    private String reason;
    private String length;
    private String time;
    private String op;
    private String pid;


    public IPBan(String ip, String reason, String length, String time, String op, String pid) {
        this.ip = ip;
        this.reason = reason;
        this.length = length;
        this.time = time;
        this.op = op;
        this.pid = pid;
    }

    public String getIP() {
        return this.ip;
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
