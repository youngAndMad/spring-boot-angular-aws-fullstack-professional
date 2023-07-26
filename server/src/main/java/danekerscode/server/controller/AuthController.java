package danekerscode.server.controller;

import danekerscode.server.payload.AuthRequest;
import danekerscode.server.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static danekerscode.server.utils.TokenType.REFRESH;

@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
@RestController
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @PostMapping
    ResponseEntity<?> authenticate(
            @RequestBody AuthRequest authRequest
    ) {
        return ResponseEntity.ok(authServiceImpl.authenticate(authRequest));
    }

    @PostMapping("refresh-token")
    ResponseEntity<?> refresh(
            @RequestHeader("refresh-token") String refreshToken
    ){
        var email = authServiceImpl.validateToken(refreshToken, REFRESH);
        return ResponseEntity.ok(authServiceImpl.createResponse(email));
    }


}
