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
    String ADMIN_MENU = """
            ==========================================================
            1. Show users (%d)\s
            2. Block or unblock user\s
            3. Write message\s
            4. Delete message\s
            5. Change group name\s
            6. Change group picture\s
            7. Add admin\s
            8. Chat history\s
            9. Search user\s
            10. Delete and leave group
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
