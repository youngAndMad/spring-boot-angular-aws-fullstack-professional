package danekerscode.server.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenType {
    ACCESS(10),
    VERIFICATION(1440),
    REFRESH(60);

    private final Integer expiration;
}
