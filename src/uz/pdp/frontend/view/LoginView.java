package uz.pdp.frontend.view;

import uz.pdp.backend.DTO.LoginDTO;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;
import uz.pdp.frontend.utills.InputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class LoginView {

    private static UserService userService = UserServiceImp.getInstance();

    public static User logIn(){
        System.out.println("Enter login info: ");
        String username = InputStream.getStr("Username: ");
        String password = InputStream.getStr("Password: ");

        return userService.login(new LoginDTO(username, password));
    };

    public static void signUp(){
        System.out.println("Enter your Info for signUp: ");
        String name = InputStream.getStr("Name: ");
        String lastName = InputStream.getStr("LastName: ");
        String birthdayStr = InputStream.getStr("Enter your birthday as dd/mm/yyyy : ");
        userService.makeBirthday(birthdayStr);



        InputStream.getStr("Username: ");
        InputStream.getStr("NickName: ");

    };

}
