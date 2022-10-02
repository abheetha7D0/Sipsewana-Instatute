package views.tm;

public class ProgramTM {
    private String id;
    private String name;
    private String duration;
    private String fee;

    public ProgramTM() {
    }

    public ProgramTM(String id, String name, String duration, String fee) {
        this.setId(id);
        this.setName(name);
        this.setDuration(duration);
        this.setFee(fee);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
