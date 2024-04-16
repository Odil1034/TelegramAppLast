package uz.pdp.backend.models.receiver;

public class ReceiverType {

    private String chatID;
    private String groupID;

    public ReceiverType(String chatID, String groupID) {
        this.chatID = chatID;
        this.groupID = groupID;
    }

    public String getChatID() {
        return chatID;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
}
