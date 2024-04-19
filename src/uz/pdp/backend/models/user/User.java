package uz.pdp.backend.models.user;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;

import java.time.LocalDate;
import java.util.Date;

public class User extends BaseModel {

    private String name;
    private String lastName;
    private String username;
    private String password;
    private LocalDate birthDay;
    private UserRole role;
    private StatusType status;

    public User(String name, String lastName, LocalDate birthDay, String username,
                String password, UserRole role, StatusType status) {
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", lastName='" + lastName + '\'' +
               ", birthDay=" + birthDay +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", role=" + role +
               ", status=" + status +
               '}';
    }
}
