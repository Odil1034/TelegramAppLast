package uz.pdp.backend.service.FollowerService;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.models.follower.Follower;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.BaseService;

import java.util.List;

public interface FollowerService extends BaseService<Follower> {

    List<User> getFollowers(String channelID);
    List<Channel> getSubscribeChannels(String userID);
    List<Channel> getUnsubscribeChannels(String channelID, String userID);
    boolean checkUserSubscriptionToChannel(String channelID, String userID);
    int getCountOfUsersInChannel(String channelID);

}
