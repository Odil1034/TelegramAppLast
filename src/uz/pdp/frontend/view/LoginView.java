package uz.pdp.frontend.view;

import uz.pdp.backend.DTO.LoginDTO;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;
import uz.pdp.frontend.utills.ScanInput;

import java.time.LocalDate;
import java.util.List;

public class LoginView {

    private static final UserService userService = UserServiceImp.getInstance();

    public static User logIn(){
        System.out.println("Enter login info: ");
        String username = ScanInput.getStr("Username: ");
        String password = ScanInput.getStr("Password: ");

        return userService.login(new LoginDTO(username, password));
    };

    public static void signUp(){
        System.out.println("Enter your Info for signUp: ");
        String name = ScanInput.getStr("Name: ");
        String lastName = ScanInput.getStr("LastName: ");
        String birthdayStr = ScanInput.getStr("Enter your birthday as dd/mm/yyyy : ");
        LocalDate birthday = userService.makeBirthday(birthdayStr);

        String username = ScanInput.getStr("Username: ");
        String password = ScanInput.getStr("Password: ");

        boolean isUserCreated = userService.signUp(new User(name, lastName, birthday,
                username, password, UserRole.USER, StatusType.ACTIVE));

        if(isUserCreated){
            System.out.println("You are registered ✅✅✅");
        }else {
            System.out.println("such a user exist ❌❌❌");
        }

    };

    public static void main(String[] args) {

        LoginView.signUp();

        List<User> usersList = userService.getList();
        for (User user : usersList) {
            System.out.println(user);
        }

    }

}
