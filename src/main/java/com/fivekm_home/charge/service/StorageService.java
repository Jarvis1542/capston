package com.fivekm_home.charge.service;

import com.fivekm_home.charge.config.StorageConfig;
import com.fivekm_home.charge.exception.StorageException;
import com.fivekm_home.charge.exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class StorageService {
    private final Path rootLocation;
    @Autowired
    public StorageService(StorageConfig properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("빈 파일: " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "파일 이름 오류 " + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("통과함");
        }
        catch (IOException e) {
            throw new StorageException("저장 실패 " + filename, e);
        }
        return "file name : " + filename + " 저장 성공";
    }

    public Stream<Path> loadAll() {
        try {
            System.out.println("rootLocation : " + this.rootLocation);
            System.out.println("Files.walk : " + Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize));
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    // 저장된 폴더에서 파일 들고오기
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            System.out.println("resource : "  + resource);
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}