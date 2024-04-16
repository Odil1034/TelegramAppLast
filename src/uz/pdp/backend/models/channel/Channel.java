package uz.pdp.backend.models.channel;

import uz.pdp.backend.models.BaseModel;

public class Channel extends BaseModel {

    private String name;
    private String authorID;
    private String messageID;
    private String userID;
    private int countOfUser;

    public Channel(String name, String authorID, String messageID, String userID, int countOfUser) {
        this.name = name;
        this.authorID = authorID;
        this.messageID = messageID;
        this.userID = userID;
        this.countOfUser = countOfUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getCountOfUser() {
        return countOfUser;
    }

    public void setCountOfUser(int countOfUser) {
        this.countOfUser = countOfUser;
    }
}
