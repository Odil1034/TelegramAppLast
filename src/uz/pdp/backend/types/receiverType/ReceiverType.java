package uz.pdp.backend.types.receiverType;

public enum ReceiverType {

    GROUP,
    CHAT,
    CHANNEL;

    public static void showType() {
        System.out.println("""
                ==========================================================
                                      RECEIVER TYPES
                ==========================================================""");
        ReceiverType[] values = values();
        for (ReceiverType value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
    }

    public static ReceiverType getType(int index){
        ReceiverType[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
