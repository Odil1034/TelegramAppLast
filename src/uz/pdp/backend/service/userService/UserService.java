package uz.pdp.backend.service.userService;

import uz.pdp.backend.DTO.LoginDTO;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.BaseService;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;

import java.time.LocalDate;
import java.util.List;

public interface UserService extends BaseService<User> {

    User login(LoginDTO loginDTO);
    boolean signUp(User user);
    User getUserByUsername(String username);
    LocalDate makeBirthday(String birthdayStr);
    int getUserAge(User user);
    boolean isValidUsername(String username);


    List<User> getList(List<String> userID);
    List<User> getList(String name);
    List<User> getList(UserRole role);
    List<User> getList(StatusType role);

    List<User> getUserListByRole(UserRole role);

    List<User> getUserListByStatus(StatusType status);
}
