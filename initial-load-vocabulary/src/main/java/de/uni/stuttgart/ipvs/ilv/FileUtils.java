package de.uni.stuttgart.ipvs.ilv;

import lombok.NonNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    private FileUtils() {
        throw new IllegalStateException(getClass().getName());
    }


    public static String contentsAsStringFromFileInClasspath(@NonNull String filename) throws IOException {

        try {
            URL fileUrl = getResourceUrlFromClasspath(filename);
            Path filePath = getPathFromUrl(fileUrl);
            return Files.readString(filePath);

        } catch (URISyntaxException | NullPointerException failedFileUrl) {
            throw new FileNotFoundException(filename + " COULD NOT BE FOUND IN CLASSPATH");
        }

    }

    private static Path getPathFromUrl(URL url) throws URISyntaxException {
        URI resourceURI = url.toURI();
        return Paths.get(resourceURI);
    }

    private static URL getResourceUrlFromClasspath(String resourceName) {
        return FileUtils.class.getClassLoader().getResource(resourceName);
    }

}
