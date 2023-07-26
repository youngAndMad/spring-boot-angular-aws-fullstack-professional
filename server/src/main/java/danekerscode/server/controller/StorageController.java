package danekerscode.server.controller;


import danekerscode.server.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/storage")
public class StorageController {

    private final S3Service service;

    @GetMapping("/download/{fileName}")
    ResponseEntity<?> getImage(
            @PathVariable String fileName
    ) {
        var file = service.downloadFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(file);
    }

    @PostMapping("/save")
    ResponseEntity<?> save(
            @RequestParam("id") Integer id,
            @RequestParam("fileName") String fileName,
            @RequestParam("file") MultipartFile file
    ) {
        return ResponseEntity.ok(service.uploadFile(file, id, fileName));
    }

    @DeleteMapping("{id}/{fileName}")
    ResponseEntity<?> delete(
            @PathVariable String fileName,
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(service.deleteFile(fileName,id));
    }
}
