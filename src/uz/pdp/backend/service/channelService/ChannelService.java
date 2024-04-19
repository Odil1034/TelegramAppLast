package uz.pdp.backend.service.channelService;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.service.BaseService;

import java.util.List;

public interface ChannelService extends BaseService<Channel> {
    boolean writeContent(String channelId, String messageId);

    List<Channel> getChannels(String userID);

    boolean userSubscriptionToChannel(String channelID, String userID);
}
