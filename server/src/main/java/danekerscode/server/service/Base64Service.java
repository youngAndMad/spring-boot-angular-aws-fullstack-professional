package danekerscode.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import danekerscode.server.payload.RegistrationRequest;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Base64Service {



    public  String encodeUserData(RegistrationRequest userData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(userData);
        return Base64.getEncoder().encodeToString(jsonData.getBytes());
    }

    public  RegistrationRequest decodeUserData(String encodedUserData) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedUserData);
        String jsonData = new String(decodedBytes);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonData, RegistrationRequest.class);
    }


}
