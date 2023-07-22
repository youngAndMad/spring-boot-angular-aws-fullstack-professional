package danekerscode.server.payload;

import danekerscode.server.model.AmazonFile;
import danekerscode.server.model.User;
import danekerscode.server.utils.Gender;

import java.util.List;


public record UserDTO(
        Integer id,
        String email,
        String name,
        String surname,
        Integer age,
        Gender gender,
        List<AmazonFile> files,
        TokenResponse tokenResponse
        ) {
    public UserDTO(
            User user,
            TokenResponse tokenResponse
    ){
        this(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getGender(),
                user.getFiles(),
                tokenResponse
        );
    }
}
