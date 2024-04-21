package uz.pdp.backend.service.groupService;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.models.message.Message;
import uz.pdp.backend.service.BaseService;


import java.util.List;

public interface GroupService extends BaseService<Group> {
    List<String> getUsersInGroup(String groupID);
    List<String> getAdminsInGroup(String groupID);
    List<String> getMessagesInGroup(String groupOrChatID);
    String getOwnerByGroupId(String groupID);
    String getGroupDescription(String groupID);

    boolean addUserInGroup(String groupID, String userID);
    boolean addAdminInGroup(String userID, String groupID);
    boolean addMessageInGroup(String MessageID, String groupID);

    List<Group> getGroups(String userID);

    // get messages in group method have a MessageService

}
