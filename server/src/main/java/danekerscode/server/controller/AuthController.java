package danekerscode.server.controller;

import danekerscode.server.payload.AuthRequest;
import danekerscode.server.service.AuthService;
import danekerscode.server.utils.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static danekerscode.server.utils.TokenType.REFRESH;

@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping
    ResponseEntity<?> authenticate(
            @RequestBody AuthRequest authRequest
    ) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

    @PostMapping("refresh-token")
    ResponseEntity<?> refresh(
            @RequestHeader("refresh-token") String refreshToken
    ){
        var email = authService.validateToken(refreshToken, REFRESH);
        return ResponseEntity.ok(authService.createResponse(email));
    }


}
