package uz.pdp.frontend.ui;

import uz.pdp.backend.models.user.User;
import uz.pdp.backend.types.user.UserRole;
import uz.pdp.frontend.utills.InputStream;
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.view.AdminView;
import uz.pdp.frontend.view.LoginView;
import uz.pdp.frontend.view.UserView;

public class ConsoleUi {

    public static void main(String[] args) {

        System.out.println("Welcome to Our Telegram Application 😊😊😊");

        while (true){
            int menu = MenuUtils.menu(MenuUtils.MAIN);
            switch (menu){
                case 1 -> {
                    User loginUser = LoginView.logIn();
                    if(loginUser != null){
                        if(loginUser.getRole().equals(UserRole.USER)){
                            UserView.profile(loginUser);
                        }else if(loginUser.getRole().equals(UserRole.GROUP_ADMIN)){
                            AdminView.profile(loginUser);
                        }else {
                            System.out.println("Something is wrong ❌❌❌");
                        }
                    }else {
                        System.out.println("username or password is incorrect ❌❌❌");
                    }

                }
                case 2 -> {
                    LoginView.signUp();
                }
                case 3 -> {
                    System.out.println("Bye bye 😢😢");
                    System.exit(0);
                }
            }
        }


    }
}
