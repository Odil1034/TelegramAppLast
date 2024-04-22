package uz.pdp.frontend.view;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.models.chat.Chat;
import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.types.user.UserRole;
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.utills.ScanInput;

import java.util.ArrayList;
import java.util.List;

import static uz.pdp.frontend.view.CommonMenuMethods.*;

public class UserView {

    private static User curUser;

    public static void profile(User user) {
        curUser = user;

        /**
         * User menu
         * 1. Groups
         * 2. Chats
         * 3. Channels
         * 4. My profile
         * 5. Delete account
         * 0. Log Out
         */
        while (true) {
            int choice = MenuUtils.menu(MenuUtils.USER_MENU);
            switch (choice) {
                case 1 -> {
                    Group group = getOrCreateGroup(curUser);
                    if (group == null) break;
                    //  showMessages(messageService.getMessagesByChatID(group.getID()), group.getName());
                    groupMenu(group);
                }

                case 2 -> {
                    Chat chat = getOrCreateChat(curUser);
                    if (chat != null) {
                        //  List<Message> messages = messageService.getMessagesByChatID(chat.getID());
                        chatMenu(chat, curUser);
                    } else {
                        System.out.println("something wrong");
                        return;
                    }
                }

                case 3 -> {
                    Channel channel = getOrCreateChannel(curUser);
                    if (channel == null) {
                        throw new RuntimeException("channel not found");
                    } else if (channel.getAuthorID().equals(curUser.getID())) {
                        channelOwnerMenu(channel);
                    } else if (channelService.hasUserSubscribedToChannel(channel.getID(), curUser.getID())) {
                        channelUserMenu(channel);
                    }
                }
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

    private static void channelUserMenu(Channel channel) {
        int menu = MenuUtils.menu(MenuUtils.CHANNEL_USERS_MENU);
        switch (menu) {
            case 1 -> System.out.println("==========================================================\n"
                    + channel.getName() + ": " + channel.getDescription() +
                    "\n==========================================================");
            case 2 -> {
                boolean b = channelService.unsubscribeChannel(channel.getID(), curUser.getID());
                if (b) {
                    System.out.println("You have left the channel");
                } else {
                    System.out.println("you are still a member of the channel");
                }
            }
            case 0 -> {
            }


            default -> throw new IllegalStateException("Unexpected value: " + menu);
        }
    }

    private static void channelOwnerMenu(Channel channel) {

        while (true) {
            showMessages(messageService.getMessagesByChatID(channel.getID()), channel.getName());

            int menu = MenuUtils.menu(MenuUtils.CHANNEL_OWNER_MENU);
            switch (menu) {
                case 1 -> writeNewMessage(channel, curUser);

                case 2 -> editMessage(channel, curUser);

                case 3 -> deleteMessage(channel, curUser);

                case 4 -> {
                    User user = findUser();
                    if (user == null) {
                        throw new RuntimeException("you did not search for the user correctly");
                    } else {
                        System.out.println(user);
                        int choice = MenuUtils.menu("""
                                Do you want to add this user to the channel?
                                1. Yes
                                2. No
                                """);
                        if (choice == 1)
                            channelService.userSubscriptionToChannel(channel.getID(), user.getID());
                        else if (choice == 2) return;
                        else {
                            System.out.println("wrong choice");
                            return;
                        }

                    }
                }

                case 5 -> {
                    String description = ScanInput.getStr("Enter new description: ");
                    channel.setDescription(description);
                    System.out.println("Channel description is changed");
                }

                case 6 -> {
                    int choice = MenuUtils.menu("""
                            Are you sure you want to delete the channel?
                            1. Yes
                            2. No
                            """);
                    if (choice == 1) {
                        boolean isDeleted = channelService.delete(channel.getID());
                        if (isDeleted) System.out.println("channel deleted successfully");
                        else System.out.println("channel is not deleted");
                    } else if (choice == 2) {
                        System.out.print("");
                    }
                    else {
                        System.out.println("wrong choice");
                    }
                }

                case 0 -> {
                    return;
                }
                default -> System.out.println("Unexpected value: " + menu);
            }
        }
    }


    private static void chatMenu(Chat chat, User currentUser) {
        String chatName = chatService.determineChatName(chat.getID(), currentUser.getID());
        while (true) {
            showMessages(messageService.getMessagesByChatID(chat.getID()), chatName);

            int menu = MenuUtils.menu(MenuUtils.CHAT_MENU);

            switch (menu) {
                case 1 -> writeNewMessage(chat, currentUser);

                case 2 -> editMessage(chat, currentUser);

                case 3 -> deleteMessage(chat);

                case 4 -> {
                    System.out.println("Are you sure delete chat?");
                    System.out.println("1. yes");
                    System.out.println("2. no");
                    int choice = ScanInput.getInt("choice: ");
                    if (choice == 1) {
                        boolean b = deleteChat(chat.getID());
                        if (b) {
                            System.out.println("message deleted successfully");
                        } else {
                            System.out.println("message is not deleted");
                        }
                        return;
                    } else if (choice == 2) {
                        return;
                    } else {
                        System.out.println("wrong choice");
                        return;
                    }
                }
                case 0 -> {
                    return;
                }
                default -> throw new IllegalStateException("Unexpected value: " + menu);
            }
        }
    }


    private static void groupMenu(Group group) {
        String curUserID = curUser.getID();
        String groupID = group.getID();
        String ownerID = group.getOwnerID();
        List<String> adminsInGroup = groupService.getAdminsInGroup(groupID);

        if (curUserID.equals(ownerID)) {
            groupOwnerMenu(group);
        } else if (adminsInGroup.contains(curUserID)) {
            groupAdminMenu(group);
        } else {
            groupUserMenu(group);
        }
    }

    private static void groupOwnerMenu(Group group) {
        while (true) {
            showMessages(messageService.getMessagesByChatID(group.getID()), group.getName());

            final List<String> adminsInGroup = groupService.getAdminsInGroup(group.getID());
            final List<String> usersInGroup = groupService.getUsersInGroup(group.getID());

            int menu = MenuUtils.menu(MenuUtils.GROUP_OWNER_MENU);
            /**
             * group Owner Menu
             * 1. Write message
             * 2. Edit message (only our message)
             * 3. Delete message (can delete any message)
             * 4. Add user
             * 5. Change user role
             * 6. Show users
             * 7. Edit group name
             * 8. Edit description
             * 9. Kick out user
             * 10. Delete group
             * 0. Exit
             */

            switch (menu) {
                case 1 -> writeNewMessage(group, curUser);

                case 2 -> editMessage(group, curUser);

                case 3 -> deleteMessage(group, curUser);

                case 4 -> {
                    // add user
                    User foundUser = findUser();
                    if (foundUser != null) {
                        System.out.println(foundUser);

                        int n = MenuUtils.menu("""
                                ==========================================================
                                can you add this user to your group?
                                 1.yes
                                 2.no
                                 ==========================================================""");

                        if (n == 1) addUser(group, foundUser);
                        else if (n == 2) return;
                        else System.out.println("wrong choice");
                    } else {
                        throw new RuntimeException("something happen");
                    }
                }
                case 5 -> {

                    List<User> list = userService.getList();
                    showUsers(list);
                    // change user role
                    int userInd = ScanInput.getInt("Choose user: ") - 1;
                    User user = list.get(userInd);
                    UserRole.showType();

                    System.out.println(("Old UserRole: " + user.getRole()));
                    int userRoleInd = ScanInput.getInt("Choose new UserRole: ") - 1;
                    UserRole type = UserRole.getType(userRoleInd);
                    user.setRole(type);
                    userService.update(user);
                    System.out.println("User: " + user.getUsername() + " Role changed to " + type);

                }
                case 6 -> {
                    // show user
                    List<String> usersIDInGroup = usersInGroup;
                    List<User> userList = new ArrayList<>();
                    for (String userID : usersIDInGroup) {
                        User user = userService.get(userID);
                        userList.add(user);
                    }
                    showUsers(userList);
                    usersInGroup.addAll(adminsInGroup);
                }
                case 7 -> {
                    // edit group name
                    String newName = ScanInput.getStr("enter new group name: ");
                    group.setName(newName);
                    System.out.println("group name is changed");
                }
                case 8 -> {
                    // edit description
                    String newDescription = ScanInput.getStr("enter new group description: ");
                    group.setDescription(newDescription);
                }
                case 9 -> {
                    // kick out user
                    //showUsers();

                }
                case 10 -> {
                    // delete group
                    int choice = MenuUtils.menu("""
                            Are you sure you want to delete the group?
                            1. Yes
                            2. No
                            """);
                    if (choice == 1) {
                        boolean isDeleted = groupService.delete(group.getID());
                        if (isDeleted) System.out.println("Group deleted successfully");
                        else System.out.println("Group is not deleted");
                    } else if (choice == 2) {
                        System.out.print("");
                    }
                    else {
                        System.out.println("wrong choice");
                    }
                }

                case 0 -> {
                    return;
                }
                default -> throw new IllegalStateException("Unexpected value: " + menu);
            }
        }
    }

    private static void showUsers(List<User> userList) {

        if(userList == null){
            System.out.println("user is not found");
            return;
        }
        System.out.printf("""
                ==========================================================
                                     USERS LIST  (%d users)
                ==========================================================
                """, userList.size());
        System.out.println("№\t USERNAME \t ROLE \t STATUS \t BIRTHDAY \t      AGE\n");
        for (int i = 0; i < userList.size(); i++) {

            User user = userList.get(i);
            System.out.println(i + 1 + "." +
                               " \t " + user.getUsername() +
                               " \t   " + user.getRole() +
                               " \t " + user.getStatus() + " " +
                               "\t " + user.getBirthDay() +
                               " \t " + userService.getUserAge(user));
        }
        System.out.println("=".repeat(50));
    }

    private static void groupAdminMenu(Group group) {
        showMessages(messageService.getMessagesByChatID(group.getID()), group.getName());
        
        MenuUtils.menu(MenuUtils.GROUP_ADMIN_MENU);
    }

    private static void groupUserMenu(Group group) {
        showMessages(messageService.getMessagesByChatID(group.getID()), group.getName());
        MenuUtils.menu(MenuUtils.GROUP_USER_MENU);
    }
}
