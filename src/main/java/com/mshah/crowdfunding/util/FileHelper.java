package com.mshah.crowdfunding.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class FileHelper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
            .withZone(ZoneOffset.UTC);


    public Optional<String> uploadFile(MultipartFile file, String baseDir) {
        log.info("FileUtility.uploadFile.start: uploading file");

        if (file.isEmpty()) {
            log.info("FileUtility.uploadFile: File is not present");
            return Optional.empty();
        }

        try {
            var destinationDir = Paths.get(baseDir).normalize();
            if (!Files.exists(destinationDir)) {
                Files.createDirectories(destinationDir);
            }

            var fileName = generateFileName(file);
            var destinationFilePath = destinationDir.resolve(fileName).normalize();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }

            log.info("""
                    FileUtility.uploadFile: uploaded file successfully, name :: {}, size :: {}
                    """, fileName, file.getBytes().length);

            return Optional.of(fileName);
        } catch (IOException e) {
            log.error("FileUtility.uploadFile.error: Error while uploading file", e);
            throw new RuntimeException("Error while uploading file", e);
        }

    }

    private String generateFileName(MultipartFile file) {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String baseName = StringUtils.stripFilenameExtension(originalFileName);
        String extension = StringUtils.getFilenameExtension(originalFileName);

        String timestamp = formatter.format(Instant.now());

        return baseName + "_" + timestamp + "." + extension;
    }
}
