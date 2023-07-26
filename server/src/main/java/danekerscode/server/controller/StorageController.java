package danekerscode.server.controller;


import danekerscode.server.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
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
        byte[] content = this.service.downloadFile(fileName);
        return ResponseEntity
                .ok()
                .contentLength(content.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(new ByteArrayResource(content));
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
