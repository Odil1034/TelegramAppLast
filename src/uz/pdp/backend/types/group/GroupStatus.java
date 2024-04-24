package uz.pdp.backend.types.group;

import uz.pdp.frontend.utills.MenuUtils;

public enum GroupStatus {

    ACTIVE,
    DELETED;

    public static void showType() {
        System.out.println(MenuUtils.menuCreate("group status"));
        GroupStatus[] values = values();
        for (GroupStatus value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
        System.out.println(MenuUtils.last());
    }

    public static GroupStatus getType(int index){
        GroupStatus[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }

}
