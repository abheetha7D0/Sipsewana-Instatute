package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "programs")
public class Program implements SuperEntity{
    @Id
    @Column(name="program_id")
    private String id;

    private String name;
    private String duration;
    private String fee;

    @OneToMany(mappedBy = "program")
    private List<RegisterDetails> registerDetails;

    public Program() {
    }

    public Program(String id) {
        this.id = id;
    }

    public Program(String id, String name, String duration, String fee, List<RegisterDetails> registerDetails) {
        this.setId(id);
        this.setName(name);
        this.setDuration(duration);
        this.setFee(fee);
        this.setRegisterDetails(registerDetails);
    }

    public Program(String id, String name, String duration, String fee) {
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

    public List<RegisterDetails> getRegisterDetails() {
        return registerDetails;
    }

    public void setRegisterDetails(List<RegisterDetails> registerDetails) {
        this.registerDetails = registerDetails;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", fee='" + fee + '\'' +
                ", registerDetails=" + registerDetails +
                '}';
    }
}
