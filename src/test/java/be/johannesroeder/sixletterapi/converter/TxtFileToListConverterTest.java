package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.utility.FileUtils;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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
        TxtFileToListConverter converter = new TxtFileToListConverter();
        List<String> convertedList = converter.convertToList(new InputWrapper<>(FileUtils.multipartToFile(file)));
        List<String> testList = List.of("testing", "the", "converter");
        assertEquals(testList, convertedList);
    }

    @Test
    public void emptyFile() throws IOException {
        MockMultipartFile file = buildMockFileWithWords("");
        TxtFileToListConverter converter = new TxtFileToListConverter();
        List<String> convertedList = converter.convertToList(new InputWrapper<>(FileUtils.multipartToFile(file)));
        assertEquals(0, convertedList.size());
    }

    @Test
    public void throwIOErrorWhenInputIsNull() {
        var converter = new TxtFileToListConverter();
        var input = new InputWrapper<>(new File("testNull"));
        assertThrows(IOException.class, () -> converter.convertToList(input));
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