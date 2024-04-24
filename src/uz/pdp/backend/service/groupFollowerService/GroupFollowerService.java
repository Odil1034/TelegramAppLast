package uz.pdp.backend.service.groupFollowerService;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.BaseService;
import uz.pdp.backend.models.groupFollower.GroupFollower;
import uz.pdp.backend.types.group.GroupRole;

import java.util.List;

public interface GroupFollowerService extends BaseService<GroupFollower> {

    List<User> getUsersInGroup(String groupID);
    List<User> getAdminsInGroup(String groupID);
    List<Group> getUserGroups(String userID);
    boolean addUserToGroup(String groupID, String userID, GroupRole role);
    boolean checkUserMemberToGroup(String groupID, String userID);
    int getCountOfUsersInGroup(String groupID);
}
