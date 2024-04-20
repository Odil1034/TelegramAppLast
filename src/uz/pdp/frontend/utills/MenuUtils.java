package uz.pdp.frontend.utills;

public interface MenuUtils {

    // main Menu
    String MAIN = """
            ==========================================================
            MAIN MENU\s
            1. Log In\s
            2. Sign Up\s
            3. Exit
            ==========================================================""";

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
    String ADMIN_MENU = """
            ==========================================================
            1. Show users (%d)
            2. Search user
            3. Show channels
            4. Show groups
            5...
            6...
            0. Log out
            ==========================================================""";
    String USER_CONTROL_MENU = """
            ==========================================================
            1. Show user info
            2. Show user channels
            3. Show user groups
            4. Block user
            5. Unblock user
            0. back to menu
            ==========================================================""";

    String USER_MENU = """
            ==========================================================
            1. Groups\s
            2. Chats\s
            3. Channels\s
            4. My profile\s
            0. Log Out\s
            ==========================================================""";

    String CHANNEL_OWNER_MENU = """
            ==========================================================
            1. Write message
            2. Edit message
            3. Delete message
            4. Add user to channel
            5. Edit Description
            6. Delete Channel
            0. Exit
            ==========================================================""";


    String CHANNEL_USERS_MENU = """
            ==========================================================
            1. Show description
            2. Leave channel
            0. Exit
            ==========================================================""";


    String GROUP_OWNER_MENU = """
            ==========================================================
            1. Write message
            2. Edit message (only our message)
            3. Delete message (can delete any message)
            4. Add user
            5. Change user role
            6. Show users
            7. Edit group name
            8. Edit description
            9. kick out user
            10. Delete group
            0. Exit
            ==========================================================""";

    String GROUP_ADMIN_MENU = """
            ==========================================================
            1. Write message
            2. Edit messages (only our message)
            3. Delete message (can delete any message)
            4. Add user
            5. Show users
            6. kick out user
            6. Leave group
            0. Exit
            ==========================================================""";

    String GROUP_USER_MENU = """
            ==========================================================
            1. Write message
            2. Edit message (only our message)
            3. Delete message (can delete our message)
            4. Show users
            5. Leave group
            0. Exit
            ==========================================================""";

    String CHAT_MENU = """
            ==========================================================
            1. Write message
            2. Edit message (only our message)
            3. Delete message (can delete our message)
            4. Delete chat
            0. Exit
            ==========================================================""";

    static int menu(String menu) {
        System.out.println(menu);
        return ScanInput.getInt("choice: ");
    }
}
