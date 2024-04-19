package uz.pdp.frontend.tests;

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


import java.util.*;

public class Main {
    private static final MessageService messageService = MessageServiceImp.getInstance();
    private static final GroupService groupService = GroupServiceImp.getInstance();
    private static final UserService userService = UserServiceImp.getInstance();
    private static final ChannelService channelService = ChannelServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();


    public static void main(String[] args){

    /*
        System.out.println("sout test");

        User umar = userService.getUserByUsername("Umar");
        String umarID = umar.getID();

        User odiljon = userService.getUserByUsername("Odiljon");
        String odiljonID = odiljon.getID();

        User xurshid = userService.getUserByUsername("Xurshid");
        String xurshidID = xurshid.getID();

        Chat umar_and_odiljon = new Chat(umarID, odiljonID);

        String umarAndOdiljonID = umar_and_odiljon.getID();

        Message sms = new Message(umarID, "this is new message from Umar to Odiljon",
                umarAndOdiljonID, new Date());

        Group umar_xurshid_odiljon = new Group
                ("Umar, Xurshid and Odiljon", odiljonID, "");

        String groupID = umar_xurshid_odiljon.getID();

        groupService.create(umar_xurshid_odiljon);

        groupService.addUserInGroup(groupID, umarID);
        groupService.addUserInGroup(groupID, xurshidID);



        System.out.println("\nfirst group: \n");

        List<String> usersInGroup = groupService.getUsersInGroup(groupID);
        List<User> users = new ArrayList<>();
        for (String userId : usersInGroup) {
            users.add(userService.get(userId));
        }

        for (User user : users) {
            System.out.println(user.getNickname());
        }



        boolean chatIsCreated = chatService.create(umar_and_odiljon);
        boolean messageIsCreated = messageService.create(sms);

        System.out.println("chatIsCreated = " + chatIsCreated);
        System.out.println("messageIsCreated = " + messageIsCreated);


        Group group2 = new Group("myGroup", xurshidID, "hello its my first group");
        String group2ID = group2.getID();
        groupService.create(group2);
        groupService.addUserInGroup(group2ID, odiljonID);

        System.out.println("\nsecond group: \n");
        List<User> usersInGroup2 = new ArrayList<>();

        for (String users22 : groupService.getUsersInGroup(group2ID)) {
            usersInGroup2.add(userService.get(users22));
        }
        for (User user : usersInGroup2) {
            System.out.println("user.getNickname() = " + user.getNickname());
        }


        User bobur = new User("Bubur002", "002",
                "Bob", UserRole.USER, StatusType.ACTIVE);
        String boburID = bobur.getID();
        Channel channel = new Channel("G40 home works", boburID, "home works for g40");


        boolean channelIsCreated = channelService.create(channel);
        String channelID = channel.getID();
        String content = "content";
        Message message = new Message(boburID, content, channelID, new Date());
        String messageID = message.getID();
        boolean messageIsCreated2 = messageService.create(message);

        boolean contentIsCreated = channelService.writeContent(channelID, messageID);
        System.out.println("contentIsCreated = " + contentIsCreated);
        System.out.println("messageIsCreated2 = " + messageIsCreated2);

*/

    }
}
