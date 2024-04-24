package uz.pdp.backend.service.channelService;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.models.message.Message;
import uz.pdp.backend.service.BaseService;

import java.util.List;

public interface ChannelService extends BaseService<Channel> {

    boolean writeContent(String channelID, Message message);
    List<Channel> getMyChannels(String userID);
    boolean deleteChannel(String channelID);
}
