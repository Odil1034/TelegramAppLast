package uz.pdp.backend.service.messageService;

import uz.pdp.backend.models.message.Message;
import uz.pdp.backend.service.BaseService;

import java.util.List;

public interface MessageService extends BaseService<Message>{
    List<Message> getMessagesByChatID(String chatID);

}
