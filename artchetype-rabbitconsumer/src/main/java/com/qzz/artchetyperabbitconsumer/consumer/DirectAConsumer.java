package com.qzz.artchetyperabbitconsumer.consumer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qzz.artchetyperabbitconsumer.constants.QueueConstants;
import com.qzz.artchetyperabbitconsumer.entity.MessageDTO;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
@RabbitListener(queues = {QueueConstants.DEFAULT_DIRECT_QUEUE_A}, containerFactory = "simpleListener")
public class DirectAConsumer {

    @RabbitHandler
    public void receiver(Message msg, Channel channel, byte[] message) throws IOException {
        MessageProperties properties = msg.getMessageProperties();
        long tagId = properties.getDeliveryTag();
        channel.basicAck(tagId, true);
        String messageReceive = new String(message);
        Gson gson = new Gson();
        MessageDTO<String> messageDTO = gson.fromJson(messageReceive, new TypeToken<MessageDTO<String>>(){}.getType());
        log.info("id:{}, message {}",properties.getMessageId(), messageDTO);
    }
}
