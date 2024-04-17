package uz.pdp.backend.service.userService;

import uz.pdp.backend.DTO.LoginDTO;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceImp implements UserService {

    List<User> users;

    private UserServiceImp() {
        this.users = new ArrayList<>();
        users.add(new User("Umar", "umar1202",
                "UmarBegTOP", UserRole.USER, StatusType.ONLINE));
        users.add(new User("Xurshid", "xurshid123",
                "Khurshidbek", UserRole.USER, StatusType.ONLINE));
        users.add(new User("Odiljon", "odil2003",
                "Odil1034", UserRole.GROUP_ADMIN, StatusType.ONLINE));
    }

    // Singleton Design Pattern
    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserServiceImp();
        }
        return userService;
    }

    @Override
    public boolean create(User newUser) {
        for (User user : users) {
            if (user.getUsername().equals(newUser.getUsername()) &&
                user.getPassword().equals(newUser.getPassword())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public User get(String userID) {
        for (User user : users) {
            if (user.getID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getList() {
        return users;
    }

    @Override
    public void update(User changeUser) {
        for (User user : users) {
            if (user.getID().equals(changeUser.getID())) {
                int ind = users.indexOf(user);
                users.set(ind, changeUser);
            }
        }
    }

    @Override
    public boolean delete(String userID) {
        for (User user : users) {
            if (user.getID().equals(userID)) {
                user.setIsDelete(true);
            }
        }
        return false;
    }

    @Override
    public User login(LoginDTO loginDTO) {
        for (User user : users) {
            if (user.getUsername().equals(loginDTO.username()) &&
                user.getPassword().equals(loginDTO.password())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User signUp(User user) {
        for (User user1 : users) {
            if (user.getUsername().equals(user1.getUsername()) &&
                user.getPassword().equals(user1.getPassword())) {
                return null;
            }
        }
        for (User user1 : users) {
            if(Objects.equals(user1, user)){
                return user1;
            }
        }
        return null;
    }
}
