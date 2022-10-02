package entity;

public class CustomEntity {
    private String studentId;
    private String name;
    private String date;

    public CustomEntity() {
    }

    public CustomEntity(String studentId, String name, String date) {
        this.setStudentId(studentId);
        this.setName(name);
        this.setDate(date);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
