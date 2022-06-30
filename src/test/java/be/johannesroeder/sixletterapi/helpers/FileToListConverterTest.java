package be.johannesroeder.sixletterapi.helpers;


import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileToListConverterTest {

    MockMultipartFile file;

    @Test
    public void firstTest() {
        file = new MockMultipartFile(
                "file",
                "mock.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "testing\nthe\nconverter".getBytes()
        );

        List<String> convertedList = FileToListConverter.convertToList(file);
        assertEquals(3, convertedList.size());
    }
}
