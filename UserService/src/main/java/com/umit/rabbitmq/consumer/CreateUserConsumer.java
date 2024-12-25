package com.umit.rabbitmq.consumer;

import com.umit.dto.request.UserSaveRequestDto;
import com.umit.rabbitmq.model.CreateUserModel;
import com.umit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    /**
     * Bu kısım RabbiMq tarafındaki kendisine ait kuyruğu dinleyecek ve ne zaman kuyruğa bir mesaj gelse
     * bu mesajı alacak ve işlem yapacaktır.
     */
    private final UserService userService;

    @RabbitListener(queues = "auth-queue")
    public void createUserListener(CreateUserModel model) {
        System.out.println("Kuyruk gelen mesaj: " + model);
        userService.save(UserSaveRequestDto.builder()
                .email(model.getEmail())
                .userName(model.getUserName())
                .authId(model.getAuthId())
                .build());
    }



}
