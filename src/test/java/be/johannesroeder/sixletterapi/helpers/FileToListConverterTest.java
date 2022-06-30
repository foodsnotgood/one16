package be.johannesroeder.sixletterapi.helpers;


import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileToListConverterTest {

    MockMultipartFile file;

    @Test
    public void firstTest() {
        file = buildMockFileWithWords("testing\nthe\nconverter");
        List<String> convertedList = FileToListConverter.convertToList(file);
        assertEquals(3, convertedList.size());
    }

    @Test
    public void separate_6letter_words_two_6letter_words() {
        file = buildMockFileWithWords("a\nan\nand\nabroad\naccept");
        List<String> sixLetterWords = FileToListConverter.separateSixLetterWords(file);
        System.out.println(sixLetterWords);
        assertEquals(2, sixLetterWords.size());
    }

    @Test
    public void separate_6letter_words_no_6letter_words() {
        file = buildMockFileWithWords("a\nan\nand\nsuper\nnice\nheavy\nduty\nhome\nwork");
        List<String> sixLetterWords = FileToListConverter.separateSixLetterWords(file);
        assertEquals(0, sixLetterWords.size());
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
