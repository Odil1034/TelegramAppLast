package uz.pdp.backend.types.user.groupUserTypes;

import uz.pdp.backend.types.user.UserRole;

public enum GroupUserTypes {

    OWNER,
    USER,
    ADMIN;

    public static void showType() {
        System.out.println("""
                ==========================================================
                                   USER TYPES OF GROUP
                ==========================================================""");
        GroupUserTypes[] values = values();
        for (GroupUserTypes value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
    }

    public static GroupUserTypes getType(int index){
        GroupUserTypes[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }

}
