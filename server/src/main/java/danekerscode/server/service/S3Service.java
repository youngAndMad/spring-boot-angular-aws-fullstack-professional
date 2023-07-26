package danekerscode.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    Boolean uploadFile(MultipartFile file, Integer id, String fileName);

    Object downloadFile(String fileName);

    Boolean deleteFile(String fileName, Integer id);
}
