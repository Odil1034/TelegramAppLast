package uz.pdp.backend.service.groupService;

import uz.pdp.backend.models.group.Group;

import java.util.*;

public class GroupServiceImp implements GroupService {

    private List<Group> groups;

    private GroupServiceImp() {
        groups = new ArrayList<>();
    }

    private static GroupServiceImp groupService;
    public static GroupService getInstance() {
        if (groupService == null) {
            groupService = new GroupServiceImp();
        }
        return groupService;
    }

    @Override
    public boolean create(Group newGroup) {
        for (Group group : groups) {
            if (group.getName().equals(newGroup.getName())) {
                return false;
            }
        }
        groups.add(newGroup);
        return true;
    }

    @Override
    public Group get(String groupID) {
        for (Group group : groups) {
            if (group.getID().equals(groupID)) {
                return group;
            }
        }
        return null;
    }

    @Override
    public List<Group> getList() {
        return groups;
    }

    @Override
    public void update(Group newGroup) {
        for (Group group : groups) {
            if (group.getID().equals(newGroup.getID())) {
                group.setName(newGroup.getName());
                group.setDescription(newGroup.getDescription());
                group.setOwnerID(newGroup.getOwnerID());
            }
        }
    }

    @Override
    public boolean delete(String groupID) {
        for (Group group : groups) {
            if (group.getID().equals(groupID)) {
                group.setDelete(true);
                return true;
            }
        }
        return false;
    }

//
//    @Override
//    public List<String> getUsersInGroup(String groupID) {
//        return new ArrayList<>(usersInGroup.get(groupID));
//    }
//
//    @Override
//    public List<String> getAdminsInGroup(String groupID) {
//        return new ArrayList<>(adminsInGroup.get(groupID));
//    }
//
//    @Override
//    public List<String> getMessagesInGroup(String groupID) {
//        return messagesInGroup.get(groupID);
//    }
//
//    @Override
//    public boolean addUserInGroup(String groupID, String userID) {
//        usersInGroup.get(groupID).add(userID);
//        return true;
//    }
//
//    @Override
//    public boolean addAdminInGroup(String userID, String groupID) {
//        adminsInGroup.get(groupID).add(userID);
//        return true;
//    }
//
//    @Override
//    public boolean addMessageInGroup(String MessageID, String groupID) {
//        messagesInGroup.get(groupID).add(MessageID);
//        return true;
//    }
//
//    @Override
//    public List<Group> getGroups(String userID) {
//
//        List<Group> groups = new ArrayList<>();
//
//        for (Group group : groups) {
//            String groupID = group.getID();
//            if (usersInGroup.get(groupID).contains(userID) ||
//                    adminsInGroup.get(groupID).contains(userID)) {
//                groups.add(group);
//            }
//        }
//        return groups;
//    }
//
//    @Override
//    public int getCountOfUsersInGroup(String groupID) {
//        return usersInGroup.get(groupID).size();
//    }

}
