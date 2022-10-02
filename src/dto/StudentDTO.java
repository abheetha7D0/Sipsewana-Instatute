package dto;

public class StudentDTO {
    private String id;
    private String name;
    private String address;
    private String contactNo;

    public StudentDTO() {
    }

    public StudentDTO(String id) {
        this.id = id;
    }

    public StudentDTO(String id, String name, String address, String contactNo) {
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

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
