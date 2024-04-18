package uz.pdp.frontend.tests;

import uz.pdp.backend.models.chat.Chat;
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

import java.util.Date;
import java.util.List;

public class TestChat {
    private static final MessageService messageService = MessageServiceImp.getInstance();
    private static final GroupService groupService = GroupServiceImp.getInstance();
    private static final UserService userService = UserServiceImp.getInstance();
    private static final ChannelService channelService = ChannelServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();

    public static void main(String[] args) {

        User umar = userService.getUserByUsername("Umar");
        String umarID = umar.getID();
        User xurshid = userService.getUserByUsername("Xurshid");
        String xurshidID = xurshid.getID();
        User odiljon = userService.getUserByUsername("Odiljon");
        String odiljonID = odiljon.getID();

        Chat chat1 = new Chat(umarID, odiljonID);
        String chat1ID = chat1.getID();

        Message message1 = new Message(umarID, "test txt", chat1ID, new Date());
        Message message2 = new Message(odiljonID, "test txt from Odiljon", chat1ID, new Date());

        messageService.create(message1);
        messageService.create(message2);

        List<Message> chat1Messages = messageService.getMessages(chat1ID);
        for (Message message : chat1Messages) {
            System.out.println(userService.get(message.getAuthorID()).getNickname()+": " + message.getContent());

        }

    }
}
