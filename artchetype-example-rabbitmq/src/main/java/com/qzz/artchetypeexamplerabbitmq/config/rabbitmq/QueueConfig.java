package com.qzz.artchetypeexamplerabbitmq.config.rabbitmq;

import com.qzz.artchetypeexamplerabbitmq.enums.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class QueueConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        template.setMandatory(true);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("回调ID:{}", correlationData);
                if (ack) {
                    log.info("消费成功");
                } else {
                    log.error("消费失败:{}", cause);
                }
            }
        });
        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyMessage, String exchange, String routingKey) {
                log.info("return message: 消息：{}，响应：[{}-{}]，交换机：{}，路由键：{}", message, replyCode, replyMessage, exchange, routingKey);
            }
        });
        return template;
    }



   // @Bean
   // public DirectExchange directExchange() {
   //     return new DirectExchange(DEFAULT_DIRECT_EXCHANGE);
   // }

   // @Bean
   // public Queue directQueueA() {
   //     return new Queue(DEFAULT_DIRECT_QUEUE_A);
   // }

   // @Bean
   // public Binding directBinding(@Qualifier("directQueueA") Queue directQueueA, @Qualifier("directExchange") DirectExchange directExchange) {
   //     return BindingBuilder.bind(directQueueA).to(directExchange).with(DEFAULT_DIRECT_ROUTING_A);
   // }

   // public DirectExchange directExchangeB() {
   //     return new DirectExchange(DIRECT_QUEUE_B);
   // }

//    @Bean
//    public MessageConverter messageConverter() {
//        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter());
//    }

//    @Bean("simpleListener")
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setBatchSize(1);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }

    @Bean
    public RabbitAdmin rabbitAdmin(@Qualifier("rabbitTemplate") RabbitTemplate template) {
        RabbitAdmin admin = new RabbitAdmin(template);
        for (QueueEnum queueEnum : QueueEnum.values()) {
            admin.declareQueue(new Queue(queueEnum.getQueue()));
            admin.declareExchange(new DirectExchange(queueEnum.getExchange()));
            admin.declareBinding(BindingBuilder.bind(new Queue(queueEnum.getQueue())).to(new DirectExchange(queueEnum.getExchange())).with(queueEnum.getRouteKey()));
        }
        return admin;
    }

}
