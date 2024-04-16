package uz.pdp.backend.models.user;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;

public class User extends BaseModel {

    private String username;
    private String password;
    private String nickname;
    private UserRole role;
    private StatusType status;

    public User(String username, String password, String nickname, UserRole role, StatusType status) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.status = status;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
