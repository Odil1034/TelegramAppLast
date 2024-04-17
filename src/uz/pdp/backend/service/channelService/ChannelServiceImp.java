package uz.pdp.backend.service.channelService;

import uz.pdp.backend.models.channel.Channel;

import java.util.ArrayList;
import java.util.List;

public class ChannelServiceImp implements ChannelService{

    private static ChannelService service;
    private List<Channel> channelList;

    public static ChannelService getInstance() {
        if (service == null){
            service = new ChannelServiceImp();
        }
        return service;
    }

    public ChannelServiceImp() {
        channelList = new ArrayList<>();
    }

    @Override
    public boolean create(Channel channel) {
        channelList.add(channel);
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
}
