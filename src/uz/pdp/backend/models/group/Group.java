package uz.pdp.backend.models.group;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.types.group.GroupStatus;

import java.util.List;

public class Group extends BaseModel {

    private String name;
    private String ownerID;
    private String description;
    private GroupStatus status;

    public Group(String name, String authorID, String description) {
        this.name = name;
        this.ownerID = authorID;
        this.description = description;
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

    public void setDescription(String massageID) {
        this.description = description;
    }
}
