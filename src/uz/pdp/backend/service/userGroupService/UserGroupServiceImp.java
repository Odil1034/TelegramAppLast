package uz.pdp.backend.service.userGroupService;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.models.userGroup.UserGroup;
import uz.pdp.backend.service.groupService.GroupService;
import uz.pdp.backend.service.groupService.GroupServiceImp;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;
import uz.pdp.backend.types.group.groupRole.GroupRole;

import java.util.ArrayList;
import java.util.List;

public class UserGroupServiceImp implements UserGroupService {

    UserService userService = UserServiceImp.getInstance();
    GroupService groupService = GroupServiceImp.getInstance();
    private List<UserGroup> userGroupList;

    private UserGroupServiceImp() {
        userGroupList = new ArrayList<>();
    }

    private static UserGroupService userGroupService;
    public static UserGroupService getInstance() {
        if (userGroupService == null) {
            userGroupService = new UserGroupServiceImp();
        }
        return userGroupService;
    }

    @Override
    public boolean create(UserGroup newUserGroup) {
        for (UserGroup userGroup : userGroupList) {
            if (userGroup.getGroupID().equals(newUserGroup.getGroupID()) &&
                userGroup.getUserID().equals(newUserGroup.getUserID())) {
                return false;
            }
        }
        userGroupList.add(newUserGroup);
        return false;
    }

    @Override
    public UserGroup get(String userGroupID) {
        for (UserGroup userGroup : userGroupList) {
            if (userGroup.getID().equals(userGroupID)) {
                return userGroup;
            }
        }
        return null;
    }

    @Override
    public List<UserGroup> getList() {
        return userGroupList;
    }

    @Override
    public void update(UserGroup newUserGroup) {
        for (UserGroup userGroup : userGroupList) {
            if (userGroup.getID().equals(newUserGroup.getID())) {
                userGroup.setUserID(newUserGroup.getUserID());
                userGroup.setGroupID(newUserGroup.getGroupID());
                userGroup.setRole(newUserGroup.getRole());
            }
        }
    }

    @Override
    public boolean delete(String userGroupID) {
        for (UserGroup userGroup : userGroupList) {
            if (userGroup.getID().equals(userGroupID)) {
                userGroup.setDelete(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getUsersInGroup(String groupID) {

        List<User> userList = new ArrayList<>();
        for (UserGroup userGroup : userGroupList) {
            if (userGroup.getGroupID().equals(groupID) &&
                userGroup.getRole().equals(GroupRole.USER)) {
                User user = userService.get(userGroup.getUserID());
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public List<User> getAdminsInGroup(String groupID) {

        List<User> adminList = new ArrayList<>();
        for (UserGroup userGroup : userGroupList) {
            if (userGroup.getGroupID().equals(groupID) &&
                userGroup.getRole().equals(GroupRole.ADMIN)) {
                User user = userService.get(userGroup.getUserID());
                adminList.add(user);
            }
        }

        return adminList;
    }

    @Override
    public boolean addUserToGroup(String groupID, String userID, GroupRole role) {
        return create(new UserGroup(userID, groupID, role));
    }

    @Override
    public List<Group> getUserGroups(String userID) {

        List<Group> userGroups = new ArrayList<>();
        for (UserGroup userGroup : userGroupList) {
            if (userGroup.getUserID().equals(userID)) {
                Group group = groupService.get(userGroup.getGroupID());
                userGroups.add(group);
            }
        }

        return userGroups;
    }

    @Override
    public int getCountOfUsersInGroup(String groupID) {
        return getUsersInGroup(groupID).size();
    }
}
