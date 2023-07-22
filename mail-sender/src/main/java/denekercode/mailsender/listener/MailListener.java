package denekercode.mailsender.listener;

import denekercode.mailsender.service.MailService;
import denekercode.mailsender.util.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MailListener {

    private final MailService mailService;

    @KafkaListener(topics = "greeting" , groupId = "mail")
    void sendGreeting(
            String link
    ){
        mailService.send(link);
    }
}
