package danekerscode.server.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import danekerscode.server.payload.RegistrationRequest;
import danekerscode.server.service.Base64Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class Base64ServiceImpl implements Base64Service {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public  String encodeUserData(RegistrationRequest userData) {
        String jsonData = objectMapper.writeValueAsString(userData);
        return Base64.getEncoder().encodeToString(jsonData.getBytes());
    }

    @SneakyThrows
    public  RegistrationRequest decodeUserData(String encodedUserData){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedUserData);
        String jsonData = new String(decodedBytes);
        return objectMapper.readValue(jsonData, RegistrationRequest.class);
    }


}
