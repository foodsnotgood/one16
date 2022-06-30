package be.johannesroeder.sixletterapi.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileToListConverter {

    private static final Logger logger = LoggerFactory.getLogger(FileToListConverter.class);
    public static List<String> convertToList(MultipartFile file) {
        List<String> list = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .forEach(list::add);
        } catch (IOException e) {
            logger.error("Something went wrong while reading the file. Details: " + e.getMessage());
        }
        return list;
    }
    //seperate all 6 letter words from non 6 letter words

}
