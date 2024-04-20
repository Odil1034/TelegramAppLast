package uz.pdp.backend.models.message;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.models.receiver.ReceiverType;

import java.time.LocalDateTime;
import java.util.Date;

public class Message extends BaseModel {

    private String authorID;
    private String content;
    private String chatID;
    private LocalDateTime dateTime;

    public Message(String authorID, String content, String chatID, LocalDateTime dateTime) {
        this.authorID = authorID;
        this.content = content;
        this.dateTime = dateTime;
        this.chatID = chatID;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }
}
