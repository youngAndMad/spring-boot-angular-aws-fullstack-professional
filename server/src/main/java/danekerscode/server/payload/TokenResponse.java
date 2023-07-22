package danekerscode.server.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TokenResponse(
        LocalDateTime givenAt,
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("access_token_expiration")
        Integer accessTokenExpiration,
        @JsonProperty("refresh_token")
        String refreshToken,
        @JsonProperty("refresh_token_expiration")
        Integer refreshTokenExpiration
) {
}
