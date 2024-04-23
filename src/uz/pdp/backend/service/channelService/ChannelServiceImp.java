package uz.pdp.backend.service.channelService;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.models.user.User;
import uz.pdp.backend.service.messageService.MessageService;
import uz.pdp.backend.service.messageService.MessageServiceImp;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;

import java.util.*;

public class ChannelServiceImp implements ChannelService {

    UserService userService = UserServiceImp.getInstance();
    MessageService messageService = MessageServiceImp.getInstance();

    private static List<Channel> channels;
    private Map <String, List<String>> messagesInChannels; // k = channelId, v = message list of channel
    private Map <String, Set<String>> subscribedUsers; // k = channelId, v = subscribed users list


    public ChannelServiceImp() {
        channels = new ArrayList<>();
    }

    private static ChannelService channelService;

    public static ChannelService getInstance() {
        if (channelService == null) {
            channelService = new ChannelServiceImp();
        }
        return channelService;
    }

    @Override
    public boolean create(Channel newChannel) {
        for (Channel channel : channels) {
            if (channel.getName().equals(newChannel.getName())) {
                return false;
            }
        }
        channels.add(newChannel);
        return true;
    }

    @Override
    public Channel get(String channelID) {
        for (Channel channel : channels) {
            if (channel.getID().equals(channelID)) {
                return channel;
            }
        }
        return null;
    }

    @Override
    public List<Channel> getList() {
        return channels;
    }

    @Override
    public void update(Channel newChannel) {
        for (Channel channel : channels) {
            if (channel.getID().equals(newChannel.getID())) {
                channel.setName(newChannel.getName());
                channel.setOwnerID(newChannel.getOwnerID());
                channel.setDescription(newChannel.getDescription());
            }
        }
    }

    @Override
    public boolean delete(String channelID) {
        for (Channel channel : channels) {
            if (channel.getID().equals(channelID)) {
                channel.setDelete(true);
                return true;
            }
        }
        return false;
    }

    public boolean subscribeUser(String userID, String channelID){
        for (Channel channel : channels) {
            if (channel.getID().equals(channelID)) {

            }
        }
        return true;
    }

    @Override
    public List<Channel> getUserChannels(String userID) {

        List<Channel> userChannels = new ArrayList<>();

        for (Channel channel : channels) {
            if (subscribedUsers.get(channel.getID()).contains(userID)) {
                userChannels.add(channel);
            }
        }
        return channels;
    }

    @Override
    public boolean writeContent(String channelID, String messageID) {
        List<String> messages = messagesInChannels.get(channelID);
        return messages.add(messageID);
    }

    @Override
    public boolean checkUserSubscriptionToChannel(String channelID, String userID) {
        Set<String> users = subscribedUsers.get(channelID);
        if (users == null) {
            throw new NullPointerException();
        }
        return users.add(userID);
    }

    @Override
    public boolean hasUserSubscribedToChannel(String channelID, String userID) {
        return subscribedUsers.get(channelID).contains(userID);
    }

    @Override
    public boolean unsubscribeChannel(String channelID, String userID) {
        return subscribedUsers.get(channelID).remove(userID);
    }

    @Override
    public int getCountOfUsersInChannel(String channelID) {
        return subscribedUsers.get(channelID).size();
    }
}
