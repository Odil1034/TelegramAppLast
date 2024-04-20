package uz.pdp.backend.service.userService;

import uz.pdp.backend.DTO.LoginDTO;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserServiceImp implements UserService {

    List<User> users;

    private UserServiceImp() {
        this.users = new ArrayList<>();

        users.add(new User("user2", "user2",
                LocalDate.of(2003, Month.AUGUST, 7), "user2",
                "user2",  UserRole.USER, StatusType.ACTIVE));

        users.add(new User("user1", "user1",
                LocalDate.of(2000, Month.JANUARY, 1), "user1",
                "user1", UserRole.USER, StatusType.ACTIVE));

        users.add(new User("user3", "user3",
                LocalDate.of(2000, Month.JANUARY, 1), "user3",
                "user3", UserRole.USER, StatusType.ACTIVE));
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
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    @Override
    public LocalDate makeBirthday(String birthdayStr) {
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = null;
        try {
            date = simpleDateFormat.parse(birthdayStr);
        } catch (ParseException e) {

        }
        LocalDate localDate = null;
        if(date!=null){
            localDate = LocalDate.parse((CharSequence) date);
        }

        return localDate;*/
        return null;
    }

    @Override
    public boolean isValidUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username) || username.isBlank() || username.isEmpty()){
                return false;
            }
        }
        return true;
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
    public User  login(LoginDTO loginDTO) {
        for (User user : users) {
            if (user.getUsername().equals(loginDTO.username()) &&
                user.getPassword().equals(loginDTO.password())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean signUp(User newUser) {
        for (User user1 : users) {
            if(Objects.equals(user1, newUser)){
                return false;
            }
        }
        users.add(newUser);
        return true;
    }
}
