package danekerscode.server.service;

import danekerscode.server.jwt.JwtService;
import danekerscode.server.payload.AuthRequest;
import danekerscode.server.payload.RegistrationRequest;
import danekerscode.server.payload.TokenResponse;
import danekerscode.server.utils.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static danekerscode.server.utils.AppConstants.ACCESS_TOKEN_EXPIRATION;
import static danekerscode.server.utils.AppConstants.REFRESH_TOKEN_EXPIRATION;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final Base64Service base64Service;


    public TokenResponse authenticate(
            AuthRequest authRequest
    ) {
        Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password())
                );
        if (authenticate.isAuthenticated()) {
            return createResponse(authRequest.email());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    public String validateToken(
            String token,
            TokenType type
    ) {
        return jwtService.validateToken(token, type);
    }

    public TokenResponse createResponse(String email) {
        return TokenResponse.builder()
                .accessTokenExpiration(ACCESS_TOKEN_EXPIRATION)
                .refreshTokenExpiration(REFRESH_TOKEN_EXPIRATION)
                .accessToken(jwtService.generateToken(email, TokenType.ACCESS))
                .refreshToken(jwtService.generateToken(email, TokenType.REFRESH))
                .build();
    }

    public String encodePassword(
            String password
    ) {
        return passwordEncoder.encode(password);
    }

    @SneakyThrows
    public String encodeUserData(
            RegistrationRequest request
    ) {
        return base64Service.encodeUserData(request);
    }

    public RegistrationRequest decodeUserDate(
            String date
    ) throws Exception {
        return base64Service.decodeUserData(date);
    }

    public String generateToken(
            String email,
            TokenType tokenType
    ) {
        return jwtService.generateToken(email, tokenType);
    }

}
