package swagger.petstore.model;


public class UserPojo {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private String userStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public static UserPojo getUserPojo(String username, String firstName, String lastName, String email, String password, String phone, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(12);
        userPojo.setUsername(username);
        userPojo.setFirstname(firstName);
        userPojo.setLastname(lastName);
        userPojo.setEmail(email);
        userPojo.setPassword(password);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(status);
        return userPojo;
    }

}

