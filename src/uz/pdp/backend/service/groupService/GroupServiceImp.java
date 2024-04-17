package uz.pdp.backend.service.groupService;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.message.Message;
import uz.pdp.backend.models.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupServiceImp implements GroupService {
    private static GroupServiceImp groupService;


    private final List<Group> groupList;
    private final Map <String, List<User>> usersInGroup;
    private final Map <String, List<User>> adminsInGroup;


    private GroupServiceImp() {
        adminsInGroup = new HashMap<>();
        groupList = new ArrayList<>();
        usersInGroup = new HashMap<>();
    }

    public static GroupService getInstance() {
        if (groupService == null) {
            groupService = new GroupServiceImp();
        }
        return groupService;
    }

    @Override
    public boolean create(Group group) {
        groupList.add(group);
        return true;
    }

    @Override
    public Group get(String ID) {
        for (Group group : groupList) {
            if (group.getID().equals(ID)) return group;
        }
        return null;
    }

    @Override
    public List<Group> getList() {
        return groupList;
    }

    @Override
    public void update(Group newM) {
        //???
    }

    @Override
    public boolean delete(String ID) {
        for (Group group : groupList) {
            if (group.getID().equals(ID)) {
                groupList.remove(group);
                return true;
            }
        }
        return false;
    }


    @Override
    public List<User> getUsersInGroup(String groupID) {
        return  usersInGroup.get(groupID);
    }

    @Override
    public List<User> getAdminsInGroup(String groupID) {
        return adminsInGroup.get(groupID);
    }

    @Override
    public List<Message> getMessagesInGroup(String groupID) {
        return null;
    }

    @Override
    public String getOwnerUserId(String groupID) {
        for (Group group : groupList) {
            if (group.getID().equals(groupID)) {
                return group.getOwnerID();
            }
        }
        return null;
    }

    @Override
    public String getGroupDescription(String groupID) {
        for (Group group : groupList) {
            if (group.getID().equals(groupID)) {
                return group.getDescription();
            }
        }
        return null;
    }
}