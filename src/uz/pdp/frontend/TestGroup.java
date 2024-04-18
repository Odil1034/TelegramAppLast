package uz.pdp.frontend;

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

import java.util.Date;
import java.util.List;

public class TestGroup {
    private static final MessageService messageService = MessageServiceImp.getInstance();
    private static final GroupService groupService = GroupServiceImp.getInstance();
    private static final UserService userService = UserServiceImp.getInstance();
    private static final ChannelService channelService = ChannelServiceImp.getInstance();
    private static final ChatService chatService = ChatServiceImp.getInstance();

    public static void main(String[] args) {

        User odiljon = userService.getUserByUsername("Odiljon");
        String odiljonID = odiljon.getID();

        Group group = new Group
                ("Umar, Xurshid and Odiljon", odiljonID, "uzki grupamishde");
        String groupID = group.getID();

        User umar = userService.getUserByUsername("Umar");
        String umarID = umar.getID();

        User xurshid = userService.getUserByUsername("Xurshid");
        String xurshidID = xurshid.getID();

        boolean groupIsCreated = groupService.create(group);

        boolean userIsAdded = groupService.addUserInGroup(groupID, umarID);

        boolean userIsAdded1 = groupService.addUserInGroup(groupID, xurshidID);

        System.out.println("groupIsCreated = " + groupIsCreated);
        System.out.println("userIsAdded = " + userIsAdded);
        System.out.println("userIsAdded1 = " + userIsAdded1);

        Message message1 = new Message(odiljonID, "salom", groupID, new Date());
        Message message2 = new Message(umarID, "qalesan", groupID, new Date());
        Message message3 = new Message(xurshidID, "nima gap?", groupID, new Date());

        boolean message1IsCreated = messageService.create(message1);
        boolean message2IsCreated = messageService.create(message2);
        boolean message3IsCreated = messageService.create(message3);

        System.out.println("message1IsCreated = " + message1IsCreated);
        System.out.println("message2IsCreated = " + message2IsCreated);
        System.out.println("message3IsCreated = " + message3IsCreated);

        System.out.println("\n" + group.getName() + " group messages:\n");

        List<Message> groupMessages = messageService.getMessages(groupID);

        for (Message message : groupMessages) {
            System.out.println(userService.get(message.getAuthorID()).getNickname() + ": " + message.getContent());
        }

        System.out.println("\ntest is successfully");
    }
}
