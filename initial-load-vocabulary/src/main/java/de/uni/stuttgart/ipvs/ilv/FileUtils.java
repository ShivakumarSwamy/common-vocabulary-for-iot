package de.uni.stuttgart.ipvs.ilv;

import lombok.NonNull;

import java.io.*;

public class FileUtils {

    private FileUtils() {
        throw new IllegalStateException(getClass().getName());
    }


    public static String contentsAsStringFromFileInClasspath(@NonNull String filename) throws FileNotFoundException {

        StringBuilder content = new StringBuilder();

        try (InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(filename);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;

            while ((line = bufferedReader.readLine()) != null) content.append(line);

        } catch (IOException | NullPointerException failedFileRead) {
            throw new FileNotFoundException(filename + " COULD BE FOUND");
        }

        return content.toString();
    }
}

