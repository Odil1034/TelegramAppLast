package uz.pdp.backend.service.channelService;

import uz.pdp.backend.models.channel.Channel;
import uz.pdp.backend.service.BaseService;

public interface ChannelService extends BaseService<Channel> {
    boolean writeContent(String channelId, String messageId);
}
