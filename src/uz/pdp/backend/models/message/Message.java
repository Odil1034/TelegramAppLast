package uz.pdp.backend.models.message;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.types.receiverType.ReceiverType;

import java.time.LocalDateTime;

public class Message extends BaseModel {

    private String authorID;
    private String content;
    private String receiverID;
    private ReceiverType receiverType;
    private LocalDateTime dateTime;

    public Message(String authorID, String content, String receiverID,
                   ReceiverType receiverType, LocalDateTime dateTime) {
        this.authorID = authorID;
        this.content = content;
        this.receiverID = receiverID;
        this.receiverType = receiverType;
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

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public ReceiverType getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(ReceiverType receiverType) {
        this.receiverType = receiverType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
