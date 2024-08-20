package Sem4.HM;

public class Employee {
    private int employeeID;
    private String name;
    private String phoneNumber;
    private int experience;

    public Employee(int employeeID, String name, String phoneNumber, int experience) {
        this.employeeID = employeeID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return String.format("id: %d, " +
                        "name: %s, " +
                        "phone: %s, " +
                        "experience: %d\n"
                , employeeID, name, phoneNumber, experience);
    }
}
