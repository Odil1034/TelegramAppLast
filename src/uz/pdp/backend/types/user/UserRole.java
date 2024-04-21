package uz.pdp.backend.types.user;

import java.util.List;
import java.util.StringJoiner;

public enum UserRole {

    USER,
    ADMIN;

    public static void showType() {
        System.out.println("""
                ==========================================================
                                      USER ROLE TYPES
                ==========================================================""");
        UserRole[] values = values();
        for (UserRole value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
    }

    public static UserRole getType(int index){
        UserRole[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
