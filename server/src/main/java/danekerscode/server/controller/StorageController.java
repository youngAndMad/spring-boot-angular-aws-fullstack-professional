package danekerscode.server.controller;


import danekerscode.server.service.S3Service;
import danekerscode.server.service.impl.S3ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/storage")
public class StorageController {

    private final S3Service s3Service;

    @GetMapping("/download/{fileName}")
    ResponseEntity<?> getImage(
            @PathVariable String fileName
    ) {
        var file = s3Service.downloadFile(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(file);
    }

    @PostMapping("/save")
    ResponseEntity<?> save(
            @RequestParam("id") Integer id,
            @RequestParam("fileName") String fileName,
            @RequestParam("file") MultipartFile file
    ) {
        return ResponseEntity.ok(s3Service.uploadFile(file, id, fileName));
    }

    @DeleteMapping("{id}/{fileName}")
    ResponseEntity<?> delete(
            @PathVariable String fileName,
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(s3Service.deleteFile(fileName,id));
    }
}
