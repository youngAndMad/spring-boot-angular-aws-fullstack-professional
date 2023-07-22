package danekerscode.server.controller;

import danekerscode.server.payload.RegistrationRequest;
import danekerscode.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<?> save(@RequestBody RegistrationRequest request) {
        userService.sendMail(request);
        return ResponseEntity.ok(true);
    }

    @GetMapping("{id}")
    ResponseEntity<?> find(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("{email}")
    ResponseEntity<?> getByToken(
            @PathVariable String email,
            @RequestParam("data") String data,
            @RequestParam("verification_token") String token
    ) {
        return ResponseEntity.status(201).body(userService.save(data));
    }

    @GetMapping
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(
            @PathVariable Integer id
    ) {
        userService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
