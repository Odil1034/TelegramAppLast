package uz.pdp.backend.models.message;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.types.receiverType.ReceiverType;

import java.time.LocalDateTime;

public class Message extends BaseModel {

    private String authorID;
    private String content;
    private String chatOrGroupID;
    private ReceiverType receiverType;
    private LocalDateTime dateTime;

    public Message(String authorID, String content, String chatOrGroupID, LocalDateTime dateTime) {
        this.authorID = authorID;
        this.content = content;
        this.chatOrGroupID = chatOrGroupID;
        this.dateTime = dateTime;
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

    public String getChatOrGroupID() {
        return chatOrGroupID;
    }

    public void setChatOrGroupID(String chatOrGroupID) {
        this.chatOrGroupID = chatOrGroupID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
