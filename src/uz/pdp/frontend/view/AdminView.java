package uz.pdp.frontend.view;

import uz.pdp.backend.models.user.User;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.utills.ScanInput;

import java.util.List;

import static uz.pdp.frontend.view.CommonMenuMethods.*;
public class AdminView {


    public static void profile(User admin) {

        int menu = MenuUtils.menu(MenuUtils.ADMIN_MENU);
        while (true) {
            switch (menu) {
                case 1 -> {
                    List<User> users = userService.getList();
                    showUsers(users);
                    int ind = ScanInput.getInt("Choose user: ") - 1;
                    if (ind < 0 || ind >= users.size()){
                        System.out.println("wrong choice");

                    }else {
                    userControl(users.get(ind));
                    }
                }
                case 2 -> {
                    User user = findUser();
                    userControl(user);
                }
                case 3 -> showChannels(channelService.getList());

                case 4 -> showGroups(groupService.getList());


                case 0 -> {
                    return;
                }
                default -> System.out.println("Wrong choice menu, try again ❌❌❌");

            }
        }
    }

    private static void userControl(User user) {

        while (true){
            int menu = MenuUtils.menu(MenuUtils.USER_CONTROL_MENU);
            switch (menu) {
                case 1 -> showProfile(user);
                case 2 -> showChannels(user);
                case 3 -> showGroups(groupService.getList());

                case 4 -> blockUser(user);
                case 5 -> unblockUser(user);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Wrong choice menu, try again ❌❌❌");

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




   /* private static void showUserGroups(User user) {
        System.out.printf("""
                ==========================================================
                                        USER %S GROUPS
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
        System.out.printf("""
                ==========================================================
                                        GROUP %S
                ==========================================================
                """, group.getName());
        List<String> messagesIDInGroup = groupService.getMessagesInGroup(group.getID());

//        messageService.getMessagesByChatID();
    }

    private static void showUserChannels(User user) {
        System.out.printf("""
                ==========================================================
                                        USER %S CHANNELS
                ==========================================================
                """, user.getUsername());

        List<Channel> channels = channelService.getChannels(user.getID());
        for (int i = 0; i < channels.size(); i++) {
            Channel channel = channels.get(i);
            User authorChannel = userService.get(channel.getAuthorID());
            System.out.println(i + 1 + ". " + channel.getName() + "\t" + authorChannel.getUsername());
        }

        int channelInd = ScanInput.getInt("Choose channel: ") - 1;
        EnterChannel(channels.get(channelInd));
    }

    private static void EnterChannel(Channel channel) {
        System.out.printf("""
                ==========================================================
                                        %S CHANNEL
                ==========================================================
                """, channel.getName());

    }





    private static void showChannels() {
        System.out.println("EXIST CHANNELS: \n" + "=".repeat(50));
        List<Channel> channelList = channelService.getList();
        int countOfSubscribes = 0;
        List<User> userList = userService.getList();
        for (int i = 0; i < channelList.size(); i++) {
            User user = userList.get(i);
            Channel channel = channelList.get(i);
            if (channelService.userSubscriptionToChannel(channel.getID(), user.getID())) {
                countOfSubscribes++;
            }
            User author = userService.get(channel.getAuthorID());
            System.out.println(i + 1 + ". " + channel.getName() + "   " + author.getUsername() + "   " +
                               countOfSubscribes + "subscribes");
        }
    }*/

}
