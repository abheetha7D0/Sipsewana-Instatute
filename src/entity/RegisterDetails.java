package entity;

import javax.persistence.*;

@Entity
@Table(name = "register_details")
public class RegisterDetails implements SuperEntity{

    @Id
    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name="program_id")
    private Program program;

    private String date;

    public RegisterDetails() {
    }

    public RegisterDetails(Student student, Program program, String date) {

        this.setStudent(student);
        this.setProgram(program);
        this.setDate(date);
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RegisterDetails{" +
                ", student=" + student +
                ", program=" + program +
                ", date='" + date + '\'' +
                '}';
    }
}
