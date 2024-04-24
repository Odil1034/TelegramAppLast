package uz.pdp.frontend.utills;

import uz.pdp.frontend.view.AdminView;

public interface MenuUtils {

    int COUNT_OF_REPEAT = 60;
    String REPEAT_SIGN = "=";


    String MAIN = menuCreate("main menu") + """
            1. Log In
            2. Sign Up
              0. Exit""" + last();

    /*
     * sistemaga admin kirgandigi menyu
     * 1.hamma  userlani korsatadi i tanlagan useri:
     *       1. profilini korishi kere
     *       2. subscribe bogan kanallarini korishi kere
     *       3. qaysi grupada bosa osha grupani ozini korishi kere
     *       4. useri block qilishi
     *       5. useri unblock qilishi
     * 2. useri qidirish i topgan useri
     *       profili, kanallari, grupasi hulas osha gap
     * 3. show channels: sistemadigi hama kanallani korish
     * 4. show groups: hama grupalani korish
     *  i telegramda nimadur qisa shulani yozish kere
     * */

    String ADMIN_MENU = menuCreate("admin menu") + """
            1. Show users
            2. Search user
            3. My Channels
            4. My Groups
            5. My Chats
            6. Show all channels
            7. Show all groups
            8. User control
            9. Group control
            10 Channel control
            11. Edit Profile
              0. Log out""" + last();
    String USER_CONTROL_MENU = menuCreate("user control menu") + """
            1. User information
            2. Show user's channels
            3. View user's groups
            4. Block user
            5. Unblock user
            6. Call the user
              0. back to menu""" + last();
    String GROUP_CONTROL_MENU = menuCreate("group control menu") + """
            1. show group users
            2. Block or unblock group
            3. show group messages
              0. back to menu""" + last();

    String CHANNEL_CONTROL_MENU = menuCreate("channel control menu") + """
            1. show channel users
            2. Block or unblock channel
            3. show channel messages
              0. back to menu""" + last();

    String USER_MENU = menuCreate("user menu") + """
            1. My Groups
            2. My Chats
            3. My subscribe channels
            4. My channels
            5. Groups
            5. Channels
            6. Edit Profile
            7. Delete account
              0. Log Out""" + last();

    String CHANNEL_OWNER_MENU = menuCreate("channel owner's menu") + """
            1. Write message
            2. Edit message
            3. Delete message
            4. Add user to channel
            5. Edit Description
            6. Edit Profile
            7. Delete Channel
              0. Exit""" + last();


    String USER_CHANNEL_MENU = menuCreate("channel user's menu") + """
            1. Show description
            2. Leave the channel
              0. Exit""" + last();


    String GROUP_OWNER_MENU = menuCreate("group owner's menu") + """
            1. Write message
            2. Edit message (only our message)
            3. Delete message (can delete any message)
            4. Add user to group
            5. Change user role
            6. Show users
            7. Edit group name
            8. Edit description
            9. Kick out user
            10. Delete group
              0. Exit""" + last();

    String GROUP_ADMIN_MENU = menuCreate("group admin's menu") + """
            1. Write message
            2. Edit messages (only our message)
            3. Delete message (can delete any message)
            4. Add user
            5. Show users
            6. Kick out user
            7. Leave group
              0. Exit""" + last();

    String GROUP_USER_MENU = menuCreate("group user's menu") + """
            1. Write message
            2. Edit message (only our message)
            3. Delete message (can delete our message)
            4. Show users
            5. Leave group
              0. Exit""" + last();

    String CHAT_MENU = menuCreate("chat menu") + """
            1. Write message
            2. Edit message (only our message)
            3. Delete message (can delete our message)
            4. Delete chat
              0. Exit""" + last();


    String SEARCH_USER_MENU = menuCreate("search user menu") + """
            1. Name
            2. Username
            3. Role
            4. Status
              0. Back to Menu""" + last();


    String USER_PROFILE_SETTINGS = menuCreate("user profile settings") + """
            1. Change first name
            2. Change last name
            3. Change birth date
            4. Change username
              0.Exit""" + last();

    String FIND_USER_MENU = menuCreate("find user menu") + """
            1. Name
            2. Username
              0.Exit""" + last();

    static int menu(String menu) {
        System.out.println(menu);
        return ScanInput.getInt("Choice: ");
    }

    static String menuCreate(String menuName){
        menuName = menuName.toUpperCase();
        String repeat = REPEAT_SIGN.repeat(COUNT_OF_REPEAT);
        int width = (COUNT_OF_REPEAT - menuName.length()) / 2;
        return repeat + "\n" + " ".repeat(width) + menuName + "\n" + repeat + "\n";
    }

    static String last(){
        return "\n" + REPEAT_SIGN.repeat(COUNT_OF_REPEAT);
    }
/*
    static void main(String[] args) {
        System.out.println(menuCreate("user menu"));
        System.out.println(menuCreate("user control menu"));

        System.out.println(ADMIN_MENU);
        System.out.println(MAIN);
        System.out.println(FIND_USER_MENU);
        System.out.println(USER_PROFILE_SETTINGS);
        System.out.println(SEARCH_USER_MENU);
        System.out.println(GROUP_USER_MENU);
        System.out.println(CHAT_MENU);
        System.out.println(USER_CONTROL_MENU);
        System.out.println(GROUP_ADMIN_MENU);
        System.out.println(CHANNEL_OWNER_MENU);
        System.out.println(USER_CHANNEL_MENU);
        System.out.println(GROUP_OWNER_MENU);
    }*/
}
