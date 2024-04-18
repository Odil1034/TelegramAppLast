package uz.pdp.backend.service.channelService;

import uz.pdp.backend.models.channel.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelServiceImp implements ChannelService{

    private static ChannelService channelService;
    private final List<Channel> channelList;
    private Map <String, List<String>> messagesInChannels; // k = channelId, v = message list of channel

    public static ChannelService getInstance() {
        if (channelService == null){
            channelService = new ChannelServiceImp();
        }
        return channelService;
    }

    public ChannelServiceImp() {
        channelList = new ArrayList<>();
        messagesInChannels = new HashMap<>();
    }

    @Override
    public boolean create(Channel channel) {
        channelList.add(channel);
        messagesInChannels.put(channel.getID(), new ArrayList<>());
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
    public void update(Channel newM) {
        //???
    }

    @Override
    public boolean delete(String ID) {
        for (Channel channel : channelList) {
            if (channel.getID().equals(ID)){
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
}
