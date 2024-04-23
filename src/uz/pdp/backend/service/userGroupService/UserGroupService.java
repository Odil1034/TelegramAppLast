package uz.pdp.backend.service.userGroupService;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.BaseService;
import uz.pdp.backend.models.userGroup.UserGroup;
import uz.pdp.backend.types.group.groupRole.GroupRole;

import java.util.List;

public interface UserGroupService extends BaseService<UserGroup> {

    List<User> getUsersInGroup(String groupID);
    List<User> getAdminsInGroup(String groupID);

    boolean addUserToGroup(String groupID, String userID, GroupRole role);

    List<Group> getUserGroups(String userID);
    int getCountOfUsersInGroup(String groupID);
}
