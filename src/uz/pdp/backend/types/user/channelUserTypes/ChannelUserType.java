package uz.pdp.backend.types.user.channelUserTypes;

import uz.pdp.backend.types.user.UserRole;

public enum ChannelUserType {

    OWNER,
    USER;

    public static void showType() {
        System.out.println("""
                ==========================================================
                                  USER TYPES OF CHANNEL
                ==========================================================""");
        ChannelUserType[] values = values();
        for (ChannelUserType value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
    }

    public static ChannelUserType getType(int index){
        ChannelUserType[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
