package uz.pdp.backend.models.groupFollower;

import uz.pdp.backend.models.BaseModel;
import uz.pdp.backend.types.group.groupRole.GroupRole;

public class GroupFollower extends BaseModel {

    private String userID;
    private String groupID;
    private GroupRole role;

    public GroupFollower(String userID, String groupID, GroupRole role) {
        this.userID = userID;
        this.groupID = groupID;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public GroupRole getRole() {
        return role;
    }

    public void setRole(GroupRole role) {
        this.role = role;
    }
}
