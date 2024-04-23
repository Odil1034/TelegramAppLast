package uz.pdp.backend.models.channel;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.types.channel.ChannelStatus;

public class Channel extends BaseModel {

    private String name;
    private String ownerID;
    private String description;
    private ChannelStatus status;

    public Channel(String name, String ownerID, String description) {
        this.name = name;
        this.ownerID = ownerID;
        this.description = description;
        this.status = ChannelStatus.ACTIVE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChannelStatus getStatus() {
        return status;
    }

    public void setStatus(ChannelStatus status) {
        this.status = status;
    }
}
