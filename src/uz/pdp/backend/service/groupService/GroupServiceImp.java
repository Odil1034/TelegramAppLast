package uz.pdp.backend.service.groupService;

import uz.pdp.backend.models.group.Group;

import java.util.*;

public class GroupServiceImp implements GroupService {

    List<Group> groups;

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

    @Override
    public int countOfGroups() {
        return groups.size();
    }
}
