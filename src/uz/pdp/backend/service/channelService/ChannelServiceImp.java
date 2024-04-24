package uz.pdp.backend.service.channelService;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.models.follower.Follower;
import uz.pdp.backend.models.message.Message;
import uz.pdp.backend.service.FollowerService.FollowerService;
import uz.pdp.backend.service.FollowerService.FollowerServiceImp;
import uz.pdp.backend.service.messageService.MessageService;
import uz.pdp.backend.service.messageService.MessageServiceImp;
import uz.pdp.backend.service.userService.UserService;
import uz.pdp.backend.service.userService.UserServiceImp;
import uz.pdp.backend.types.receiverType.ReceiverType;

import java.util.*;

public class ChannelServiceImp implements ChannelService {

    UserService userService = UserServiceImp.getInstance();
    MessageService messageService = MessageServiceImp.getInstance();
    private static List<Channel> channels;

    private ChannelServiceImp() {
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
                return channels.remove(channel);
            }
        }
        return false;
    }

    @Override
    public boolean writeContent(String channelID, Message message) {
        boolean b = messageService.create(message);
        if(!messageService.create(message)){
            message.setReceiverType(ReceiverType.CHANNEL);
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<Channel> getMyChannels(String ownerID) {
        List<Channel> ownerChannels = new ArrayList<>();
        for (Channel channel : channels) {
            if (channel.getOwnerID().equals(ownerID)) {
                ownerChannels.add(channel);
            }
        }
        return ownerChannels;
    }

    @Override
    public boolean deleteChannel(String channelID) {
        FollowerService followerService = FollowerServiceImp.getInstance();
        for (Follower follower : followerService.getList()) {
            if (follower.getChannelID().equals(channelID)) {
                followerService.delete(follower.getID());
            }
        }
        channels.removeIf(channel -> channel.getID().equals(channelID));
        return true;
    }

    public int countAllChannels(){
        return channels.size();
    }
}
