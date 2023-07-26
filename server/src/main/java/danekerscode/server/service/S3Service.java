package danekerscode.server.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {

    @Value("${cloud.aws.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3;
    private final UserService userService;
    private final AmazonFileService amazonFileService;


    public Boolean uploadFile(MultipartFile file, Integer id, String fileName) {
        var user = userService.getUser(id);
        amazonFileService.save(user, fileName);
        var fileToUpload = convertMultiPartFileToFile(file);
        s3.putObject(new PutObjectRequest(bucketName, fileName, fileToUpload));
        fileToUpload.delete();
        return Boolean.TRUE;
    }


    @SneakyThrows
    public Object downloadFile(String fileName) {
        S3Object object = s3.getObject(bucketName, fileName);
        S3ObjectInputStream s3is = object.getObjectContent();
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        byte[] read_buf = new byte[1024];
        int read_len;
        while ((read_len = s3is.read(read_buf)) > 0) {
            fileOutputStream.write(read_buf, 0, read_len);
        }
        Path pathObject = Paths.get(fileName);
        return new UrlResource(pathObject.toUri());
    }

    public Boolean deleteFile(String fileName, Integer id) {
        s3.deleteObject(bucketName, fileName);
        amazonFileService.deleteById(id);
        return Boolean.TRUE;
    }

    public static File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }


//    public static String convertPdfToHtml(InputStream inputStream) throws IOException {
//        try (PDDocument document = PDDocument.load(inputStream)) {
//            PDFTextStripper textStripper = new PDFTextStripper();
//            String pdfText = textStripper.getText(document);
//
//            // Convert PDF text to HTML
//            Document htmlDocument = Jsoup.parse(pdfText);
//            return htmlDocument.html();
//        }
//    }


}
