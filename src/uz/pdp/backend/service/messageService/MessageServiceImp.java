package uz.pdp.backend.service.messageService;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.message.Message;
import uz.pdp.backend.service.groupService.GroupService;
import uz.pdp.backend.service.groupService.GroupServiceImp;
import uz.pdp.backend.types.receiverType.ReceiverType;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImp implements MessageService{

    GroupService groupService = GroupServiceImp.getInstance();
    private static MessageService service;
    private final List<Message> messages;

    private MessageServiceImp() {
        messages = new ArrayList<>();
    }

    public static MessageService getInstance(){
        if (service == null){
            service = new MessageServiceImp();
        }
        return service;
    }

    @Override
    public boolean create(Message message) {
        messages.add(message);
        return true;
    }

    @Override
    public Message get(String ID) {
        for (Message message : messages) {
            if (message.getID().equals(ID)) {
                return message;
            }
        }
        return null;
    }

    @Override
    public List<Message> getList() {
        return messages;
    }

    @Override
    public void update(Message newMessage) {
        for (Message message : messages) {
            if (message.getID().equals(newMessage.getID())) {
                message.setContent(newMessage.getContent());
                message.setDateTime(newMessage.getDateTime());
            }
        }
    }

    @Override
    public boolean delete(String messageID) {
        for (Message message : messages) {
            if (message.getID().equals(messageID)){
                messages.remove(message);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Message> getMessagesInGroup(String groupOrChatID) {
        List<Message> messagesInChat = new ArrayList<>();
        for (Message message : messages) {
            if (message.getChatOrGroupID().equals(groupOrChatID)){
                messagesInChat.add(message);
            }
        }
        return messagesInChat;
    }

    @Override
    public boolean addMessageInGroup(String messageID, String groupID) {

        for (Message message : messages) {
            if (message.getID().equals(messageID)) {
                message.setReceiverType(ReceiverType.GROUP);
                message.setChatOrGroupID(groupID);
                return true;
            }
        }
        return false;
    }
}
