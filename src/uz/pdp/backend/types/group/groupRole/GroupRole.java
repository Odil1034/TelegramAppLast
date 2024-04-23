package uz.pdp.backend.types.group.groupRole;

public enum GroupRole {

    ADMIN,
    USER,
    OWNER;

    public static void showType() {
        System.out.println("""
                ==========================================================
                                       GROUP ROLE
                ==========================================================""");
        GroupRole[] values = values();
        for (GroupRole value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
    }

    public static GroupRole getType(int index){
        GroupRole[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
