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
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.utills.ScanInput;

import java.util.List;
import java.util.Set;

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
        1. Show users (%d)
        2. Search user
        3. Show channels
        4. Show groups
        0. Log out
        */
        int menu = MenuUtils.menu(MenuUtils.ADMIN_MENU);
        while (true){
            switch (menu){
                case 1 -> {
                    showUsers();
                }
                case 2 -> {
                    searchUser();
                }
                case 3 -> {
                    AdminView.showChannels();
                }
                case 4 -> {
                    showGroups();
                }
            }
        }
    }

    private static void showGroups() {
        System.out.println("EXIST GROUPS: \n" + "=".repeat(50));
        List<Group> groupList = groupService.getList();
        for (int i = 0; i < groupList.size(); i++) {
            Group group = groupList.get(i);
            int countOfSubscribes = groupService.getUsersInGroup(String.valueOf(groupList.get(i))).size();;

            String ownerID = groupService.getOwnerByGroupId(group.getID());
            User owner = userService.get(ownerID);
            System.out.println(i+1 + ". GroupName: " + group.getName() +
                               " Owner: " + owner.getUsername() +
                               "   " + countOfSubscribes + " subscribes");
        }
    }

    private static List<User> showUsers() {
        List<User> usersList = userService.getList();

        System.out.println("User List: \n" + "=".repeat(50));
        System.out.println("№\t USERNAME \t ROLE \t STATUS \t BIRTHDAY\n");
        for (int i = 0; i < usersList.size(); i++) {
            User user = usersList.get(i);
            System.out.println(i+1 + ". \t " + user.getUsername() + " \t   " + user.getRole() + " \t "
                               + user.getStatus() + " \t " + user.getBirthDay());
        }
        int userInd = ScanInput.getInt("Choose user: ") - 1;
        UserControl(usersList.get(userInd));
        return usersList;

    }

    private static void UserControl(User user) {
        int menu = MenuUtils.menu(MenuUtils.USER_CONTROL_MENU);
        /**1. Show user info
        2. Show user channels
        3. Show user groups
        4. Block user
        5. Unblock user
        0. back to menu*/
        while (true){
            switch (menu){
                case 1 -> {
                    showUserInfo(user);
                }
                case 2 -> {
                    showUserChannels(user);
                }
                case 3 -> {
                    showUserGroups(user);
                }
                case 4 -> {
                    blockUser(user);
                }
                case 5 -> {
                    unblockUser(user);
                }
                case 0 -> {
                    return;
                }
                default -> {
                    System.out.println("Wrong choice menu, try again ❌❌❌");
                }
            }
        }
    }

    private static void showUserGroups(User user) {
        System.out.printf("""
                ==========================================================
                                        USER %d GROUPS
                ==========================================================
                """, user.getUsername());

        List<Group> userGroups = groupService.getGroups(user.getID());
        for (int i = 0; i < userGroups.size(); i++) {
            Group group = userGroups.get(i);
            User ownerGroup = userService.get(group.getOwnerID());
            Set<String> groupUsers = groupService.getUsersInGroup(group.getID());
            int countMembers = groupUsers.size();
            System.out.println(i+1 + ". " + group.getName() +
                               "\tOwner: " + ownerGroup.getUsername() +
                               "\t"+ countMembers +" members");
        }

        int groupInd = ScanInput.getInt("Choose group: ") - 1;
        EnterGroup(userGroups.get((groupInd)));
    }

    private static void EnterGroup(Group group) {
        System.out.printf("""
                ==========================================================
                                        GROUP %s
                ==========================================================
                """, group.getName());
    }

    private static void showUserChannels(User user) {
        System.out.printf("""
                ==========================================================
                                        USER %d CHANNELS
                ==========================================================
                """, user.getUsername());

        List<Channel> channels = channelService.getChannels(user.getID());
        for (int i = 0; i < channels.size(); i++) {
            Channel channel = channels.get(i);
            User authorChannel = userService.get(channel.getAuthorID());
            System.out.println(i+1 + ". " + channel.getName() + "\t" + authorChannel.getUsername());
        }

        int channelInd = ScanInput.getInt("Choose channel: ") - 1;
        EnterChannel(channels.get(channelInd));
    }

    private static void EnterChannel(Channel channel) {
        System.out.printf("""
                ==========================================================
                                        %d CHANNEL
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
//                           "\nAge: " + user.getAge()
                           "\nRole: " + user.getRole() +
                           "\nStatus: " + user.getStatus());
    }


    private static void showChannels() {
        System.out.println("EXIST CHANNELS: \n" + "=".repeat(50));
        List<Channel> channelList = channelService.getList();
        int countOfSubscribes = 0;
        List<User> userList = userService.getList();
        for (int i = 0; i < channelList.size(); i++) {
            User user = userList.get(i);
            Channel channel = channelList.get(i);
            if(channelService.userSubscriptionToChannel(channel.getID(), user.getID())){
                countOfSubscribes ++;
            }
            User author = userService.get(channel.getAuthorID());
            System.out.println(i+1 + ". " + channel.getName() + "   " + author.getUsername() + "   " +
                               countOfSubscribes + "subscribes");
        }
    }

    private static void searchUser() {
        List<User> users = userService.getList();
        System.out.println(" ================== SEARCH USER ===========================");
        int search = ScanInput.getInt("Fields :\n" + "=".repeat(50) +
                       "\n 1. name \n 2. username \n 3. role \n 4. status \n 5. Back to Menu");

        switch (search){
            case 1 -> {

            }
            case 2 -> {

            }
            case 3 -> {

            }
        }

    }

    public static void main(String[] args) {
        showUsers();
        showChannels();
        showGroups();
    }
}
