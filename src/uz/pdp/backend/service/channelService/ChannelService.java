package uz.pdp.backend.service.channelService;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.service.BaseService;

import java.util.List;

public interface ChannelService extends BaseService<Channel> {

    List<Channel> getUserChannels(String userID);
    boolean unsubscribeChannel(String channelID, String userID);
    boolean hasUserSubscribedToChannel(String channelID, String userID);
    boolean writeContent(String channelID, String messageID);
    int getCountOfUsersInChannel(String channelID);
    boolean checkUserSubscriptionToChannel(String channelID, String userID);

}
