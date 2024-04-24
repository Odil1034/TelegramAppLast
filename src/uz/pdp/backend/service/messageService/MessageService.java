package uz.pdp.backend.service.messageService;

import uz.pdp.backend.models.message.Message;
import uz.pdp.backend.service.BaseService;
import uz.pdp.backend.types.receiverType.ReceiverType;

import java.util.List;

public interface MessageService extends BaseService<Message>{

    List<Message> getGroupMessages(String groupID, ReceiverType type);
    List<Message> getChannelMessages(String channelID, ReceiverType type);
    List<Message> getChatMessages(String chatID, ReceiverType type);
    List<Message> getMyMessages(List<Message> receiverMessages, String userID);

    boolean addMessage(Message newMessage, String receiverID, ReceiverType type);
}
