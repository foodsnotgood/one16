package be.johannesroeder.sixletterapi.converter;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

class TxtFileToListConverterTest {

    @Test
    void convertToListTest() {
        MockMultipartFile file = buildMockFileWithWords("testing\nthe\nconverter");
        List<String> testList = List.of("testing", "the", "converter");
        List<String> convertedList = convertToList(file);
        assertEquals(testList, convertedList);
    }

    @Test
    public void emptyFile() {
        MockMultipartFile file = buildMockFileWithWords("");
        List<String> convertedList = convertToList(file);
        assertEquals(0, convertedList.size());
    }

    private MockMultipartFile buildMockFileWithWords(String words){
        return new MockMultipartFile(
                "file",
                "mock.txt",
                MediaType.TEXT_PLAIN_VALUE,
                words.getBytes()
        );
    }
}