package uz.pdp.backend.service.FollowerService;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.models.follower.Follower;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.channelService.ChannelService;
import uz.pdp.backend.service.channelService.ChannelServiceImp;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;

import java.util.ArrayList;
import java.util.List;

public class FollowerServiceImp implements FollowerService {

    UserService userService = UserServiceImp.getInstance();
    ChannelService channelService = ChannelServiceImp.getInstance();

    List<Follower> followerList;

    public FollowerServiceImp() {
        followerList = new ArrayList<>();
    }

    private static FollowerService followerService;
    public static FollowerService getInstance(){
        if(followerService == null){
            followerService = new FollowerServiceImp();
        }
        return followerService;
    }
    @Override
    public boolean create(Follower newFollower) {
        for (Follower follower : followerList) {
            if (follower.getUserID().equals(newFollower.getUserID()) &&
                follower.getChannelID().equals(newFollower.getChannelID())) {
                return false;
            }
        }
        followerList.add(newFollower);
        return true;
    }

    @Override
    public Follower get(String followerID) {
        for (Follower follower : followerList) {
            if (follower.getID().equals(followerID)) {
                return follower;
            }
        }
        return null;
    }

    @Override
    public List<Follower> getList() {
        return followerList;
    }

    @Override
    public void update(Follower newFollower) {
        for (Follower follower : followerList) {
            if (follower.getID().equals(newFollower.getID())) {
                follower.setChannelID(newFollower.getChannelID());
                follower.setUserID(newFollower.getUserID());
                follower.setRole(newFollower.getRole());
            }
        }
    }

    @Override
    public boolean delete(String followerID) {
        for (Follower follower : followerList) {
            if (follower.getID().equals(followerID)) {
                follower.setDelete(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getFollowers(String channelID) {
        List<User> followers = new ArrayList<>();
        for (Follower follower : followerList) {
            if (follower.getChannelID().equals(channelID)) {
                User user = userService.get(follower.getUserID());
                followers.add(user);
            }
        }
        return followers;
    }

    @Override
    public boolean checkUserSubscriptionToChannel(String channelID, String userID) {
        for (Follower follower : followerList) {
            if (follower.getChannelID().equals(channelID) &&
                follower.getUserID().equals(userID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Channel> getSubscribeChannels(String userID) {

        List<Channel> userChannels = new ArrayList<>();
        for (Follower follower : followerList) {
            if (follower.getUserID().equals(userID)) {
                Channel channel = channelService.get(follower.getChannelID());
                userChannels.add(channel);
            }
        }

        return userChannels;
    }

    @Override
    public List<Channel> getUnsubscribeChannels(String channelID, String userID) {

        List<Channel> unsubscribeChannels = new ArrayList<>();
        for (Follower follower : followerList) {
            if (!follower.getUserID().equals(userID)) {
                Channel channel = channelService.get(follower.getChannelID());
                unsubscribeChannels.add(channel);
            }
        }
        return unsubscribeChannels;
    }

    @Override
    public int getCountOfUsersInChannel(String channelID) {
        return getFollowers(channelID).size();
    }
}
