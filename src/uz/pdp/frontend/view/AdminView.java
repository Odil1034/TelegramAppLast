package uz.pdp.frontend.view;

import uz.pdp.backend.models.user.User;
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.utills.ScanInput;

import java.util.List;

import static uz.pdp.frontend.view.CommonMenuMethods.*;

public class AdminView {

    private static User curUser;


    public static void profile(User admin) {
        curUser = admin;

        /*1. Show users (%d)
        2. Search user
        3. Show channels
        4. Show groups
        5...
        6...
        0. Log out
            ========================================================== */
        int menu = MenuUtils.menu(MenuUtils.ADMIN_MENU);
        switch (menu){
            case 1 -> {
                showUsers(userService.getList());
            }
            case 2 -> {
                User user = findUser();
            }
        }
    }




    public static void main(String[] args) {

    }
}
