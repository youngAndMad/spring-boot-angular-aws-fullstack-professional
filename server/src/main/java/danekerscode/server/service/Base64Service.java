package danekerscode.server.service;

import danekerscode.server.payload.RegistrationRequest;

public interface Base64Service {
    RegistrationRequest decodeUserData(String encodedUserData);

    String encodeUserData(RegistrationRequest userData);
}
