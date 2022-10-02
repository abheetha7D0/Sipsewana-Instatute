package dto;

public class RegistrationDetailsDTO {
    private String studentId;
    private String programId;
    private String date;

    public RegistrationDetailsDTO() {
    }

    public RegistrationDetailsDTO(String studentId, String programId, String date) {
        this.setStudentId(studentId);
        this.setProgramId(programId);
        this.setDate(date);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
