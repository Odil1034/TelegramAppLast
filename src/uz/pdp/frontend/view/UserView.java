package uz.pdp.frontend.view;

import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.FollowerService.FollowerService;
import uz.pdp.backend.service.FollowerService.FollowerServiceImp;
import uz.pdp.backend.service.channelService.ChannelService;
import uz.pdp.backend.service.channelService.ChannelServiceImp;
import uz.pdp.backend.service.chatService.ChatService;
import uz.pdp.backend.service.chatService.ChatServiceImp;
import uz.pdp.backend.service.groupFollowerService.GroupFollowerService;
import uz.pdp.backend.service.groupFollowerService.GroupFollowerServiceImp;
import uz.pdp.backend.service.groupService.GroupService;
import uz.pdp.backend.service.groupService.GroupServiceImp;
import uz.pdp.backend.service.messageService.MessageService;
import uz.pdp.backend.service.messageService.MessageServiceImp;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;
import uz.pdp.frontend.utills.MenuUtils;

public class UserView {

    private static UserService userService = UserServiceImp.getInstance();
    private static GroupService groupService = GroupServiceImp.getInstance();
    private static MessageService messageService = MessageServiceImp.getInstance();
    private static FollowerService followerService = FollowerServiceImp.getInstance();
    private static ChannelService channelService = ChannelServiceImp.getInstance();
    private static GroupFollowerService groupFollowerService = GroupFollowerServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();
    private static User curUser;

    public static void profile(User user) {
        curUser = user;

        /**
         * User menu
         * 1. My Groups
         * 2. My Chats
         * 3. My Channels
         * 4. Groups
         * 5. Channels
         * 6. My profile
         * 7. Delete account
         * 0. Log Out
         */
        while (true) {
            int choice = MenuUtils.menu(MenuUtils.USER_MENU);
            switch (choice) {
                case 1 -> UserView.showMyGroups(curUser);
                case 2 -> showMyChats(curUser);
                case 3 -> showMyChannels(curUser);
                case 5 -> groups();
                case 6 -> channels();
                case 7 -> deleteAccount();
                case 4 -> editProfile(curUser);
                case 0 -> {
                    System.out.println("logged out");
                    curUser = null;
                    return;
                }
                default -> System.out.println("Incorrect selection, please try again");

            }
        }
    }

    private static void showMyGroups(User curUser) {

    }

    private static void showMyChats(User curUser) {

    }

    private static void showMyChannels(User curUser) {

    }

    private static void groups() {

    }

    private static void channels() {

    }

    private static void deleteAccount() {

    }

    private static void editProfile(User curUser) {

    }


}
