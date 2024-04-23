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
    public LocalDate makeBirthday(String birthday);
    int getUserAge(LocalDate userBirthday);
    boolean isValidUsername(String username);
    List<User> getListMatchName(String name);
    List<User> getListMatchRole(UserRole role);
    List<User> getListMatchStatusType(StatusType statusType);
}
