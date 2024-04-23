package uz.pdp.backend.models.follower;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.types.channel.ChannelRole;

public class Follower extends BaseModel {

    private String channelID;
    private String userID;
    private ChannelRole role;

    public Follower(String channelID, String userID, ChannelRole role) {
        this.channelID = channelID;
        this.userID = userID;
        this.role = role;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ChannelRole getRole() {
        return role;
    }

    public void setRole(ChannelRole role) {
        this.role = role;
    }
}
