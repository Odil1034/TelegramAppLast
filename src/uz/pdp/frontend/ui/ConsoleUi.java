package uz.pdp.frontend.ui;

import uz.pdp.backend.models.user.User;
import uz.pdp.backend.types.user.UserRole;
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.view.AdminView;
import uz.pdp.frontend.view.LoginView;
import uz.pdp.frontend.view.UserView;

public class ConsoleUi {

    public static void main(String[] args) {

        System.out.println("==========================================================\n" +
                "Welcome to Our Telegram Application 😊😊😊");

        while (true) {
            int menu = MenuUtils.menu(MenuUtils.MAIN);
            switch (menu) {
                case 1 -> loginUser();
                case 2 -> signUp();
                case 0 -> exitProgram();
                default -> System.out.println("Wrong choice, try again ❌❌❌");
            }
        }
    }

    private static void loginUser() {
        User loginUser = LoginView.logIn();
        if (loginUser != null) {
            switch (loginUser.getStatus()) {
                case BLOCKED -> System.out.println("Your account is blocked ❌");
                case DELETED -> System.out.println("Your account has been deleted and cannot be recovered ❌");
                case ACTIVE -> {
                    if (loginUser.getRole().equals(UserRole.USER)) {
                        UserView.profile(loginUser);
                    } else if (loginUser.getRole().equals(UserRole.ADMIN)) {
                        AdminView.profile(loginUser);
                    } else {
                        System.out.println("Something is wrong ❌");
                    }
                }
                default -> System.out.println("Something is wrong ❌");
            }
        } else {
            System.out.println("Username or password is incorrect ❌");
        }
    }

    private static void signUp() {
        LoginView.signUp();
    }

    private static void exitProgram() {
        System.out.println("Bye bye 😢");
        System.exit(0);
    }

}
