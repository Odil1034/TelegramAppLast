package uz.pdp.backend.types.user;

public enum StatusType {

    ACTIVE,
    DELETED,
    BLOCKED;

    public static void showType() {
        System.out.println("""
                ==========================================================
                                     USER STATUS TYPES
                ==========================================================""");
        StatusType[] values = values();
        for (StatusType value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
    }

    public static StatusType getType(int index){
        StatusType[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
