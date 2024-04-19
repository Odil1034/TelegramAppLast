package uz.pdp.frontend.utills;

public interface MenuUtils {

    // main Menu
    String MAIN = """
            ==========================================================
            MAIN MENU\s
            1. Login In\s
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


    static int menu(String menu){
        System.out.println(menu);
        return InputStream.getInt("choice: ");
    }
}
