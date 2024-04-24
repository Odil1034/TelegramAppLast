package uz.pdp.backend.service.chatService;

import uz.pdp.backend.models.chat.Chat;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;

import java.util.ArrayList;
import java.util.List;

public class ChatServiceImp implements ChatService {
    private final List<Chat> chatList;

    private ChatServiceImp() {
        chatList = new ArrayList<>();
    }

    private static ChatService chatService;

    public static ChatService getInstance() {
        if (chatService == null) {
            chatService = new ChatServiceImp();
        }
        return chatService;
    }

    @Override
    public boolean create(Chat newChat) {
        for (Chat chat : chatList) {
            if (chat.getFirstUserID().equals(newChat.getFirstUserID()) &&
                chat.getSecondUserID().equals(newChat.getSecondUserID()) ||
                chat.getFirstUserID().equals(newChat.getSecondUserID()) &&
                chat.getSecondUserID().equals(newChat.getFirstUserID()) ||
                chat.getSecondUserID().equals(newChat.getFirstUserID()) &&
                chat.getFirstUserID().equals(newChat.getSecondUserID())) {
                return false;
            }
        }
        chatList.add(newChat);
        return true;
    }

    @Override
    public Chat get(String chatID) {
        for (Chat chat : chatList) {
            if (chat.getID().equals(chatID)) {
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
    public void update(Chat newChat) {
        for (Chat chat : chatList) {
            if (chat.getID().equals(newChat.getID())) {
                chat.setFirstUserID(newChat.getFirstUserID());
                chat.setSecondUserID(newChat.getSecondUserID());
            }
        }
    }

    @Override
    public boolean delete(String chatID) {
        for (Chat chat : chatList) {
            if (chat.getID().equals(chatID)) {
                chatList.remove(chat);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Chat> getUserChats(String userID) {
        List<Chat> userChats = new ArrayList<>();
        for (Chat chat : chatList) {
            if (chat.getFirstUserID().equals(userID) || chat.getSecondUserID().equals(userID)) {
                userChats.add(chat);
            }
        }
        return userChats;
    }

    @Override
    public Chat determineChat(String chatID, String user1ID) {

        return null;
    }

}
