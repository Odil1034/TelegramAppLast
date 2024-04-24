package uz.pdp.frontend.view;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.models.follower.Follower;
import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.message.Message;
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
import uz.pdp.backend.types.channel.ChannelRole;
import uz.pdp.backend.types.receiverType.ReceiverType;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;

import static uz.pdp.frontend.utills.MenuUtils.*;
import static uz.pdp.frontend.utills.MessageUtils.*;
import static uz.pdp.frontend.utills.ScanInput.*;
import static uz.pdp.frontend.view.CommonView.*;

import java.time.LocalDateTime;
import java.util.List;

public class AdminView {

    private static final UserService userService = UserServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();
    private static final ChannelService channelService = ChannelServiceImp.getInstance();
    private static final GroupService groupService = GroupServiceImp.getInstance();
    private static final MessageService messageService = MessageServiceImp.getInstance();
    private static final GroupFollowerService groupFollowerService = GroupFollowerServiceImp.getInstance();
    private static final FollowerService followerService = FollowerServiceImp.getInstance();
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
            switch (menu) {
                case 1 -> showUsers(userService.getList());
                case 2 -> searchUser();
                case 3 -> showMyChannels(curUser);
                case 4 -> showMyGroups(curUser);
                case 5 -> showMyChats(curUser);
                case 6 -> showAllChannels(curUser);
                case 7 -> showAllGroups(groupService.getList());
                case 8 -> userControl(null);
                case 9 -> groupControl();
                case 10 -> channelControl();
                case 11 -> {
                    if (logOut() == 1) {
                        return;
                    }
                }
                default -> System.out.println("Wrong choice menu, try again ❌❌❌");
            }
        }
    }


    private static void showUsers(List<User> userList) {
        if (userList == null) {
            System.out.println("Anything is wrong");
            return;
        }
        if (userList.isEmpty()) notFound("user");
        else {
            System.out.print(menuCreate("show users"));

            System.out.format("%-3S  %-12S   %-12S   %-10S    %-5S %n%n",
                    "№", "username", "password", "status", "role");
            for (int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                System.out.format("%-3d  %-12s   %-12s   %-10S    %-5S %n",
                        i + 1, user.getUsername(), user.getPassword(), user.getStatus(), user.getRole());
            /*System.out.println(
                    i+1  + "  " + "username: " + user.getUsername() + "    password: " +
                    user.getPassword() + "    status: " + user.getStatus() +
                    "    role: " + user.getRole());*/
            }
            System.out.println(last());
        }
    }

    private static User searchUser() {

        System.out.print(menuCreate("searching user by"));
        while (true) {
            System.out.println("\n" +
                               "1. username \n" +
                               "2. name \n" +
                               "3. status \n" +
                               "4. role \n" +
                               "   0. Exit" + last());
            int ch = getInt("Choose: ");
            switch (ch) {
                case 1 -> {
                    return searchingUserByUsername();
                }
                case 2 -> {
                    return searchingUserByName();
                }
                case 3 -> {
                    return searchingUserByStatus();
                }
                case 4 -> {
                    return searchingUserByRole();
                }
                case 0 -> {
                    return null;
                }
                default -> System.out.println("Wrong choice, try again");
            }
        }
    }

    private static User searchingUserByUsername() {
        String username = getStr("Enter searching username: ");
        User foundUser = userService.getUserByUsername(username);

        if (foundUser != null) return foundUser;
        else notFound("user");
        return null;
    }

    private static User searchingUserByName() {
        String name = getStr("Enter searching user's name: ");
        List<User> userListMatchName = userService.getListMatchName(name);

        if (userListMatchName == null) {
            notFound("user");
            return null;
        } else {
            showUsers(userListMatchName);
            int index = getInt("Choose: ") - 1;
            return userListMatchName.get(index);
        }
    }

    private static User searchingUserByStatus() {
        StatusType.showType();
        int typeInd = getInt("Choose : ") - 1;
        StatusType type = StatusType.getType(typeInd);

        List<User> foundUsers = userService.getListMatchStatusType(type);

        if (foundUsers == null) {
            notFound("user");
            return null;
        } else {
            showUsers(foundUsers);
            int index = getInt("Choose: ") - 1;
            return foundUsers.get(index);
        }
    }

    private static User searchingUserByRole() {
        UserRole.showType();
        int typeInd = getInt("Choose : ") - 1;
        StatusType type = StatusType.getType(typeInd);

        List<User> foundUsers = userService.getListMatchStatusType(type);

        if (foundUsers == null) {
            notFound("user");
            return null;
        } else {
            showUsers(foundUsers);
            int index = getInt("Choose: ") - 1;
            return foundUsers.get(index);
        }
    }

    private static void showMyChannels(User admin) {
        List<Channel> adminChannels = followerService.getSubscribeChannels(admin.getID());

        if (adminChannels.isEmpty()) {
            while (true) {
                System.out.println("No channel has been created");
                System.out.print(menuCreate("create new channel"));
                System.out.println(("\n 1. Create new Channel \n 0. Exit"));
                System.out.println(last());
                int ques = getInt("\n Choose: ");
                if (ques != 1) {
                    if (ques == 0) return;
                    else System.out.println("Anything is wrong ❌❌❌");
                } else {
                    Channel newChannel = createNewChannel(curUser);
                    showChannel(newChannel);
                }
            }
        }
        System.out.println(menuCreate("your channels"));

        if (!adminChannels.isEmpty()) {
            for (int i = 0; i < adminChannels.size(); i++) {
                Channel channel = adminChannels.get(i);
                int countOfFollowers = followerService.getCountOfUsersInChannel(channel.getID());
                System.out.println(i + 1 + ". " +
                                   channel.getName() + "     " + countOfFollowers + "  subscriber");
            }
        } else {
            notFound("You are subscribe channel");
        }

        System.out.println(last());
    }

    private static void showMyGroups(User admin) {

    }

    private static void showMyChats(User admin) {

    }

    private static void showAllChannels(User admin) {

    }

    private static void showAllGroups(List<Group> groupList) {

    }

    private static void userControl(User user) {

    }

    private static void groupControl() {

    }

    private static void channelControl() {

    }

    static void showChannel(Channel channel) {
        if (channel != null) {
            int i = 1;
            List<Message> channelMessages = null;
            List<Message> myMessages = null;
            channelMenu: while (i == 1) {
                System.out.println(menuCreate(channel.getName()));
                channelMessages = messageService.getChannelMessages(channel.getID(), ReceiverType.CHANNEL);
                myMessages = messageService.getMyMessages(channelMessages, curUser.getID());
                showMessages(channelMessages);
                break;
            }
            if (channel.getOwnerID().equals(curUser.getID())) {
                adminMenu: while (i == 0) {
                    int menu = menu(CHANNEL_OWNER_MENU);
                    switch (menu) {
                        case 1 -> writeMessage(channel.getID());
                        case 2 -> editMessage(myMessages);
                        case 3 -> deleteMessage(channelMessages);
                        case 4 -> addUserToChannel(channel.getID());
                        case 5 -> editDescription(channel);
                        case 6 -> deleteChannel(channel);
                        case 0 -> i = 0;
                        default -> System.out.println("Wrong choice menu ❌❌❌");
                    }
                    i = 1;
                }
            }
        } else {
            System.out.println("Anything is wrong ❌❌❌");
        }
    }

    private static void deleteChannel(Channel channel) {
        while (true) {
            int q = getInt("Are you want delete channel? \n 1. Yes \n 2. No");
            if (q == 1) {
                if (channelService.deleteChannel(channel.getID())) {
                    System.out.println("Channel " + channel.getName() + " is deleted ✔✔");
                } else {
                    System.out.println("Something is wrong ❌❌");
                }
            } else if (q == 2) {
                return;
            } else {
                System.out.println("Invalid value, please enter again ❌❌❌");
            }
        }
    }

    private static void editDescription(Channel channel) {
        String newDescription = getStr("Enter new description: ");
        channel.setDescription(newDescription);
        channelService.update(channel);
        System.out.println("Channel description updated ✔");
    }

    private static void addUserToChannel(String channelID) {
        List<User> users = userService.getList();
        showUsers(users);
        int userInd = getInt("Choose user: ") - 1;
        User user = users.get(userInd);
        Follower newFollower = new Follower(channelID, user.getID(), ChannelRole.USER);
        if (followerService.create(newFollower)) {
            System.out.println("User " + user.getUsername() + " is added ✔");
        }
    }

    private static void deleteMessage(List<Message> channelMessages) {
        showMessages(channelMessages);
        int mesInd = getInt("Choose: ") - 1;
        String deletedMessageID = channelMessages.get(mesInd).getID();
        if (messageService.delete(deletedMessageID)) {
            System.out.println("Message is deleted ✔");
        } else {
            System.out.println("Something is wrong ❌");
        }
    }

    private static void editMessage(List<Message> ownMessages) {
        if (ownMessages == null) {
            System.out.println("Anything is wrong ❌❌❌");
        } else {
            if (ownMessages.isEmpty()) {
                System.out.println("You don't have own messages");
            } else {
                showMessages(ownMessages);
                int messInd = getInt("Choose: ") - 1;
                Message message = ownMessages.get(messInd);
                String newContent = getStr("Enter new Context: ");
                LocalDateTime dateTime = LocalDateTime.now();
                message.setContent(newContent);
                message.setDateTime(dateTime);

                messageService.update(message);
                System.out.println("Message edited ✔✔✔");
            }
        }
    }

    private static void writeMessage(String receiverID) {
        String content = getStr("Enter message: ");
        Message newMessage = new Message(curUser.getID(), content,
                receiverID, ReceiverType.CHANNEL, LocalDateTime.now());

        if (messageService.create(newMessage)) {
            System.out.println("Message sent ✅✅✅");
        } else {
            System.out.println("Message don't sent ❌❌❌");
        }
    }

    private static int logOut() {
        while (true) {
            int exit = getInt("Are you want exit from your Profile ? \n 1. Yes \n 2. No");
            if (exit == 1) {
                return 1;
            } else if (exit == 2) ;
            else {
                System.out.println("Wrong choice, please valid value");
            }
        }
    }

    public static void main(String[] args) {
        showUsers(userService.getList());
//        User user = searchUser();
//        List<User> user1 = List.of(user);
//        showUsers(user1);
        /*searchingUserByUsername();
        searchingUserByName();
        searchingUserByRole();
        searchingUserByStatus();*/
    }
}
