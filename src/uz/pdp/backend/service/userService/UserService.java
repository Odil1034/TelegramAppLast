package uz.pdp.backend.service.userService;

import uz.pdp.backend.DTO.LoginDTO;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.BaseService;

import java.time.LocalDate;

public interface UserService extends BaseService<User> {

    User login(LoginDTO loginDTO);
    boolean signUp(User user);
    User getUserByUsername(String username);
    LocalDate makeBirthday(String birthdayStr);
    int getUserAge(User user);
    boolean isValidUsername(String username);
}
