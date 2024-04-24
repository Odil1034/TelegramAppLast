package uz.pdp.backend.service.userService;

import uz.pdp.backend.DTO.LoginDTO;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserServiceImp implements UserService {

    List<User> users;

    private UserServiceImp() {
        this.users = new ArrayList<>();
        users.add(new User("Sardor", "Baxodirov",
                LocalDate.of(2001, Month.SEPTEMBER, 12), "1",
                "1", UserRole.ADMIN, StatusType.ACTIVE));

        users.add(new User("Anvar", "Baxromov",
                LocalDate.of(1993, Month.JANUARY, 7), "2",
                "2", UserRole.USER, StatusType.ACTIVE));


        users.add(new User("Sanjar", "Temirov",
                LocalDate.of(2002, Month.JANUARY, 1), "Sanjar1",
                "u1", UserRole.USER, StatusType.ACTIVE));

        users.add(new User("Ikrom", "Umarov",
                LocalDate.of(2003, Month.DECEMBER, 3), "Imrom2",
                "u2", UserRole.USER, StatusType.ACTIVE));

        users.add(new User("Sanobar", "Abbosova",
                LocalDate.of(2004, Month.FEBRUARY, 28), "Sanobar3",
                "u3", UserRole.USER, StatusType.ACTIVE));

        users.add(new User("Guli", "Mustafoyeva",
                LocalDate.of(2002, Month.JANUARY, 1), "Guli3",
                "u4", UserRole.USER, StatusType.ACTIVE));

        users.add(new User("Umida", "Murodova",
                LocalDate.of(2003, Month.DECEMBER, 3), "Umida4",
                "u5", UserRole.USER, StatusType.ACTIVE));

        users.add(new User("Eshmat", "Ismatov",
                LocalDate.of(2004, Month.FEBRUARY, 28), "Eshmat5",
                "u6", UserRole.USER, StatusType.ACTIVE));
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
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public LocalDate makeBirthday(String birthdayStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(birthdayStr, formatter);
    }

    public int getUserAge(LocalDate birthday) {
        int age;

        LocalDate now = LocalDate.now();
        Period period = Period.between(birthday, now);
        age = period.getYears();

        return age;
    }

    @Override
    public boolean isValidUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username) || username.isBlank() || username.isEmpty()) {
                return false;
            }
        }
        return true;
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
                user.setDelete(true);
                user.setStatus(StatusType.DELETED);
                return true;
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
    public boolean signUp(User newUser) {
        for (User user1 : users) {
            if (Objects.equals(user1, newUser)) {
                return false;
            }
        }
        users.add(newUser);
        return true;
    }

    @Override
    public List<User> getListMatchName(String name) {
        List<User> userList = new ArrayList<>();

        for (User user : users) {
            if (user.getName().equals(name)) {
                userList.add(user);
            }
        }

        return userList;
    }

    @Override
    public List<User> getListMatchRole(UserRole role) {
        List<User> userList = new ArrayList<>();

        for (User user : users) {
            if (user.getRole().equals(role)) {
                userList.add(user);
            }
        }

        return userList;
    }

    @Override
    public List<User> getListMatchStatusType(StatusType statusType) {
        List<User> userList = new ArrayList<>();

        for (User user : users) {
            if (user.getStatus().equals(statusType)) {
                userList.add(user);
            }
        }

        return userList;
    }

    @Override
    public List<User> getList() {
        return users;
    }
}
