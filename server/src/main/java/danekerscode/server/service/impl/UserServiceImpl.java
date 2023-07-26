package danekerscode.server.service.impl;

import danekerscode.server.exception.EmailRegisteredException;
import danekerscode.server.exception.UserNotFoundException;
import danekerscode.server.model.User;
import danekerscode.server.payload.RegistrationRequest;
import danekerscode.server.payload.UserDTO;
import danekerscode.server.repository.UserRepository;
import danekerscode.server.service.AuthService;
import danekerscode.server.service.UserService;
import danekerscode.server.utils.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthService authService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.greeting.prefix}")
    private String GREETING_PREFIX;


    @SneakyThrows
    public void sendMail(RegistrationRequest request) {
        var optional = userRepository.findUserByEmail(request.email());

        if (optional.isPresent()) {
            throw new EmailRegisteredException(request.email());
        }

        kafkaTemplate.send("greeting", "%s/%s?verification_token=%s&data=%s"
                .formatted(
                        GREETING_PREFIX,
                        request.email(),
                        authService.generateToken(request.email(), TokenType.VERIFICATION),
                        authService.encodeUserData(request)
                )
        );
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }


    @SneakyThrows
    public UserDTO save(String data) {
        var request = authService.decodeUserDate(data);
        var user = userRepository.save(User.builder()
                .age(request.age())
                .name(request.name())
                .email(request.email())
                .surname(request.surname())
                .password(authService.encodePassword((request.password())))
                .gender(request.gender())
                .build());

        return new UserDTO(user, authService.createResponse(user.getEmail()));
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
