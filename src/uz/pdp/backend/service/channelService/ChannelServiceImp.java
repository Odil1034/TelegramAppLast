package uz.pdp.backend.service.channelService;

import uz.pdp.backend.models.channel.Channel;

import java.util.*;

public class ChannelServiceImp implements ChannelService{

    private static ChannelService channelService;
    private final List<Channel> channelList;
    private final Map <String, List<String>> messagesInChannels; // k = channelId, v = message list of channel
    private final Map <String, Set<String>> subscribedUsers; // k = channelId, v = subscribed users list

    public static ChannelService getInstance() {
        if (channelService == null){
            channelService = new ChannelServiceImp();
        }
        return channelService;
    }

    public ChannelServiceImp() {
        channelList = new ArrayList<>();
        messagesInChannels = new HashMap<>();
        subscribedUsers = new HashMap<>();
    }

    @Override
    public boolean create(Channel channel) {
        channelList.add(channel);
        messagesInChannels.put(channel.getID(), new ArrayList<>());
        subscribedUsers.put(channel.getID(), new HashSet<>(List.of(channel.getAuthorID())));
        return true;
    }

    @Override
    public Channel get(String ID) {
        for (Channel channel : channelList) {
            if (channel.getID().equals(ID)){
                return channel;
            }
        }
        return null;
    }

    @Override
    public List<Channel> getList() {
        return channelList;
    }

    @Override
    public void update(Channel newChannel) {
//        ???
    }

    @Override
    public boolean delete(String channelID) {
        for (Channel channel : channelList) {
            if (channel.getID().equals(channelID)){
                channelList.remove(channel);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean writeContent(String channelId, String messageId) {
        List<String> messages = messagesInChannels.get(channelId);
       return messages.add(messageId);
    }

    @Override
    public List<Channel> getChannels(String userID) {

        List<Channel> channels = new ArrayList<>();

        for (Channel channel : channelList) {
            if (subscribedUsers.get(channel.getID()).contains(userID)) {
                channels.add(channel);
            }
        }
        return channels;
    }

    @Override
    public boolean userSubscriptionToChannel(String channelID, String userID) {
        Set<String> users = subscribedUsers.get(channelID);
        if (users == null){
            throw new NullPointerException();
        }
       return users.add(userID);
    }


    @Override
    public boolean hasUserSubscribedToChannel(String channelID, String userID) {
        return subscribedUsers.get(channelID).contains(userID);
    }

    @Override
    public boolean unsubscribeChannel(String channelID, String userId) {
       return subscribedUsers.get(channelID).remove(userId);
    }
}
