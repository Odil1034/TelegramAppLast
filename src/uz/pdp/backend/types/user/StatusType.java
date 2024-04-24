package uz.pdp.backend.types.user;

import uz.pdp.frontend.utills.MenuUtils;

public enum StatusType {

    ACTIVE,
    DELETED,
    BLOCKED;

    public static void showType() {
        System.out.println(MenuUtils.menuCreate("user status types"));
        StatusType[] values = values();
        for (StatusType value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
        System.out.println(MenuUtils.last());
    }

    public static StatusType getType(int index){
        StatusType[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
