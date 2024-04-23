package uz.pdp.backend.service.groupFollowerService;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.BaseService;
import uz.pdp.backend.models.groupFollower.GroupFollower;
import uz.pdp.backend.types.group.groupRole.GroupRole;

import java.util.List;

public interface GroupFollowerService extends BaseService<GroupFollower> {

    List<User> getUsersInGroup(String groupID);
    List<User> getAdminsInGroup(String groupID);
    List<Group> getGroupUsers(String userID);

    boolean addUserToGroup(String groupID, String userID, GroupRole role);
    int getCountOfUsersInGroup(String groupID);
}
