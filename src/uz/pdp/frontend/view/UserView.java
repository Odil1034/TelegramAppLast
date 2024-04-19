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
import uz.pdp.frontend.utills.InputStream;
import uz.pdp.frontend.utills.MenuUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
                    Group group = showOrCreateGroup();
                    showMessages(messageService.getMessages(group.getID()), group.getName());
                    writeMessage(group, group.getName());
                }
                case 2 -> {
                    Chat chat = showOrCreateChat();
                    if (chat != null){
                        String name = chatService.determineChatName(chat.getID(), curUser.getID());
                        List<Message> messages = messageService.getMessages(chat.getID());
                        writeMessage(chat, name);
                    }else {
                        System.out.println("something wrong");
                        return;
                    }
                }
                case 3 -> {
                    List<Channel> channels = showChannels();
                }
                case 4 -> {
                    showProfile();
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


    private static void writeMessage(BaseModel chat, String chatName) {
        while (true) {
            showMessages(messageService.getMessages(chat.getID()), chatName);
            int menu = MenuUtils.menu("""
                    
                    ==========================================================
                    1.Write new message
                    2.add user in group
                    2.Edit message
                    3.Exit
                    ==========================================================""");
            switch (menu) {
                case 1 -> {
                    messageService.create(new Message(curUser.getID(),
                            InputStream.getStr("Write new message: "),
                            chat.getID(), new Date()));
                }
                case 2 -> {
                    List<Message> messages = messageService.getMessages(chat.getID());
                    int i = 1;
                    for (Message message : messages) {
                        System.out.println(i + ". " + message.getContent());
                        i++;
                    }
                    int choiceMessage = InputStream.getInt("choice: ") - 1;
                    Message message = messages.get(choiceMessage);
                    String txt = InputStream.getStr("write new message: ");
                    message.setContent(txt);
                }
                case 3 -> {
                    return;
                }
            }
        }
    }

    private static void editProfile() {
        System.out.println("Settings");

        showProfile();

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
                    String firstName = InputStream.getStr("enter new first name: ");
                    curUser.setName(firstName);
                    System.out.println("first name is changed");
                }
                case 2 -> {
                    String lastName = InputStream.getStr("enter new last name: ");
                    curUser.setName(lastName);
                    System.out.println("last name is changed");
                }
                case 3 -> {
                    String date = InputStream.getStr("enter your new birth date: ");
                    LocalDate localDate = userService.makeBirthday(date);
                    curUser.setBirthDay(localDate);
                    System.out.println("your birth date was changed success");
                }
                case 4 -> {
                    String username = InputStream.getStr("enter new username: ");
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

    private static void showProfile() {
        String name = curUser.getName();
        String lastName = curUser.getLastName();
        LocalDate birthDay = curUser.getBirthDay();
        String username = curUser.getUsername();
        String password = curUser.getPassword();
        UserRole role = curUser.getRole();
        StatusType status = curUser.getStatus();

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

        channelName = InputStream.getStr("enter channel name: ");
        description = InputStream.getStr("enter channel description: ");

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

        groupName = InputStream.getStr("enter group name: ");
        description = InputStream.getStr("enter group description: ");


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

    private static Group showOrCreateGroup() {
        List<Group> groups = groupService.getGroups(curUser.getID());
        if (groups.isEmpty()){
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

        int choice = InputStream.getInt("choice: ");
        if (choice == i) {
            return createGroup();
        } if (choice == 0){
            return null;
        }else {
            choice--;
            return groups.get(choice);
        }


    }

    private static Chat showOrCreateChat() {
        List<Chat> allUsersChat = chatService.getAllUsersChat(curUser.getID());
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
        int choice = InputStream.getInt("choice: ") - 1;
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
        System.out.println("find user");
        String username = InputStream.getStr("enter username: ");
        User userByUsername = userService.getUserByUsername(username);
        if (userByUsername == null) {
            System.out.println("user is not found");
        } else {
            Chat newChat = new Chat(curUser.getID(), userByUsername.getID());
            boolean chatIsCreated = chatService.create(newChat);

            if (chatIsCreated) {
                return newChat;
            }

        }
        return null;
    }


    private static void showMessages(List<Message> messages, String chatName) {
        System.out.println("\n==========================================================");
        System.out.println(chatName);
        for (Message message : messages) {
            System.out.println(userService.get(message.getAuthorID()).getName() + ": " + message.getContent());
        }
        System.out.println("==========================================================");
    }
}
