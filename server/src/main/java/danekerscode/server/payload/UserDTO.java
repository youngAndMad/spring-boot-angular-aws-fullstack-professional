package danekerscode.server.payload;

import danekerscode.server.model.User;


public record UserDTO(
        User user,
        TokenResponse tokens
) {
}
