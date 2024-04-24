package uz.pdp.frontend.view;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.FollowerService.FollowerService;
import uz.pdp.backend.service.FollowerService.FollowerServiceImp;
import uz.pdp.backend.service.channelService.ChannelService;
import uz.pdp.backend.service.channelService.ChannelServiceImp;
import uz.pdp.backend.service.chatService.ChatService;
import uz.pdp.backend.service.chatService.ChatServiceImp;
import uz.pdp.backend.service.groupService.GroupService;
import uz.pdp.backend.service.groupService.GroupServiceImp;
import uz.pdp.backend.service.messageService.MessageService;
import uz.pdp.backend.service.messageService.MessageServiceImp;
import uz.pdp.backend.service.groupFollowerService.GroupFollowerService;
import uz.pdp.backend.service.groupFollowerService.GroupFollowerServiceImp;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;

import static uz.pdp.frontend.utills.MenuUtils.*;
import static uz.pdp.frontend.utills.MessageUtils.*;
import static uz.pdp.frontend.utills.ScanInput.*;

import java.util.List;

public class AdminView {

    private static final UserService userService = UserServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();
    private static final ChannelService channelService = ChannelServiceImp.getInstance();
    private static final GroupService groupService = GroupServiceImp.getInstance();
    private static final MessageService messageService = MessageServiceImp.getInstance();
    private static final GroupFollowerService groupFollowerService = GroupFollowerServiceImp.getInstance();
    private static final FollowerService followerService = FollowerServiceImp.getInstance();
    private static User admin;

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

    public static void profile(User curUser) {
        admin = curUser;

       /* 1. Show users
        2. Search user
        3. My Channels
        4. My Groups
        5. My Chats
        6. Show channels
        7. Show groups
        8. User control
        9. Group control
        10 Channel control
        11. Edit Profile
        0. Log out""" + last();*/
        while (true) {
            int menu = menu(ADMIN_MENU);
            switch (menu){
                case 1 -> showUsers(userService.getList());
                case 2 -> searchUser();
                case 3 -> showMyChannels(admin);
                case 4 -> showMyGroups(admin);
                case 5 -> showMyChats(admin);
                case 6 -> showChannels(admin);
                case 7 -> showGroups(groupService.getList());
                case 8 -> userControl(null);
                case 9 -> groupControl();
                case 10 -> channelControl();
                case 11 -> {
                    if (logOut() == 1) {
                        return;
                    }
                }
            }
        }

    }


    private static void showUsers(List<User> userList) {
        if(userList.isEmpty()) NotFound("user");
        System.out.print(menuCreate("show users"));

        System.out.format("%-3S" + "%-12S"+"%-12S" + "%-10S" + " %-5S %n%n",
                "№", "username", "password", "status", "role");
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            System.out.format("%-3d" + "%-12S"+"%-12S" + "%-10S" + " %-5S %n",
                    i+1, user.getUsername(), user.getPassword(), user.getStatus(), user.getRole());
            /*System.out.println(
                    i+1  + "  " + "username: " + user.getUsername() + "    password: " +
                    user.getPassword() + "    status: " + user.getStatus() +
                    "    role: " + user.getRole());*/
        }
        System.out.println(last());
    }

    private static void searchUser() {
        System.out.println(menuCreate("searching user by"));
        while (true){
            System.out.println("\n" +
                               "1. username \n" +
                               "2. name \n" +
                               "3. status \n" +
                               "4. role \n" +
                               "   0. Exit" + last());
            int ch = getInt("Choose: ");
            String userField;
            switch (ch){
                case 1 -> searchingUserByUsername();
                case 2 -> searchingUserByName();
                case 3 -> searchingUserByStatus();
                case 4 -> searchingUserByRole();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Wrong choice, try again");
            }
        }
    }

    private static void searchingUserByRole() {
        UserRole.showType();
        int typeInd = getInt("Choose : ") - 1;
        StatusType type = StatusType.getType(typeInd);

        List<User> foundUsers = userService.getListMatchStatusType(type);

        if (foundUsers != null) showUsers(foundUsers);
        else NotFound("user");
    }

    private static void searchingUserByStatus() {
        StatusType.showType();
        int typeInd = getInt("Choose : ") - 1;
        StatusType type = StatusType.getType(typeInd);

        List<User> foundUsers = userService.getListMatchStatusType(type);

        if (foundUsers != null) showUsers(foundUsers);
        else NotFound("user");
    }

    private static void searchingUserByName() {
        String userField;
        userField = getStr("Enter searching user's name: ");
        List<User> listMatchName = userService.getListMatchName(userField);
        showUsers(listMatchName);
    }

    private static void searchingUserByUsername() {
        String userField;
        userField = getStr("Enter searching username: ");
        User foundUser = userService.getUserByUsername(userField);

        if (foundUser != null) userControl(foundUser);
        else NotFound("user");
    }


    private static void showMyChannels(User admin) {

    }

    private static void showMyGroups(User admin) {

    }

    private static void showMyChats(User admin) {

    }

    private static void showChannels(User admin) {

    }

    private static void showGroups(List<Group> groupList) {

    }

    private static void userControl(User user) {

    }

    private static void groupControl() {

    }
    private static void channelControl() {

    }

    private static int logOut() {
        while (true){
            int exit = getInt("Are you want exit from your Profile ? \n 1. Yes \n 2. No");
            if (exit == 1) {
                return 1;
            } else if (exit == 2);
            else {
                System.out.println("Wrong choice, please valid value");
            }
        }
    }

    public static void main(String[] args) {
        showUsers(userService.getList());
    }
}
