package uz.pdp.backend.service.groupService;

import uz.pdp.backend.models.group.Group;
import uz.pdp.backend.service.BaseService;


import java.util.List;

public interface GroupService extends BaseService<Group> {

    int countOfGroups();
}
