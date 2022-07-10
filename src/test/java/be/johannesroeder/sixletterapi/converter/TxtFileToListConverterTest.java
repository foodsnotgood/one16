package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.utility.FileUtils;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TxtFileToListConverterTest {

    @Test
    void convertToListTest() throws IOException {
        MockMultipartFile file = buildMockFileWithWords("testing\nthe\nconverter");
        TxtFileToListConverter converter = new TxtFileToListConverter(new InputWrapper(FileUtils.multipartToFile(file)));
        List<String> convertedList = converter.convertToList();
        List<String> testList = List.of("testing", "the", "converter");
        assertEquals(testList, convertedList);
    }

    @Test
    public void emptyFile() throws IOException {
        MockMultipartFile file = buildMockFileWithWords("");
        TxtFileToListConverter converter = new TxtFileToListConverter(new InputWrapper(FileUtils.multipartToFile(file)));
        List<String> convertedList = converter.convertToList();
        assertEquals(0, convertedList.size());
    }

    @Test
    public void throwIOErrorWhenInputIsNull(){
        TxtFileToListConverter converter = new TxtFileToListConverter(new InputWrapper(new File("random")));
        assertThrows(IOException.class, converter::convertToList);
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