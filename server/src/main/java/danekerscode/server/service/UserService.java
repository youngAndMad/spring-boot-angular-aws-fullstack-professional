package danekerscode.server.service;

import danekerscode.server.model.User;
import danekerscode.server.payload.RegistrationRequest;
import danekerscode.server.payload.UserDTO;

import java.util.List;

public interface UserService {
    void sendMail(RegistrationRequest request);
    User getUser(Integer id);
    UserDTO save(String data);
    void deleteById(Integer id);
    List<User> findAll();
}
