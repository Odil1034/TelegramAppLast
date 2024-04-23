package uz.pdp.backend.models.chat;

import uz.pdp.backend.models.BaseModel;

public class Chat extends BaseModel {
    private String firstUserID;
    private String secondUserID;

    public Chat(String firstUserID, String secondUserID) {
        this.firstUserID = firstUserID;
        this.secondUserID = secondUserID;
    }

    public String getFirstUserID() {
        return firstUserID;
    }

    public void setFirstUserID(String firstUserID) {
        this.firstUserID = firstUserID;
    }

    public String getSecondUserID() {
        return secondUserID;
    }

    public void setSecondUserID(String secondUserID) {
        this.secondUserID = secondUserID;
    }
}
