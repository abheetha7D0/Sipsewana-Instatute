package entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "students")
public class Student implements SuperEntity{
    @Id
    @Column(name = "student_id")
    private String id;

    private String name;
    private String address;
    private String contactNo;

    @OneToMany(mappedBy = "student" ,cascade = CascadeType.REMOVE)
    private List<RegisterDetails> registerDetails;

    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String name, String address, String contactNo, List<RegisterDetails> registerDetails) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setContactNo(contactNo);
        this.setRegisterDetails(registerDetails);
    }

    public Student(String id, String name, String address, String contactNo) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setContactNo(contactNo);

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public List<RegisterDetails> getRegisterDetails() {
        return registerDetails;
    }

    public void setRegisterDetails(List<RegisterDetails> registerDetails) {
        this.registerDetails = registerDetails;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", registerDetails=" + registerDetails +
                '}';
    }
}
