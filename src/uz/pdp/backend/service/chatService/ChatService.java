package uz.pdp.backend.service.chatService;

import uz.pdp.backend.models.chat.Chat;
import uz.pdp.backend.service.BaseService;

import java.util.List;

public interface ChatService extends BaseService<Chat> {

    List<Chat> getUserChats(String userID);
    Chat determineChat(String chatID, String user1ID);
}
