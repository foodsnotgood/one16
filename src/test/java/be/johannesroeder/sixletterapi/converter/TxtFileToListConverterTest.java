package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static be.johannesroeder.sixletterapi.converter.TxtFileToListConverter.*;

class TxtFileToListConverterTest {

    @Test
    void convertToListTest() throws IOException {
        MockMultipartFile file = buildMockFileWithWords("testing\nthe\nconverter");
        TxtFileToListConverter converter = new TxtFileToListConverter(new InputWrapper(file));
        List<String> convertedList = converter.convertToList();
        List<String> testList = List.of("testing", "the", "converter");
        assertEquals(testList, convertedList);
    }

    @Test
    public void emptyFile() throws IOException {
        MockMultipartFile file = buildMockFileWithWords("");
        TxtFileToListConverter converter = new TxtFileToListConverter(new InputWrapper(file));
        List<String> convertedList = converter.convertToList();
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