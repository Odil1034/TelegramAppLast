package uz.pdp.backend.models.message;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.models.receiver.ReceiverType;

import java.util.Date;

public class Message extends BaseModel {

    private String authorID;
    private String content;
    private String ChatID;
    private Date date;

    public Message(String authorID, String content, String chatID, Date date) {
        this.authorID = authorID;
        this.content = content;
        this.date = date;
        this.ChatID = chatID;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getChatID() {
        return ChatID;
    }

    public void setChatID(String chatID) {
        ChatID = chatID;
    }
}
