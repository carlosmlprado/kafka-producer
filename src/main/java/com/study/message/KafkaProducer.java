package com.study.message;

import com.study.dto.FamilyDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@Log4j2
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, FamilyDTO> kafkaTemplate;

    public void sendMessage(FamilyDTO data) {
        log.info(String.format("Message sent -> %s", data.toString()));
        Message<FamilyDTO> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "email-topic")
                .build();

        kafkaTemplate.send(message);

        try {
            sendMessageWithCallback(data);
        } catch (ExecutionException e) {
            log.error(e.getMessage());
        } catch (InterruptedException ex) {
            log.error(ex.getMessage());
        }
    }

    void sendMessageWithCallback(FamilyDTO data) throws ExecutionException, InterruptedException {
        CompletableFuture<SendResult<String, FamilyDTO>> future =
                (CompletableFuture<SendResult<String, FamilyDTO>>) kafkaTemplate.send("email-topic", data);

        log.info(future.get());
    }

}
