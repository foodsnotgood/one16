package be.johannesroeder.sixletterapi.helpers;


import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static be.johannesroeder.sixletterapi.helpers.FileToListConverter.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileToListConverterTest {

    @Test
    public void firstTest() {
        MockMultipartFile file = buildMockFileWithWords("testing\nthe\nconverter");
        List<String> testList = Arrays.asList("testing", "the", "converter");
        List<String> convertedList = convertToList(file);
        assertEquals(testList, convertedList);
    }

    @Test
    public void emptyFile() {
        MockMultipartFile file = buildMockFileWithWords("");
        List<String> convertedList = convertToList(file);
        assertEquals(0, convertedList.size());
    }

    @Test
    public void one_word_more_then_6() {
        MockMultipartFile file = buildMockFileWithWords("testers");
        List<String> convertedList = separateSixLetterWords(file);
        assertEquals(0, convertedList.size());
    }

    @Test
    public void separate_6letter_words_two_6letter_words() {
        MockMultipartFile file = buildMockFileWithWords("a\nan\nand\nabroad\naccept");
        List<String> testList = Arrays.asList("abroad", "accept");
        List<String> sixLetterWords = separateSixLetterWords(file);
        assertEquals(testList, sixLetterWords);
    }

    @Test
    public void separate_6letter_words_no_6letter_words() {
        MockMultipartFile file = buildMockFileWithWords("a\nan\nand\nsuper\nnice\nheavy\nduty\nhome\nwork");
        List<String> sixLetterWords = separateSixLetterWords(file);
        assertEquals(0, sixLetterWords.size());
    }

    @Test
    public void separate_6letter_words_only_6letter_words() {
        MockMultipartFile file = buildMockFileWithWords("androi\ndandyl\nandrey\nsuperb\nnicest\nhealth\nduties\nhomesy\nworker");
        List<String> testList = Arrays.asList("androi", "dandyl", "andrey", "superb", "nicest", "health", "duties", "homesy", "worker");
        List<String> sixLetterWords = separateSixLetterWords(file);
        assertEquals(testList, sixLetterWords);
    }

    @Test
    public void separate_non_6letter_words() {
        MockMultipartFile file = buildMockFileWithWords("a\nan\nand\nabroad\naccept");
        List<String> testList = Arrays.asList("a", "an", "and");
        List<String> nonSixLetterWords = separateNonSixLetterWords(file);
        assertEquals(testList, nonSixLetterWords);
    }

    @Test
    public void one_word_less_then_6() {
        MockMultipartFile file = buildMockFileWithWords("test");
        List<String> convertedList = separateNonSixLetterWords(file);
        List<String> mockList = List.of("test");
        assertEquals(mockList, convertedList);
    }

    @Test
    public void separate_non_6letters_with_empty_file() {
        MockMultipartFile file = buildMockFileWithWords("");
        List<String> convertedList = separateNonSixLetterWords(file);
        assertEquals(0, convertedList.size());
    }

    @Test
    public void mapNon6LetterWords() {
        List<String> non6Letters = Arrays.asList("a","b","cc","dd","eee","ffff","ggggg");
        HashMap<Integer, List<String>> map = mapNonSixLetters(non6Letters);
        HashMap<Integer, List<String>> expectedMap = new HashMap<>();
        expectedMap.put(1, Arrays.asList("a", "b"));
        expectedMap.put(2, Arrays.asList("cc", "dd"));
        expectedMap.put(3, List.of("eee"));
        expectedMap.put(4, List.of("ffff"));
        expectedMap.put(5, List.of("ggggg"));
        assertEquals(expectedMap, map);
    }

    @Test
    public void mapNon6LetterWords_emptyList() {
        List<String> emptyList = new ArrayList<>();
        HashMap<Integer, List<String>> map = mapNonSixLetters(emptyList);
        assertTrue(map.isEmpty());
    }

    @Test
    public void mapNon6LetterWords_ListWithEmptyStrings(){
        List<String> emptyStringsList = Arrays.asList("", "", "", "", "", "");
        HashMap<Integer, List<String>> map = mapNonSixLetters(emptyStringsList);
        assertTrue(map.isEmpty());
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
