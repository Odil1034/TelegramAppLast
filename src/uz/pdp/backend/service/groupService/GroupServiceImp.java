package uz.pdp.backend.service.groupService;

import uz.pdp.backend.models.group.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupServiceImp implements GroupService {
    private static GroupServiceImp groupService;


    private final List<Group> groupList; // all groups list
    private final Map<String, List<String>> usersInGroup;  /// <groupID, < List<usersID> >
    private final Map<String, List<String>> adminsInGroup; /// <groupID, < List<adminsID> >
    private final Map<String, List<String>> messagesInGroup; /// <groupID, < List<MessagesID> >


    private GroupServiceImp() {
        adminsInGroup = new HashMap<>();
        groupList = new ArrayList<>();
        usersInGroup = new HashMap<>();
        messagesInGroup = new HashMap<>();

    }

    public static GroupService getInstance() {
        if (groupService == null) {
            groupService = new GroupServiceImp();
        }
        return groupService;
    }

    @Override
    public boolean create(Group group) {
        String groupID = group.getID();
        groupList.add(group);

        List<String> userList = new ArrayList<>();
        List<String> adminList = new ArrayList<>();
        List<String> messageList = new ArrayList<>();

        boolean add = userList.add(group.getOwnerID());

        usersInGroup.put(groupID, userList);
        adminsInGroup.put(groupID, adminList);
        messagesInGroup.put(groupID, messageList);
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
    public List<String> getUsersInGroup(String groupID) {
        return usersInGroup.get(groupID);
    }

    @Override
    public List<String> getAdminsInGroup(String groupID) {
        return adminsInGroup.get(groupID);
    }

    @Override
    public List<String> getMessagesInGroup(String groupID) {
        return messagesInGroup.get(groupID);
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


    @Override
    public boolean addUserInGroup(String groupID, String userID) {
        usersInGroup.get(groupID).add(userID);
        return true;
    }

    @Override
    public boolean addAdminInGroup(String userID, String groupID) {
        adminsInGroup.get(groupID).add(userID);
        return true;
    }

    @Override
    public boolean addMessageInGroup(String MessageID, String groupID) {
        messagesInGroup.get(groupID).add(MessageID);
        return true;
    }

    @Override
    public List<Group> getGroups(String userID) {

        List<Group> groups = new ArrayList<>();

        for (Group group : groupList) {
            String groupID = group.getID();
            if (usersInGroup.get(groupID).contains(userID) ||
                    adminsInGroup.get(groupID).contains(userID)) {
                groups.add(group);
            }
        }
        return groups;
    }
}
