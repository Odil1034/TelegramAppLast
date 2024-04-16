package uz.pdp.backend.models.message;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.models.receiver.ReceiverType;

import java.util.Date;

public class Message extends BaseModel {

    private String authorID;
    private String message;
    private ReceiverType receiver;
    private Date date;

    public Message(String authorID, String message, Date date) {
        this.authorID = authorID;
        this.message = message;
        this.date = date;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReceiverType getReceiver() {
        return receiver;
    }

    public void setReceiver(ReceiverType receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
