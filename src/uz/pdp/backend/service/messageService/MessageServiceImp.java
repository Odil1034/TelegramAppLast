package uz.pdp.backend.service.messageService;

import uz.pdp.backend.models.message.Message;

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
    public List<Message> getMessagesByChatID(String chatID) {
        List<Message> messagesInChat = new ArrayList<>();
        for (Message message : messages) {
            if (message.getChatID().equals(chatID)){
                messagesInChat.add(message);
            }
        }
        return messagesInChat;
    }

}
