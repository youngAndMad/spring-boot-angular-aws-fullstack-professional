package danekerscode.server.payload;

import danekerscode.server.utils.Gender;

public record RegistrationRequest(
        String email,
        String name,
        String surname,
        String password,
        Integer age,
        Gender gender
) {
}
