package uz.pdp.backend.types.group;

import uz.pdp.frontend.utills.MenuUtils;

public enum GroupRole {

    ADMIN,
    USER,
    OWNER;

    public static void showType() {
        System.out.println(MenuUtils.menuCreate("group role"));
        GroupRole[] values = values();
        for (GroupRole value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
        System.out.println(MenuUtils.last());
    }

    public static GroupRole getType(int index){
        GroupRole[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
