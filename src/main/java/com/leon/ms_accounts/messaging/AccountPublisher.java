package com.leon.ms_accounts.messaging;

import com.google.gson.Gson;
import com.leon.ms_accounts.domain.dto.request.AccountMovementEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.leon.ms_accounts.MsAccountsApplication.topicExchangeName;

@Component
public class AccountPublisher {
    private final RabbitTemplate rabbitTemplate;

    public AccountPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void convertAndSend(AccountMovementEvent accountMovementEvent) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(topicExchangeName, "leon.accounts", new Gson().toJson(accountMovementEvent));
    }
}
