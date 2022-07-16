package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.expeptions.EmptyInputException;
import be.johannesroeder.sixletterapi.expeptions.WrongDelimiterException;
import be.johannesroeder.sixletterapi.utility.FileUtils;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvFileToListConverterTest {

    @Test
    void convertToListTest() throws IOException {
        MockMultipartFile file = buildMockFileWithWords("1;testing\n2;the\n3;converter");
        CsvFileToListConverter converter = new CsvFileToListConverter();
        List<String> convertedList = converter.convertToList(new InputWrapper<>(FileUtils.multipartToFile(file)));
        List<String> testList = List.of("testing", "the", "converter");
        assertEquals(testList, convertedList);
    }

    @Test
    public void throwEmptyInputExceptionWhenInputIsEmpty() throws IOException {
        MockMultipartFile multipartFile = buildMockFileWithWords("");
        var converter = new CsvFileToListConverter();
        var input = new InputWrapper<>(FileUtils.multipartToFile(multipartFile));
        assertThrows(EmptyInputException.class, () -> converter.convertToList(input));

    }

    @Test
    public void throwIOErrorWhenInputIsNull() {
        var converter = new CsvFileToListConverter();
        var input = new InputWrapper<>(new File("testNull"));
        assertThrows(FileNotFoundException.class, () -> converter.convertToList(input));
    }

    @Test
    public void throwIOErrorWhenDelimiterIsUnexpected() throws IOException {
        MockMultipartFile multipartFile = buildMockFileWithWords("1,testing\n2,the\n3,converter");
        var converter = new CsvFileToListConverter();
        var input = new InputWrapper<>(FileUtils.multipartToFile(multipartFile));
        assertThrows(WrongDelimiterException.class, () -> converter.convertToList(new InputWrapper<>(FileUtils.multipartToFile(multipartFile))));
    }

    private MockMultipartFile buildMockFileWithWords(String words){
        return new MockMultipartFile(
                "file",
                "mock.csv",
                MediaType.TEXT_PLAIN_VALUE,
                words.getBytes()
        );
    }
}