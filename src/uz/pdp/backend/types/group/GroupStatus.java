package uz.pdp.backend.types.group;

public enum GroupStatus {

    ACTIVE,
    DELETED;

    public static void showType() {
        System.out.println("""
                ==========================================================
                                       GROUP ROLE
                ==========================================================""");
        GroupStatus[] values = values();
        for (GroupStatus value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
    }

    public static GroupStatus getType(int index){
        GroupStatus[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }

}
