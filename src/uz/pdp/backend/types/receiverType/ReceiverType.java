package uz.pdp.backend.types.receiverType;

import uz.pdp.frontend.utills.MenuUtils;

public enum ReceiverType {

    GROUP,
    CHAT,
    CHANNEL;

    public static void showType() {
        System.out.println(MenuUtils.menuCreate("receiver type"));
        ReceiverType[] values = values();
        for (ReceiverType value : values) {
            System.out.println(value.ordinal() + 1 + ".  " + value);
        }
        System.out.println(MenuUtils.last());
    }

    public static ReceiverType getType(int index){
        ReceiverType[] values = values();
        if(index < 0 || index > values.length){
            return null;
        }

        return values[index];
    }
}
