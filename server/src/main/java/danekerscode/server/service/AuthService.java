package danekerscode.server.service;

import danekerscode.server.payload.AuthRequest;
import danekerscode.server.payload.RegistrationRequest;
import danekerscode.server.payload.TokenResponse;
import danekerscode.server.utils.TokenType;

public interface AuthService {
    TokenResponse authenticate(
            AuthRequest authRequest
    );

    String validateToken(
            String token,
            TokenType type
    );

    TokenResponse createResponse(String email);

    String encodePassword(
            String password
    );

    String encodeUserData(
            RegistrationRequest request
    );

    RegistrationRequest decodeUserDate(
            String date
    );

    String generateToken(
            String email,
            TokenType tokenType
    );
}
