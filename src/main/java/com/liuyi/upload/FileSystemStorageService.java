package com.liuyi.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path fileRootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties prop) {
        this.fileRootLocation = Paths.get(prop.getLocation());
    }


    @Override
    public void init() {
        try {
            Files.createDirectories(fileRootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file " + filename);
        }
        if (filename.contains("..")) {
            throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
        }
        try {
            Files.copy(file.getInputStream(), fileRootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(fileRootLocation, 1).filter(path -> !path.equals(this.fileRootLocation)).map(path -> this.fileRootLocation.relativize(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored file list", e);
        }
    }

    @Override
    public Path load(String filename) {
        return fileRootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            UrlResource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file :" + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file :" + filename);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(fileRootLocation.toFile());
    }

    public static void main(String[] args) {
        Path path = Paths.get("..");
        System.out.println(path.normalize().toAbsolutePath());

    }
}
