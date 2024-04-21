package uz.pdp.frontend.view;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.models.chat.Chat;
import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.message.Message;
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
import uz.pdp.backend.types.user.UserRole;
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.utills.ScanInput;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class UserView {
    private static final UserService userService = UserServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();
    private static final ChannelService channelService = ChannelServiceImp.getInstance();
    private static final GroupService groupService = GroupServiceImp.getInstance();
    private static final MessageService messageService = MessageServiceImp.getInstance();
    private static User curUser;

    public static void profile(User user) {
        curUser = user;
        while (true) {
            int choice = MenuUtils.menu(MenuUtils.USER_MENU);
            switch (choice) {
                case 1 -> {
                    Group group = getOrCreateGroup();
                    if (group == null) break;
                  //  showMessages(messageService.getMessagesByChatID(group.getID()), group.getName());
                    groupMenu(group);
                }
                case 2 -> {
                    Chat chat = showOrCreateChat();
                    if (chat != null) {
                        String name = chatService.determineChatName(chat.getID(), curUser.getID());
                      //   List<Message> messages = messageService.getMessagesByChatID(chat.getID());
                        writeMessageOnChat(chat, name);
                    } else {
                        System.out.println("something wrong");
                        return;
                    }
                }
                case 3 -> {
                    showChannels();
                }
                case 4 -> {
                    showProfile(curUser);
                    ///....
                }
                case 0 -> {
                    System.out.println("logged out");
                    curUser = null;
                    return;
                }
                default -> {
                    System.out.println("Incorrect selection, please try again");
                }
            }
        }
    }

    private static void groupMenu(Group group) {
        String curUserID = curUser.getID();
        String groupID = group.getID();
        String ownerID = group.getOwnerID();
        Set<String> adminsInGroup = groupService.getAdminsInGroup(groupID);

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

            int menu = MenuUtils.menu(MenuUtils.GROUP_OWNER_MENU);
            switch (menu) {
                case 1 -> {
                    writeNewMessage(group);
                }
                case 2 -> {
                    editMessage(group);
                }
                case 3 -> {
                    deleteMessage(group);
                }
                case 4 -> {
                    // add user
                    User foundUser = findUser();
                    if (foundUser != null) {
                        System.out.println(foundUser);
                        System.out.println("can you add this user to your group? \n 1.yes \n 2.no");
                        int n = ScanInput.getInt("choice: ");
                        if (n == 1) addUser(group, foundUser);
                        else if (n == 2) return;
                        else System.out.println("wrong choice");
                    }
                    else throw new RuntimeException("something happen");
                }
                case 5 -> {
                    // change user role
                }
                case 6 -> {
                    // show user
                    Set<String> usersInGroup = groupService.getUsersInGroup(group.getID());
                    usersInGroup.addAll(groupService.getAdminsInGroup(group.getID()));
                    showUsers(usersInGroup);
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
                    Set<String> usersInGroup = groupService.getUsersInGroup(group.getID());

                }
                case 10 -> {
                    // delete group
                }
                case 0 -> {
                    return;
                }
                default -> throw new IllegalStateException("Unexpected value: " + menu);
            }
        }
    }

    private static void showUsers(Set<String> usersID) {
        int i = 1;
        for (String userID : usersID) {
            User user = userService.get(userID);
            System.out.println(i + ". " + user.getUsername());
            i++;
        }

    }

    private static void addUser(Channel channel, User user) {

    }
    private static void addUser(Group group, User user) {
        boolean b = groupService.addUserInGroup(group.getID(), user.getID());
        if (b){
            System.out.println("user added successfully to channel");
        }
        else System.out.println("user not added");
    }

    private static String choiceMessage(List<Message> messageList) {
        int i = 1;
        for (Message message : messageList) {
            System.out.println(i + ". " + message.getContent());
            i++;
        }
        int ind = ScanInput.getInt("choice message: ") - 1;
        if (ind < 0 || ind >= messageList.size()) {
            System.out.println("wrong choice");
            return null;
        } else return messageList.get(ind).getID();
    }


    private static void deleteMessage(Group group) {
        String groupID = group.getID();
        String curUserID = curUser.getID();

        List<Message> groupMessages = messageService.getMessagesByChatID(groupID);

        if (group.getOwnerID().equals(curUserID) || groupService.getAdminsInGroup(groupID).contains(curUserID)) {
            // access delete any chat
            String messageID = choiceMessage(groupMessages);
            messageService.delete(messageID);

        } else if (groupService.getUsersInGroup(groupID).contains(curUserID)) {
            // access delete our messages
            String msg = choiceMessage(groupMessages);
            Message message = messageService.get(msg);

            if (message.getAuthorID().equals(curUserID)) {
                System.out.println("message deleted");
                messageService.delete(message.getID());
            } else {
                System.out.println("message is not deleted");
            }

        } else {
            throw new RuntimeException("something happened");
        }

    }

    private static void deleteMessage(Channel channel) {
        if (curUser.getID().equals(channel.getAuthorID())){
            String messageId = choiceMessage(messageService.getMessagesByChatID(channel.getID()));
            messageService.delete(messageId);
            System.out.println("message is deleted");
        }else {
            throw new RuntimeException("Only the channel creator can delete the message");
        }
    }

    private static void deleteMessage(Chat chat) {
        String messageID = choiceMessage(messageService.getMessagesByChatID(chat.getID()));
        if (messageID == null){
            throw  new RuntimeException("something happen");
        }else {
            messageService.delete(messageID);
            System.out.println("message deleted");
        }
    }

    private static void groupAdminMenu(Group group) {
        showMessages(messageService.getMessagesByChatID(group.getID()), group.getName());
        int menu = MenuUtils.menu(MenuUtils.GROUP_ADMIN_MENU);
    }

    private static void groupUserMenu(Group group) {
        showMessages(messageService.getMessagesByChatID(group.getID()), group.getName());
        int menu = MenuUtils.menu(MenuUtils.GROUP_USER_MENU);
    }

   /* private static void writeMessage(BaseModel chat, String chatName) {
        while (true) {
            showMessages(messageService.getMessagesByChatID(chat.getID()), chatName);
            int menu = MenuUtils.menu("""
                                        
                    ==========================================================
                    1.Write new message
                    2.Edit message
                    3.Add user
                    0.Exit
                    ==========================================================""");
            switch (menu) {
                case 1 -> {
                    // write new message
                    writeNewMessage(chat);
                }
                case 2 -> {
                    editMessage(chat);
                }
                case 3 -> {

                    // add user

                    User foundUser = findUser();

                    if (foundUser == null) return;
                    else if (chat instanceof Group group) {
                        System.out.println(foundUser);
                        System.out.println("can you add this user your group?");
                        int choice = MenuUtils.menu("""
                                1.yes
                                2.no
                                """);
                        if (choice == 1) {
                            System.out.println("user is added your group");
                            groupService.addUserInGroup(group.getID(), foundUser.getID());
                        } else if (choice == 2) {
                            System.out.println("user is not added your group");
                            return;
                        } else {
                            System.out.println("wrong choice");
                            return;
                        }
                    } else if (chat instanceof Channel channel) {
                        System.out.println(foundUser);
                        System.out.println("can you subscribe this user your channel?");
                        int choice = MenuUtils.menu("""
                                1.yes
                                2.no
                                """);
                        if (choice == 1) {
                            channelService.userSubscriptionToChannel(channel.getID(), foundUser.getID());
                            System.out.println("this user is subscribed your channel");
                        } else if (choice == 2) {
                            System.out.println("user is not subscribed your channel");
                            return;
                        } else {
                            System.out.println("wrong choice");
                            return;
                        }
                    }
                }
                case 0 -> {
                    return;
                }
            }
        }
    }*/

    private static void editMessage(BaseModel chat) {

        // edit message
        List<Message> messages = messageService.getMessagesByChatID(chat.getID());
        int i = 1;
        for (Message message : messages) {
            System.out.println(i + ". " + message.getContent());
            i++;
        }
        int choiceMessage = ScanInput.getInt("choice: ") - 1;
        Message message = messages.get(choiceMessage);
        if (message.getAuthorID().equals(curUser.getID())) {
            String txt = ScanInput.getStr("write new message: ");
            message.setContent(txt);
        } else {
            System.out.println("You can only edit your message");
            return;
        }
    }

    private static void writeMessageOnChat(BaseModel chat, String chatName) {
        while (true) {
            showMessages(messageService.getMessagesByChatID(chat.getID()), chatName);
            int menu = MenuUtils.menu("""
                                        
                    ==========================================================
                    1.Write new message
                    2.Edit message
                    3.Exit
                    ==========================================================""");
            switch (menu) {
                case 1 -> writeNewMessage(chat);
                case 2 -> editMessage(chat);
                case 3 -> {
                    return;
                }
            }
        }
    }

    private static void writeNewMessage(BaseModel chat) {
        String content = ScanInput.getStr("write message: ");
        Message newMessage = new Message(curUser.getID(), content, chat.getID(), LocalDateTime.now());
        messageService.create(newMessage);
    }

    private static void editProfile(User user) {
        System.out.println("Settings");

        showProfile(user);

        while (true) {
            String settings = """
                    ==========================================================
                    1.Change first name
                    2.Change last name
                    3.Change birth date
                    4.Change username
                    0.Exit
                    ==========================================================
                    """;
            int menu = MenuUtils.menu(settings);
            switch (menu) {
                case 1 -> {
                    String firstName = ScanInput.getStr("enter new first name: ");
                    user.setName(firstName);
                    System.out.println("first name is changed");
                }
                case 2 -> {
                    String lastName = ScanInput.getStr("enter new last name: ");
                    user.setName(lastName);
                    System.out.println("last name is changed");
                }
                case 3 -> {
                    String date = ScanInput.getStr("enter your new birth date: ");
                    LocalDate localDate = userService.makeBirthday(date);
                    user.setBirthDay(localDate);
                    System.out.println("your birth date was changed success");
                }
                case 4 -> {
                    String username = ScanInput.getStr("enter new username: ");
                    if (userService.isValidUsername(username)) {
                        user.setUsername(username);
                        System.out.println("username success changed");
                    } else {
                        System.out.println("entered invalid username");
                    }

                    System.out.println("name is changed");
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("wrong choice");
            }
        }
    }


    private static void showProfile(User user) {
        String name = user.getName();
        String lastName = user.getLastName();
        LocalDate birthDay = user.getBirthDay();
        String username = user.getUsername();
        String password = user.getPassword();
        UserRole role = user.getRole();
        StatusType status = user.getStatus();

        System.out.println("==========================================================");
        System.out.println("Account Status: " + status);
        System.out.println("First name: " + name);
        System.out.println("Last name: " + lastName);
        System.out.println("Birth date: " + birthDay);
        System.out.println("Username: " + username);
        System.out.println("User role: " + role);
        System.out.println("==========================================================");
    }


    private static Channel createChannel() {
        String channelName, description;

        channelName = ScanInput.getStr("enter channel name: ");
        description = ScanInput.getStr("enter channel description: ");

        Channel channel = new Channel(channelName, curUser.getID(), description);

        boolean channelIsCreated = channelService.create(channel);

        if (channelIsCreated) {
            System.out.println("group created successfully");
        } else {
            System.out.println("group was not created. something went wrong");
        }
        return channel;
    }


    private static Group createGroup() {
        String groupName, description;

        groupName = ScanInput.getStr("enter group name: ");
        description = ScanInput.getStr("enter group description: ");


        Group group = new Group(groupName, curUser.getID(), description);

        boolean groupIsCreated = groupService.create(group);

        if (groupIsCreated) {
            System.out.println("group created successfully");
        } else {
            System.out.println("group was not created. something went wrong");
        }
        return group;
    }


    private static List<Channel> showChannels() {
        List<Channel> channels = channelService.getChannels(curUser.getID());
        int i = 1;
        for (Channel channel : channels) {

            System.out.println
                    (i + ". " + channel.getName());
            i++;
        }
        return channels;
    }


    private static Group getOrCreateGroup() {
        List<Group> groups = groupService.getGroups(curUser.getID());
        if (groups.isEmpty()) {
            System.out.println("you don't have any groups yet");
        }
        int i = 1;
        for (Group group : groups) {
            System.out.println
                    (i + ". " + group.getName());
            i++;
        }
        System.out.println(i + ". " + "Create new group");
        System.out.println("0. " + "Exit");

        int choice = ScanInput.getInt("choice: ");
        if (choice == i) {
            return createGroup();
        }
        if (choice == 0) {
            return null;
        } else {
            choice--;
            return groups.get(choice);
        }
    }


    private static Chat showOrCreateChat() {
        List<Chat> allUsersChat = chatService.getAllUsersChatsByUserID(curUser.getID());
        if (allUsersChat.isEmpty()) {
            int menu = MenuUtils.menu("""
                    ==========================================================
                    you don't have any chats yet
                    1.create chat
                    2.exit
                    ==========================================================
                    """);
            if (menu == 1) return createChat();
            else return null;
        }

        System.out.println("==========================================================");
        int i = 1;

        for (Chat chat : allUsersChat) {

            String chatName = chatService.determineChatName(chat.getID(), curUser.getID());
            System.out.println(i + ". " + chatName);

            i++;
        }
        System.out.println("0.Exit");
        System.out.println("==========================================================");
        int choice = ScanInput.getInt("choice: ") - 1;
        if (choice < -1 || choice >= allUsersChat.size()) {
            System.out.println("wrong choice");
            return null;
        } else if (choice == -1) {
            return null;
        } else {
            return allUsersChat.get(choice);
        }
    }


    private static Chat createChat() {
        User findUser = findUser();
        if (findUser != null) {

            Chat newChat = new Chat(curUser.getID(), findUser.getID());
            boolean chatIsCreated = chatService.create(newChat);

            if (chatIsCreated) {
                return newChat;
            } else {
                System.out.println("chat is not created");
                return null;
            }
        } else {
            System.out.println("user  not found");
            return null;
        }

    }


    private static User findUser() {
        System.out.println("find user");
        String username = ScanInput.getStr("enter username: ");
        User userByUsername = userService.getUserByUsername(username);
        if (userByUsername == null) {
            System.out.println("user is not found");
        } else {
            return userByUsername;
        }
        return null;
    }


    private static void showMessages(List<Message> messages, String chatName) {
        System.out.println("\n==========================================================");
        int spacesCount = (60 - chatName.length()) / 2;
        String spaces = " ".repeat(spacesCount);
        System.out.println(spaces + chatName + spaces);

        for (Message message : messages) {
            System.out.println(userService.get(message.getAuthorID()).getName() + ": " + message.getContent());
        }
        System.out.println("==========================================================");
    }
}
