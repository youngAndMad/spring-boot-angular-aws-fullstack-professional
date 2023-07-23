package danekerscode.server.controller;

import danekerscode.server.payload.RegistrationRequest;
import danekerscode.server.service.AuthService;
import danekerscode.server.service.UserService;
import danekerscode.server.utils.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping
    ResponseEntity<?> save(
            @RequestBody RegistrationRequest request
    ) {
        userService.sendMail(request);
        return ResponseEntity.ok(true);
    }

    @GetMapping("find")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> find(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("verify")
    ResponseEntity<?> getByToken(
            @RequestParam("data") String data,
            @RequestParam("verification_token") String token
    ) {
        System.out.println(authService.validateToken(token, TokenType.VERIFICATION));
        return ResponseEntity.status(201).body(userService.save(data));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> delete(
            @PathVariable Integer id
    ) {
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
