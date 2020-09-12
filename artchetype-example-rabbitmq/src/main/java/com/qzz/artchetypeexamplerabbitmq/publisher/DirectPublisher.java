package com.qzz.artchetypeexamplerabbitmq.publisher;

import com.qzz.artchetypeexamplerabbitmq.config.rabbitmq.QueueConfig;
import com.qzz.artchetypeexamplerabbitmq.constants.QueueConstants;
import com.qzz.artchetypeexamplerabbitmq.entity.MessageDTO;
import com.qzz.artchetypeexamplerabbitmq.enums.QueueEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class DirectPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void sendA(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        MessageDTO<String> messageDTO = new MessageDTO<>();
        messageDTO.setBody(message);
        messageDTO.setRequestId(correlationData.getId());
        rabbitTemplate.convertAndSend(QueueEnum.DIRECT_QUEUE_A.getExchange(), QueueConstants.DEFAULT_DIRECT_ROUTING_A, messageDTO, correlationData);
    }

    public void sendB(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        MessageDTO<String> messageDTO = new MessageDTO<>();
        messageDTO.setBody(message);
        messageDTO.setRequestId(correlationData.getId());
        rabbitTemplate.convertAndSend(QueueEnum.DIRECT_QUEUE_B.getExchange(), QueueEnum.DIRECT_QUEUE_B.getRouteKey(), messageDTO, correlationData);
    }

    public void sendMultiB(String message) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        MessageDTO<String> messageDTO = new MessageDTO<>();
        messageDTO.setBody(message);
        messageDTO.setRequestId(correlationData.getId());
        rabbitTemplate.convertAndSend(QueueEnum.DIRECT_QUEUE_MULTIB.getExchange(), QueueEnum.DIRECT_QUEUE_MULTIB.getRouteKey(), messageDTO, correlationData);
    }

}
