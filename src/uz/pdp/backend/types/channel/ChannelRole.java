package uz.pdp.backend.types.channel;

public enum ChannelRole {

    OWNER,
    USER;

    public static void showType() {
        System.out.println("""
                ==========================================================
                                       CHANNEL ROLE
                ==========================================================""");
        ChannelRole[] values = values();
        for (ChannelRole value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
    }

    public static ChannelRole getType(int index){
        ChannelRole[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
