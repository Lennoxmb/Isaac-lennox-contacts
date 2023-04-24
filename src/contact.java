public class contact {
    private String firstName;
    private String LastName;
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        LastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
