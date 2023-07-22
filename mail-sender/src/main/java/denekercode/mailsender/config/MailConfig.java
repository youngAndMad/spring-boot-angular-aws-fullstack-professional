package denekercode.mailsender.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private Integer port;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Value("${spring.mail.protocolType}")
    private String protocolType;

    @Value("${spring.mail.mailDebug}")
    private String mailDebug;

    @Value("${spring.mail.danekerscode}")
    private String danekerscode;

    @Value("${spring.mail.enable}")
    private String enable;

    @Bean
    public JavaMailSender getMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        mailSender.setUsername(username);
        mailSender.setPassword(this.password);

        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty(this.protocol, protocolType);
        properties.setProperty(mailDebug, danekerscode);
        properties.put(enable, danekerscode);

        return mailSender;
    }

}