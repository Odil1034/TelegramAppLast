package uz.pdp.backend.types.channel;

import uz.pdp.frontend.utills.MenuUtils;

public enum ChannelStatus {

    ACTIVE,
    DELETED;

    public static void showType() {
        System.out.println(MenuUtils.menuCreate("channel status"));
        ChannelStatus[] values = values();
        for (ChannelStatus value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
        System.out.println(MenuUtils.last());
    }

    public static ChannelStatus getType(int index){
        ChannelStatus[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
