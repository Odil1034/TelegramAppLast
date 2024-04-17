package uz.pdp.backend.service.userService;

import uz.pdp.backend.DTO.LoginDTO;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.BaseService;

public interface UserService extends BaseService<User> {

    User login(LoginDTO loginDTO);
    User signUp(User user);

}
