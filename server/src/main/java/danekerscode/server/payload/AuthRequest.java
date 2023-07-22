package danekerscode.server.payload;


public record AuthRequest(
        String email,
        String password
) {
}
