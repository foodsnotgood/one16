package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.expeptions.EmptyInputException;
import be.johannesroeder.sixletterapi.expeptions.WrongDelimiterException;
import be.johannesroeder.sixletterapi.utility.FileUtils;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileToListConverterTest {

    @Test
    void convertToListTest() throws IOException {
        MockMultipartFile file = buildMockFileWithWords("1;testing\n2;the\n3;converter");
        CsvFileToListConverter converter = new CsvFileToListConverter(new InputWrapper(FileUtils.multipartToFile(file)));
        List<String> convertedList = converter.convertToList();
        List<String> testList = List.of("testing", "the", "converter");
        assertEquals(testList, convertedList);
    }

    @Test
    public void throwEmptyInputExceptionWhenInputIsEmpty() throws IOException {
        MockMultipartFile file = buildMockFileWithWords("");
        CsvFileToListConverter converter = new CsvFileToListConverter(new InputWrapper(FileUtils.multipartToFile(file)));
        assertThrows(EmptyInputException.class, converter::convertToList);
    }

    @Test
    public void throwIOErrorWhenInputIsNull(){
        CsvFileToListConverter converter = new CsvFileToListConverter(new InputWrapper(new File("random")));
        assertThrows(IOException.class, converter::convertToList);
    }

    @Test
    public void throwIOErrorWhenDelimiterIsUnexpected() throws IOException {
        MockMultipartFile file = buildMockFileWithWords("1,testing\n2,the\n3,converter");
        CsvFileToListConverter converter = new CsvFileToListConverter(new InputWrapper(FileUtils.multipartToFile(file)));
        assertThrows(WrongDelimiterException.class, converter::convertToList);
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