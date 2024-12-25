package com.umit.rabbitmq.producer;

import com.umit.rabbitmq.model.CreateUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(CreateUserModel model) {
        rabbitTemplate.convertAndSend("auth-exchange", "auth-binding-key", model);
    }
}
