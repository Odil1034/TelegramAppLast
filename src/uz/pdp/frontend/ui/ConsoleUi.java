package uz.pdp.frontend.ui;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.groupService.GroupService;
import uz.pdp.backend.service.groupService.GroupServiceImp;
import uz.pdp.backend.types.user.UserRole;
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.view.AdminView;
import uz.pdp.frontend.view.LoginView;
import uz.pdp.frontend.view.UserView;

public class ConsoleUi {

    public static void main(String[] args) {
        GroupService groupService = GroupServiceImp.getInstance();
        Group group = new Group("uzbek sila", "001", "sila");
        boolean b = groupService.create(group);


        System.out.println("==========================================================\n" +
                "Welcome to Our Telegram Application 😊😊😊");

        while (true){
            int menu = MenuUtils.menu(MenuUtils.MAIN);
            switch (menu){
                case 1 -> {
                    User loginUser = LoginView.logIn();
                    if(loginUser != null){
                        if(loginUser.getRole().equals(UserRole.USER)){
                            UserView.profile(loginUser);
                        }else if(loginUser.getRole().equals(UserRole.ADMIN)){
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
