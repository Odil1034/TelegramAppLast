package uz.pdp.backend.service.userService;

import uz.pdp.backend.DTO.LoginDTO;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class UserServiceImp implements UserService {

    List<User> userList;

    private UserServiceImp() {
        this.userList = new ArrayList<>();

        userList.add(new User("admin1", "admin1",
                LocalDate.of(2001, Month.AUGUST, 7), "admin",
                "qwerty", UserRole.ADMIN, StatusType.ACTIVE));

        userList.add(new User("user1", "user1",
                LocalDate.of(2002, Month.JANUARY, 1), "user1",
                "qwerty", UserRole.USER, StatusType.ACTIVE));

        userList.add(new User("user2", "user2",
                LocalDate.of(2003, Month.DECEMBER, 3), "user2",
                "qwerty", UserRole.USER, StatusType.ACTIVE));

        userList.add(new User("user4", "user4",
                LocalDate.of(2004, Month.FEBRUARY, 28), "user3",
                "qwerty", UserRole.USER, StatusType.ACTIVE));
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
        for (User user : userList) {
            if (user.getUsername().equals(newUser.getUsername()) &&
                    user.getPassword().equals(newUser.getPassword())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public User get(String userID) {
        for (User user : userList) {
            if (user.getID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
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
        for (User user : userList) {
            if (user.getUsername().equals(username) || username.isBlank() || username.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<User> getList(List<String> usersID) {
        List<User> result = new ArrayList<>();

        for (User user : userList) {
            if (usersID.contains(user.getID())) {
                result.add(user);
            }
        }

        return result;
    }


    @Override
    public List<User> getList(String name) {
        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (user.getName().equals(name)) {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public List<User> getList(UserRole role) {
        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (user.getRole().equals(role)) {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public List<User> getList(StatusType status) {
        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (user.getStatus().equals(status)) {
                result.add(user);
            }
        }
        return result;
    }


    @Override
    public List<User> getList() {
        return userList;
    }

    @Override
    public void update(User changeUser) {
        for (User user : userList) {
            if (user.getID().equals(changeUser.getID())) {
                int ind = userList.indexOf(user);
                userList.set(ind, changeUser);
            }
        }
    }

    @Override
    public boolean delete(String userID) {
        for (User user : userList) {
            if (user.getID().equals(userID)) {
                user.setIsDelete(true);
            }
        }
        return false;
    }

    @Override
    public User login(LoginDTO loginDTO) {
        for (User user : userList) {
            if (user.getUsername().equals(loginDTO.username()) &&
                    user.getPassword().equals(loginDTO.password())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean signUp(User newUser) {
        for (User user1 : userList) {
            if (Objects.equals(user1, newUser)) {
                return false;
            }
        }
        userList.add(newUser);
        return true;
    }
}
