package uz.pdp.backend.types.channel;

import uz.pdp.frontend.utills.MenuUtils;

public enum ChannelRole {

    OWNER,
    USER;

    public static void showType() {
        System.out.println(MenuUtils.menuCreate("channel role"));
        ChannelRole[] values = values();
        for (ChannelRole value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
        System.out.println(MenuUtils.last());
    }

    public static ChannelRole getType(int index){
        ChannelRole[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
