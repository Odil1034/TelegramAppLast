package uz.pdp.frontend.view;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.models.group.Group;
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
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.utills.ScanInput;

import java.util.List;

import static uz.pdp.frontend.view.CommonMenuMethods.*;

public class AdminView {

    private static final UserService userService = UserServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();
    private static final ChannelService channelService = ChannelServiceImp.getInstance();
    private static final GroupService groupService = GroupServiceImp.getInstance();
    private static final MessageService messageService = MessageServiceImp.getInstance();
    private static User curUser;

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

    public static void profile(User admin) {
        curUser = admin;

        /**
         1. Show users
         2. Search user
         3. Show channels
         4. Show groups
         0. Log out
         */
        while (true) {
            int menu = MenuUtils.menu(MenuUtils.ADMIN_MENU);
            switch (menu) {
                case 1 -> AdminView.showUsers(userService.getList());
                case 2 -> searchUser();
                case 3 -> AdminView.showChannels();
                case 4 -> showGroups();
                case 0 -> {
                    int change = ScanInput.getInt("Are you  want to exit of your profile? \n 1. Yes \n 2. No");
                    if (change == 1) {
                        return;
                    }
                }
                default -> {
                    System.out.println("Wrong choice menu, try again ❌❌❌");
                }
            }
        }
    }

    private static void showUsers(List<User> userList) {
        if(userList == null){
            System.out.println("User is not found");
        }else{

            System.out.printf("""
                ==========================================================
                                     USERS LIST  (%d users)
                ==========================================================
                """, userList.size());
            System.out.println("№\t USERNAME \t NAME \t ROLE \t STATUS \t BIRTHDAY \t      AGE\n");
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                System.out.println(i + 1 + "." +
                                   " \t " + user.getUsername() +
                                   " \t " + user.getName() +
                                   " \t   " + user.getRole() +
                                   " \t " + user.getStatus() + " " +
                                   "\t " + user.getBirthDay() +
                                   " \t " + userService.getUserAge(user));
            }
            System.out.println("=".repeat(50));

            int ind = ScanInput.getInt("Choose user: ") - 1;
            User user = userList.get(ind);
            UserControl(user);
        }
    }

    private static void showGroups() {
        System.out.println("EXIST GROUPS: \n" + "=".repeat(50));
        List<Group> groupList = groupService.getList();
        for (int i = 0; i < groupList.size(); i++) {
            Group group = groupList.get(i);
            List<String> users = groupService.getUsersInGroup(group.getID());
            int countOfSubscribes = users.size();

            String ownerID = String.valueOf(groupList.get(i).getOwnerID());
            User owner = userService.get(ownerID);
            System.out.println(i + 1 + ". GroupName: " + group.getName() +
                               " Owner: " + owner.getUsername() +
                               "   " + countOfSubscribes + " subscribes");
        }
        int groupInd = ScanInput.getInt("Choose group: ");
        Group group = groupList.get(groupInd);/*
        showMessages(, group.getID());*/
    }

    private static void UserControl(User user) {
        /**
         1. Show user info
         2. Show user channels
         3. Show user groups
         4. Block user
         5. Unblock user
         0. back to menu
         */
        while (true) {
            System.out.println("User : " + user.getUsername());
            int menu = MenuUtils.menu(MenuUtils.USER_CONTROL_MENU);
            switch (menu) {
                case 1 -> showUserInfo(user);
                case 2 -> showUserChannels(user);
                case 3 -> showUserGroups(user);
                case 4 -> blockUser(user);
                case 5 -> unblockUser(user);
                case 0 -> {
                    return;
                }
                default -> {
                    System.out.println("Wrong choice menu, try again ❌❌❌");
                }
            }
        }
    }

    private static void unblockUser(User user) {
        if (user.getStatus().equals(StatusType.BLOCKED)) {
            user.setStatus(StatusType.ACTIVE);
            userService.update(user);
        }
        System.out.println("user " + user.getUsername() + " was removed from the block ✅✅✅");
    }

    private static void blockUser(User user) {
        if (user.getStatus().equals(StatusType.ACTIVE)) {
            user.setStatus(StatusType.BLOCKED);
        }
        userService.update(user);
        System.out.println("User " + user.getUsername() + " is blocked ✅✅✅");
    }

    private static void showUserGroups(User user) {
        System.out.printf("""
                ==========================================================
                                        %S GROUPS
                ==========================================================
                """, user.getUsername());

        List<Group> userGroups = groupService.getGroups(user.getID());
        for (int i = 0; i < userGroups.size(); i++) {
            Group group = userGroups.get(i);
            User ownerGroup = userService.get(group.getOwnerID());
            List<String> groupUsers = groupService.getUsersInGroup(group.getID());
            int countMembers = groupUsers.size();
            System.out.println(i + 1 + ". " + group.getName() +
                               "\tOwner: " + ownerGroup.getUsername() +
                               "\t" + countMembers + " members");
        }

        int groupInd = ScanInput.getInt("Choose group: ") - 1;
        EnterGroup(userGroups.get((groupInd)));
    }

    private static void EnterGroup(Group group) {

    }

    private static void showUserChannels(User user) {

        List<Channel> channels = channelService.getChannels(user.getID());
        if (channels != null) {
            int countOfChannels = channels.size();

            System.out.printf("""
                    ==========================================================
                                   %S CHANNELS (%d channels)
                    ==========================================================
                    """, user.getUsername(), countOfChannels);

            for (int i = 0; i < channels.size(); i++) {
                Channel channel = channels.get(i);
                User authorChannel = userService.get(channel.getAuthorID());
                System.out.println(i + 1 + ". " + channel.getName() +
                                   "\t" + authorChannel.getUsername());
            }

            int channelInd = ScanInput.getInt("Choose channel: ") - 1;
            EnterChannel(channels.get(channelInd));
        } else {
            System.out.println(user.getUsername() + " does not have a channel");
        }
    }

    private static void EnterChannel(Channel channel) {
        System.out.printf("""
                ==========================================================
                                        %S CHANNEL
                ==========================================================
                """, channel.getName());

    }

    private static void showUserInfo(User user) {
        System.out.println("""
                ==========================================================
                                        USER INFO
                ==========================================================""");
        System.out.println("UserName: " + user.getUsername() +
                           "\nPassword: " + user.getPassword() +
                           "\nName: " + user.getName() +
                           "\nLastName: " + user.getLastName() +
                           "\nBirthday: " + user.getBirthDay() +
                           "\nAge: " + userService.getUserAge(user) +
                           "\nRole: " + user.getRole() +
                           "\nStatus: " + user.getStatus());
    }


    private static void showChannels() {
        List<Channel> channelList = channelService.getList();
        if (channelList == null){
            System.out.println("Channel not found ❌❌❌");

            int choose = ScanInput.getInt("Are you want create new Channel? " +
                                          "\n 1. Yes \n 2. No \n Choose: ");
            if (choose == 1) getOrCreateChannel(curUser);

        }else {
            System.out.printf("""
                ==========================================================\s
                                ALL EXIST CHANNELS (%d channel)\s
                ==========================================================""",
                    channelList.size());

            int countOfSubscribes = 0;
            List<User> userList = userService.getList();
            for (int i = 0; i < channelList.size(); i++) {
                User user = userList.get(i);
                Channel channel = channelList.get(i);
                if (channelService.userSubscriptionToChannel(channel.getID(), user.getID())) {
                    countOfSubscribes++;
                }
                User author = userService.get(channel.getAuthorID());
                System.out.println(i + 1 + ". " + channel.getName() + "   " + author.getUsername() +
                                   "   (" + countOfSubscribes + "  subscribes)");
            }
        }

    }

    private static void searchUser() {
        showUsers(userService.getList());
        System.out.println("""
                ==========================================================
                                        SEARCH USER
                ==========================================================""");


        System.out.println(("Fields :\n" + "=".repeat(50) +
                            "\n 1. name " +
                            "\n 2. username " +
                            "\n 3. role " +
                            "\n 4. status " +
                            "\n 5. Back to Menu"));
        int search = ScanInput.getInt("Choose Field: ");

        List<User> users = userService.getList();

        User searchingUser = null;
        switch (search) {
            case 1 -> {
                String name = ScanInput.getStr("Enter name: ");
                for (User user : users) {
                    if (user.getName().equals(name)) {
                        searchingUser = user;
                        break;
                    }
                }
                if (searchingUser != null) {
                    UserControl(searchingUser);
                } else {
                    System.out.println("User is not found ❌❌❌");
                }
            }
            case 2 -> {
                String username = ScanInput.getStr("Enter username: ");
                for (User user : users) {
                    if (user.getUsername().equals(username)) {
                        searchingUser = user;
                        break;
                    }
                }
                if (searchingUser != null) {
                    UserControl(searchingUser);
                } else {
                    System.out.println("User is not found ❌❌❌");
                }
            }
            case 3 -> {

            }
        }

    }

}
