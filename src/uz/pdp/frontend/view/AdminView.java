package uz.pdp.frontend.view;

import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.channelService.ChannelService;
import uz.pdp.backend.service.channelService.ChannelServiceImp;
import uz.pdp.backend.service.chatService.ChatService;
import uz.pdp.backend.service.chatService.ChatServiceImp;
import uz.pdp.backend.service.groupService.GroupService;
import uz.pdp.backend.service.groupService.GroupServiceImp;
import uz.pdp.backend.service.messageService.MessageService;
import uz.pdp.backend.service.messageService.MessageServiceImp;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.utills.ScanInput;

import java.util.List;

public class AdminView {

    private static final UserService userService = UserServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();
    private static final ChannelService channelService = ChannelServiceImp.getInstance();
    private static final GroupService groupService = GroupServiceImp.getInstance();
    private static final MessageService messageService = MessageServiceImp.getInstance();
    private static User curUser;


    public static void profile(User admin) {
        curUser = admin;

        /*1. Show users (%d)
        2. Search user
        3. Show channels
        4. Show groups
        5...
        6...
        0. Log out
            ========================================================== */
        int menu = MenuUtils.menu(MenuUtils.ADMIN_MENU);
        switch (menu){
            case 1 -> {
                showUsers();
            }
            case 2 -> {
                searchUser();
            }
        }
    }

    private static void searchUser() {
        showUsers();
        System.out.println(" ================== SEARCH USER ===========================");
        int search = ScanInput.getInt("Fields :\n" + "=".repeat(50) +
                       "\n 1. name \n 2. username \n 3. role \n 4. status \n 5. Back to Menu");

        switch (search){
            case 1 -> {
                String name = ScanInput.getStr("Enter name: ");

            }
        }


    }

    private static void showUsers() {
        List<User> usersList = userService.getList();

        System.out.println("User List: \n" + "=".repeat(50));
        System.out.println("№\t USERNAME \t ROLE \t STATUS \t BIRTHDAY\n");
        for (int i = 0; i < usersList.size(); i++) {
            User user = usersList.get(i);
            System.out.println(i+1 + ". \t " + user.getUsername() + " \t   " + user.getRole() + " \t "
                               + user.getStatus() + " \t " + user.getBirthDay());
        }
    }

    public static void main(String[] args) {
        showUsers();
    }
}
