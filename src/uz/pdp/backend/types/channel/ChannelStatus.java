package uz.pdp.backend.types.channel;

public enum ChannelStatus {

    ACTIVE,
    DELETED;

    public static void showType() {
        System.out.println("""
                ==========================================================
                                       CHANNEL STATUS
                ==========================================================""");
        ChannelStatus[] values = values();
        for (ChannelStatus value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
    }

    public static ChannelStatus getType(int index){
        ChannelStatus[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
