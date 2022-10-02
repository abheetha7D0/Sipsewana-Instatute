package views.tm;

public class StudentTM {
    private String id;
    private String name;
    private String address;
    private String contactNo;

    public StudentTM() {
    }

    public StudentTM(String id, String name, String address, String contactNo) {
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
}
