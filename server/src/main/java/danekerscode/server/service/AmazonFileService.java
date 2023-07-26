package danekerscode.server.service;

import danekerscode.server.model.User;

public interface AmazonFileService {
    void deleteById(Integer id);
    void save(User user, String name);
}
