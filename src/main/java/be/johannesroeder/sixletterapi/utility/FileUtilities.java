package be.johannesroeder.sixletterapi.utility;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtilities {
    public static File multipartToFile(MultipartFile multipartFile, String extension) throws IOException {
        File convertedFile = new File(System.getProperty("java.io.tmpdir")
                +"/"
                + RandomStringUtils.random(10, true, true)
                + "."
                + extension);
        multipartFile.transferTo(convertedFile);
        return convertedFile;
    }
}
