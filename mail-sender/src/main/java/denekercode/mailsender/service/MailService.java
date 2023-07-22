package denekercode.mailsender.service;

import denekercode.mailsender.util.EmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void send(String link){
        log.info("sending new request {}" , link);
        javaMailSender.send(create.apply(getEmail.apply(link),link));
    }

    private final Function<String,String> getEmail = link -> link.substring("http://localhost:8080/api/v1/auth/".length() , link.indexOf('?'));


    private final BiFunction<String,String, SimpleMailMessage> create = (email,link) -> {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("danekerscode@gmail.com");
        simpleMailMessage.setSubject("Welcome to Danekerscode system");
        simpleMailMessage.setText(link);
        return simpleMailMessage;
    };
}
