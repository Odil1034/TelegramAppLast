package uz.pdp.backend.service.chatService;

import uz.pdp.backend.models.chat.Chat;

import java.util.ArrayList;
import java.util.List;

public class ChatServiceImp implements ChatService {
    private static ChatService chatService;
    private final List<Chat> chatList;


    private ChatServiceImp() {
        chatList = new ArrayList<>();
    }

    public static ChatService getInstance() {
        if (chatService == null) {
            chatService = new ChatServiceImp();
        }
        return chatService;
    }


    @Override
    public boolean create(Chat chat) {
        chatList.add(chat);
        return true;
    }

    @Override
    public Chat get(String ID) {
        for (Chat chat : chatList) {
            if (chat.getID().equals(ID)) {
                return chat;
            }
        }
        return null;
    }

    @Override
    public List<Chat> getList() {
        return chatList;
    }

    @Override
    public void update(Chat newM) {
        //???
    }

    @Override
    public boolean delete(String ID) {
        return chatList.removeIf(chat -> chat.getID().equals(ID));
    }

    @Override
    public List<Chat> getAllUsersChatsByUserID(String userID) {
        List<Chat> userChats = new ArrayList<>();
        for (Chat chat : chatList) {
            if (chat.getFirstUserID().equals(userID) || chat.getSecondUserID().equals(userID)){
                userChats.add(chat);
            }
        }
        return userChats;
    }
}
