package com.qzz.artchetyperabbitconsumer.consumer;

import com.qzz.artchetyperabbitconsumer.constants.QueueConstants;
import com.qzz.artchetyperabbitconsumer.entity.MessageDTO;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
@RabbitListener(queues = {QueueConstants.DIRECT_QUEUE_B}, containerFactory = "simpleListener")
public class DirectBConsumer {

    @RabbitHandler
    public void receiver(Message msg, Channel channel, byte[] message) throws IOException {
        MessageProperties properties = msg.getMessageProperties();
        long tagId = properties.getDeliveryTag();
        channel.basicAck(tagId, true);
        String messageReceive = new String(message);
        log.info("id:{}, message {}",properties.getMessageId(), messageReceive);
    }
}
