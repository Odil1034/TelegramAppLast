package uz.pdp.frontend.view;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.models.message.Message;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.FollowerService.FollowerService;
import uz.pdp.backend.service.FollowerService.FollowerServiceImp;
import uz.pdp.backend.service.channelService.ChannelService;
import uz.pdp.backend.service.channelService.ChannelServiceImp;
import uz.pdp.backend.service.chatService.ChatService;
import uz.pdp.backend.service.chatService.ChatServiceImp;
import uz.pdp.backend.service.groupFollowerService.GroupFollowerService;
import uz.pdp.backend.service.groupFollowerService.GroupFollowerServiceImp;
import uz.pdp.backend.service.groupService.GroupService;
import uz.pdp.backend.service.groupService.GroupServiceImp;
import uz.pdp.backend.service.messageService.MessageService;
import uz.pdp.backend.service.messageService.MessageServiceImp;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;
import uz.pdp.backend.types.receiverType.ReceiverType;

import static uz.pdp.frontend.utills.MenuUtils.*;
import static uz.pdp.frontend.utills.MessageUtils.*;
import static uz.pdp.frontend.utills.ScanInput.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class CommonView {
    private static final UserService userService = UserServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();
    private static final ChannelService channelService = ChannelServiceImp.getInstance();
    private static final GroupService groupService = GroupServiceImp.getInstance();
    private static final MessageService messageService = MessageServiceImp.getInstance();
    private static final GroupFollowerService groupFollowerService = GroupFollowerServiceImp.getInstance();
    private static final FollowerService followerService = FollowerServiceImp.getInstance();
    private static User curUser;


    protected static void showMessages(List<Message> messageList) {

        if (messageList != null) {
            if (messageList.isEmpty()) {
                System.out.println("No message have been posted yet");
            }else {
                for (Message message : messageList) {
                    String authorID = message.getAuthorID();
                    User user = userService.get(authorID);
                    LocalDateTime dateTime = message.getDateTime();
                    String date = dateTime.format(DateTimeFormatter.ofPattern("dd /MMMMM/  yyyy"));
                    System.out.println(date);
                    String time = dateTime.format(DateTimeFormatter.ofPattern("hh : mm"));
                    System.out.println(
                            user.getUsername() + ": " +
                            message.getContent() + time);
                }

            }
        } else {
            System.out.println("Anythings wrong ❌❌❌");
        }
    }

    protected static Channel createNewChannel(User curUser) {

        String newChannelName = getStr("Enter new channel name: ");
        String description = getStr("Enter description: ");

        Channel channel = new Channel(newChannelName, curUser.getID(), description);

        if (channelService.create(channel)){
            return channel;
        }else {
            System.out.println("Channel not created ❌❌❌");
            return null;
        }
    }
}
