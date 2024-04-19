package uz.pdp.frontend.tests;

import uz.pdp.backend.models.channel.Channel;
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

import java.util.Date;
import java.util.List;

public class testChannel {

    private static final MessageService messageService = MessageServiceImp.getInstance();
    private static final GroupService groupService = GroupServiceImp.getInstance();
    private static final UserService userService = UserServiceImp.getInstance();
    private static final ChannelService channelService = ChannelServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();

    public static void main(String[] args) {

/*

        User bobur = new User("Bob", "101", "Bobur",
                UserRole.USER, StatusType.ACTIVE);
        String boburID = bobur.getID();

        Channel boburChannel = new Channel
                ("g40 home works", boburID, "daily home works for g40");
        String boburChannelID = boburChannel.getID();


        boolean channelIsCreated = channelService.create(boburChannel);
        System.out.println("channelIsCreated = " + channelIsCreated);

        Message firstMessage = new Message
                (boburID, "this is first content from Bobur channel",
                        boburChannelID, new Date());

        Message secondMessage = new Message
                (boburID, "second message",
                        boburChannelID, new Date());

        boolean message1IsCreated = messageService.create(firstMessage);
        boolean message2IsCreated = messageService.create(secondMessage);

        System.out.println("message1IsCreated = " + message1IsCreated);
        System.out.println("message2IsCreated = " + message2IsCreated);



        System.out.println("\ncontents on " + boburChannel.getName() + ": \n");

        List<Message> messages = messageService.getMessages(boburChannelID);
        for (Message message : messages) {
            System.out.println(message.getContent());
        }
*/

        System.out.println("test test");
        System.out.println("eee");

    }

}