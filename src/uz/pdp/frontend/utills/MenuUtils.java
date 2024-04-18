package uz.pdp.frontend.utills;

public interface MenuUtils {

    // main Menu
    String MAIN = "MAIN MENU \n1. Login In \n2. Sign Up \n3. Exit";
    String ADMIN_MENU = "1. Show users (soni) \n2. Block or unblock user\n3. Write message " +
                       "\n4. Delete message \n5. Change group name \n6. Change group picture " +
                       "\n7. Add admin \n8. Chat history \n9. Search user \n10. Delete and leave group";

    String USER_MENU = "\n1. Show message \n2. Write message \n3. Edit message " +
                       "\n4. Delete message \n5. Add user(Lichniy users) \n6. Save user's contact" +
                       "\n7. Search message \n8. Search chat \n9. Search user " +
                       "\n10. Block user";


    static int menu(String menu){
        System.out.println(menu);
        return InputStream.getInt("Choose: ");
    }
}
