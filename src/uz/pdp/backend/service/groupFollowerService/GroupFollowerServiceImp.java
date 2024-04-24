package uz.pdp.backend.service.groupFollowerService;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.models.groupFollower.GroupFollower;
import uz.pdp.backend.service.groupService.GroupService;
import uz.pdp.backend.service.groupService.GroupServiceImp;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;
import uz.pdp.backend.types.group.GroupRole;

import java.util.ArrayList;
import java.util.List;

public class GroupFollowerServiceImp implements GroupFollowerService {

    UserService userService = UserServiceImp.getInstance();
    GroupService groupService = GroupServiceImp.getInstance();
    List<GroupFollower> groupFollowerList;

    private GroupFollowerServiceImp() {
        groupFollowerList = new ArrayList<>();
    }

    private static GroupFollowerService groupFollowerService;
    public static GroupFollowerService getInstance() {
        if (groupFollowerService == null) {
            groupFollowerService = new GroupFollowerServiceImp();
        }
        return groupFollowerService;
    }

    @Override
    public boolean create(GroupFollower newGroupFollower) {
        for (GroupFollower groupFollower : groupFollowerList) {
            if (groupFollower.getGroupID().equals(newGroupFollower.getGroupID()) &&
                groupFollower.getUserID().equals(newGroupFollower.getUserID())) {
                return false;
            }
        }
        groupFollowerList.add(newGroupFollower);
        return false;
    }

    @Override
    public GroupFollower get(String userGroupID) {
        for (GroupFollower groupFollower : groupFollowerList) {
            if (groupFollower.getID().equals(userGroupID)) {
                return groupFollower;
            }
        }
        return null;
    }

    @Override
    public List<GroupFollower> getList() {
        return groupFollowerList;
    }

    @Override
    public void update(GroupFollower newGroupFollower) {
        for (GroupFollower groupFollower : groupFollowerList) {
            if (groupFollower.getID().equals(newGroupFollower.getID())) {
                groupFollower.setUserID(newGroupFollower.getUserID());
                groupFollower.setGroupID(newGroupFollower.getGroupID());
                groupFollower.setRole(newGroupFollower.getRole());
            }
        }
    }

    @Override
    public boolean delete(String userGroupID) {
        for (GroupFollower groupFollower : groupFollowerList) {
            if (groupFollower.getID().equals(userGroupID)) {
                groupFollower.setDelete(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getUsersInGroup(String groupID) {

        List<User> userList = new ArrayList<>();
        for (GroupFollower groupFollower : groupFollowerList) {
            if (groupFollower.getGroupID().equals(groupID) &&
                groupFollower.getRole().equals(GroupRole.USER)) {
                User user = userService.get(groupFollower.getUserID());
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public List<User> getAdminsInGroup(String groupID) {
        List<User> adminList = new ArrayList<>();

        for (GroupFollower groupFollower : groupFollowerList) {
            if (groupFollower.getGroupID().equals(groupID) &&
                groupFollower.getRole().equals(GroupRole.ADMIN)) {
                User user = userService.get(groupFollower.getUserID());
                adminList.add(user);
            }
        }

        return adminList;
    }

    @Override
    public List<Group> getUserGroups(String userID) {

        List<Group> userGroups = new ArrayList<>();
        for (GroupFollower groupFollower : groupFollowerList) {
            if (groupFollower.getUserID().equals(userID)) {
                Group group = groupService.get(groupFollower.getGroupID());
                userGroups.add(group);
            }
        }

        return userGroups;
    }

    @Override
    public boolean addUserToGroup(String groupID, String userID, GroupRole role) {
        Group group = groupService.get(groupID);
        return false;
    }

    @Override
    public boolean checkUserMemberToGroup(String groupID, String userID) {
        for (User user : getUsersInGroup(groupID)) {
            if(user.getID().equals(userID)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getCountOfUsersInGroup(String groupID) {
        return getUsersInGroup(groupID).size();
    }
}
