package uz.pdp.backend.service.groupService;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.message.Message;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.BaseService;

import java.util.List;

public interface GroupService extends BaseService<Group> {
    List<User> getUsersInGroup(String groupID);
    List<User> getAdminsInGroup(String groupID);
    List<Message> getMessagesInGroup(String groupID);
    String getOwnerUserId(String groupID);
    String getGroupDescription(String groupID);

}
