package uz.pdp.frontend.view;

import uz.pdp.backend.models.user.User;

public class AdminView {
    private static User curAdmin;

    public static void profile(User admin) {
        curAdmin = admin;


    }

}
