package uz.pdp.backend.service.messageService;

import uz.pdp.backend.models.message.Message;
import uz.pdp.backend.service.groupService.GroupService;
import uz.pdp.backend.service.groupService.GroupServiceImp;
import uz.pdp.backend.types.receiverType.ReceiverType;

import java.util.ArrayList;
import java.util.List;

public class MessageServiceImp implements MessageService{

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
    public boolean addMessage(Message newMessage, String receiverID, ReceiverType type) {

        for (Message message : messages) {
            if (message.getID().equals(newMessage.getID())) {
                message.setReceiverType(type);
                message.setReceiverID(receiverID);
                return true;
            }
        }

        return false;
    }

    public List<Message> getGroupMessages(String groupID, ReceiverType type){
        List<Message> groupMessages = new ArrayList<>();

        for (Message message : messages) {
            if (message.getReceiverID().equals(groupID) &&
                message.getReceiverType().equals(ReceiverType.GROUP)) {
                groupMessages.add(message);
            }
        }

        return groupMessages;
    }

    public List<Message> getChannelMessages(String channelID, ReceiverType type){
        List<Message> groupMessages = new ArrayList<>();

        for (Message message : messages) {
            if (message.getReceiverID().equals(channelID) &&
                message.getReceiverType().equals(ReceiverType.CHANNEL)) {
                groupMessages.add(message);
            }
        }

        return groupMessages;
    }

    public List<Message> getChatMessages(String chatID, ReceiverType type){
        List<Message> groupMessages = new ArrayList<>();

        for (Message message : messages) {
            if (message.getReceiverID().equals(chatID) &&
                message.getReceiverType().equals(ReceiverType.GROUP)) {
                groupMessages.add(message);
            }
        }

        return groupMessages;
    }

    @Override
    public List<Message> getMyMessages(List<Message> receiverMessages, String userID) {

        List<Message> myMessages = new ArrayList<>();

        for (Message message : receiverMessages) {
            if (message.getAuthorID().equals(userID)) {
                myMessages.add(message);
            }
        }
        return myMessages;
    }
}
