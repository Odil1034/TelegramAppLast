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
import uz.pdp.backend.service.userGroupService.UserGroupService;
import uz.pdp.backend.service.userGroupService.UserGroupServiceImp;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;
import uz.pdp.backend.types.group.groupRole.GroupRole;
import uz.pdp.backend.types.user.StatusType;
import uz.pdp.backend.types.user.UserRole;
import uz.pdp.frontend.utills.MenuUtils;
import uz.pdp.frontend.utills.ScanInput;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface CommonMenuMethods {
    UserService userService = UserServiceImp.getInstance();
    ChatService chatService = ChatServiceImp.getInstance();
    ChannelService channelService = ChannelServiceImp.getInstance();
    UserGroupService userGroupService = UserGroupServiceImp.getInstance();
    GroupService groupService = GroupServiceImp.getInstance();
    MessageService messageService = MessageServiceImp.getInstance();

    static User findUser() {
//        showUsers(userService.getList());
        /** Search user menu
         * 1. Name
         * 2. Username
         * 3. Role
         * 4. Status
         * 0. Back to Menu
         */

        List<User> list;
        while (true) {
            int choice = MenuUtils.menu(MenuUtils.SEARCH_USER_MENU);
            switch (choice) {
                case 1 -> {
                    String name = ScanInput.getStr("Enter name: ");
                    list = userService.getListMatchName(name);
                    return checkList(list);
                }
                case 2 -> {
                    String username = ScanInput.getStr("Enter username: ");
                    User user = userService.getUserByUsername(username);
                    if (user != null) {
                        return user;
                    } else {
                        System.out.println("User is not found");
                        return null;
                    }
                }
                case 3 -> {
                    UserRole.showType();
                    int index = ScanInput.getInt("Choose: ") - 1;
                    UserRole userRole = UserRole.getType(index);
                    list = userService.getListMatchRole(userRole);
                    return checkList(list);
                }
                case 4 -> {
                    StatusType.showType();
                    int index = ScanInput.getInt("Choose: ") - 1;
                    StatusType statusType = StatusType.getType(index);
                    list = userService.getListMatchStatusType(statusType);
                    return checkList(list);
                }
                case 0 -> {
                    return null;
                }
                default -> throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    private static User checkList(List<User> list) {
        if (list == null) {
            System.out.println("User is not found");
            return null;
        } else {
            showUsers(list);
            int ind = ScanInput.getInt("Choice: ") - 1;
            return list.get(ind);
        }
    }


    static boolean deleteChat(String chatID) {
        return chatService.delete(chatID);
    }

    static void showMessages(List<Message> messages, String chatName) {
        System.out.println("\n==========================================================");
        int spacesCount = (60 - chatName.length()) / 2;
        String spaces = " ".repeat(spacesCount);
        System.out.println(spaces + chatName + spaces);

        for (Message message : messages) {
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy   hh : mm");
            System.out.println(userService.get(message.getAuthorID()).getName() +
                               ": " + message.getContent() +
                               "\t\t" + message.getDateTime().format(formatDate));
        }
        System.out.println("==========================================================");
    }

    static void showUsers(List<User> usersList) {

        if (usersList == null) {
            System.out.println("User is not found");
        } else {

            System.out.printf("""
                    ==========================================================
                                         USERS LIST  (%d users)
                    ==========================================================
                    """, usersList.size());
            System.out.println("№\t USERNAME \t NAME \t ROLE \t STATUS \t BIRTHDAY \t      AGE\n");
            for (int i = 0; i < usersList.size(); i++) {
                User user = usersList.get(i);
                int userAge = userService.getUserAge(user.getBirthDay());
                System.out.println(i + 1 + "." +
                                   " \t " + user.getUsername() +
                                   " \t " + user.getName() +
                                   " \t   " + user.getRole() +
                                   " \t " + user.getStatus() + " " +
                                   " \t " + user.getBirthDay() +
                                   " \t " + userAge);
            }
            System.out.println("=".repeat(50));
        }

    }

    static void addUser(Group group, User user, GroupRole groupRole) {
        boolean b = userGroupService.addUserToGroup(group.getID(), user.getID(), groupRole);
        if (b) {
            System.out.println("user added successfully to channel");
        } else System.out.println("user not added");
    }

    static boolean addUser(Channel channel, User user) {
        return channelService.userSubscriptionToChannel(channel.getID(), user.getID());
    }


    static String choiceMessage(List<Message> messageList) {
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


    static void deleteMessage(Group group, User curUser) {
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


    static void deleteMessage(Channel channel, User curUser) {
        if (curUser.getID().equals(channel.getAuthorID())) {
            String messageId = choiceMessage(messageService.getMessagesByChatID(channel.getID()));
            messageService.delete(messageId);
            System.out.println("message is deleted");
        } else {
            System.out.println("Only the channel creator can delete the message");
        }
    }


    static void deleteMessage(Chat chat) {
        String messageID = choiceMessage(messageService.getMessagesByChatID(chat.getID()));
        if (messageID == null) {
            throw new RuntimeException("something happen");
        } else {
            messageService.delete(messageID);
            System.out.println("message deleted");
        }
    }


    static Chat createChat(User curUser) {
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

    static Group getOrCreateGroup(User curUser) {
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
            return createGroup(curUser);
        }
        if (choice == 0) {
            return null;
        } else {
            choice--;
            return groups.get(choice);
        }
    }

    static Group createGroup(User curUser) {
        String groupName, description;

        groupName = ScanInput.getStr("enter group name: ");
        description = ScanInput.getStr("enter group description: ");


        Group group = new Group(groupName, curUser.getID(), description);

        boolean groupIsCreated = groupService.create(group);

        User user = findUser();
        assert user != null;
        addUser(group, user);

        if (groupIsCreated) {
            System.out.println("group created successfully");
        } else {
            System.out.println("group was not created. something went wrong");
        }
        return group;
    }


    static Chat getOrCreateChat(User curUser) {
        List<Chat> allUsersChat = chatService.getAllUsersChatsByUserID(curUser.getID());
        if (allUsersChat.isEmpty()) {
            int menu = MenuUtils.menu("""
                    ==========================================================
                    you don't have any chats yet
                    1.create chat
                    2.exit
                    ==========================================================
                    """);
            if (menu == 1) return createChat(curUser);
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


    static Channel getOrCreateChannel(User currentUser) {
        List<Channel> channels = channelService.getChannels(currentUser.getID());

        if (channels.isEmpty()) {
            System.out.println("==========================================================");
            System.out.println("you don't have any channels yet");

            while (true) {
                System.out.println("can you create new channel?");
                int menu = MenuUtils.menu("""
                        1. Yes
                        2. No
                        ==========================================================""");

                if (menu == 1) {

                    String channelName, description;

                    channelName = ScanInput.getStr("enter channel name: ");
                    description = ScanInput.getStr("enter channel description: ");

                    Channel channel = new Channel(channelName, currentUser.getID(), description);

                    boolean channelIsCreated = channelService.create(channel);

                    if (channelIsCreated) {
                        System.out.println("group created successfully");
                    } else {
                        System.out.println("group was not created. something went wrong");
                    }
                    return channel;

                } else if (menu == 2) {
                    return null;
                } else {
                    System.out.println("wrong choice");
                }
            }

        } else {
            while (true) {
                showChannels(currentUser);
                int choice = ScanInput.getInt("choice: ") - 1;
                if (choice < 0 || choice >= channels.size()) {
                    System.out.println("Incorrect selection, please try again");
                } else {
                    return channels.get(choice);
                }
            }
        }
    }


    static void showChannels(User curUser) {
        List<Channel> channels = channelService.getChannels(curUser.getID());
        System.out.println("==========================================================");
        int i = 1;
        for (Channel channel : channels) {
            System.out.println(i + ". " + channel.getName());
            i++;
        }
        System.out.println("==========================================================");

    }


    static void editMessage(BaseModel chat, User curUser) {

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
        }
    }


    static void writeNewMessage(BaseModel chat, User curUser) {
        String content = ScanInput.getStr("write message: ");
        Message newMessage = new Message(curUser.getID(), content, chat.getID(), LocalDateTime.now());
        messageService.create(newMessage);
    }


    static void showProfile(User user) {
        String name = user.getName();
        String lastName = user.getLastName();
        LocalDate birthDay = user.getBirthDay();
        String username = user.getUsername();
        // String password = user.getPassword();
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


    static void editProfile(User curUser) {
        System.out.println("Settings");

        showProfile(curUser);

        while (true) {
            int menu = MenuUtils.menu(MenuUtils.USER_PROFILE_SETTINGS);
            switch (menu) {
                case 1 -> {
                    String firstName = ScanInput.getStr("enter new first name: ");
                    curUser.setName(firstName);
                    System.out.println("first name is changed");
                }
                case 2 -> {
                    String lastName = ScanInput.getStr("enter new last name: ");
                    curUser.setName(lastName);
                    System.out.println("last name is changed");
                }
                case 3 -> {
                    String date = ScanInput.getStr("enter your new birth date: ");
                    LocalDate localDate = userService.makeBirthday(date);
                    curUser.setBirthDay(localDate);
                    System.out.println("your birth date was changed success");
                }
                case 4 -> {
                    String username = ScanInput.getStr("enter new username: ");
                    if (userService.isValidUsername(username)) {
                        curUser.setUsername(username);
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


}
