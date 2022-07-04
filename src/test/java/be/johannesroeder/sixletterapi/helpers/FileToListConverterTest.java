package be.johannesroeder.sixletterapi.helpers;


import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static be.johannesroeder.sixletterapi.helpers.FileToListConverter.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileToListConverterTest {

    @Test
    public void firstTest() {
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

    @Test
    public void one_word_more_then_6() {
        MockMultipartFile file = buildMockFileWithWords("testers");
        List<String> convertedList = separateMaxLetterWords(file);
        assertEquals(0, convertedList.size());
    }

    @Test
    public void separate_6letter_words_two_6letter_words() {
        MockMultipartFile file = buildMockFileWithWords("a\nan\nand\nabroad\naccept");
        List<String> testList = List.of("abroad", "accept");
        List<String> sixLetterWords = separateMaxLetterWords(file);
        assertEquals(testList, sixLetterWords);
    }

    @Test
    public void separate_6letter_words_no_6letter_words() {
        MockMultipartFile file = buildMockFileWithWords("a\nan\nand\nsuper\nnice\nheavy\nduty\nhome\nwork");
        List<String> sixLetterWords = separateMaxLetterWords(file);
        assertEquals(0, sixLetterWords.size());
    }

    @Test
    public void separate_6letter_words_only_6letter_words() {
        MockMultipartFile file = buildMockFileWithWords("androi\ndandyl\nandrey\nsuperb\nnicest\nhealth\nduties\nhomesy\nworker");
        List<String> testList = List.of("androi", "dandyl", "andrey", "superb", "nicest", "health", "duties", "homesy", "worker");
        List<String> sixLetterWords = separateMaxLetterWords(file);
        assertEquals(testList, sixLetterWords);
    }

    @Test
    public void separate_non_6letter_words() {
        MockMultipartFile file = buildMockFileWithWords("a\nan\nand\nabroad\naccept");
        List<String> testList = List.of("a", "an", "and");
        List<String> nonMaxLetterWords = separateNonMaxLetterWords(file);
        assertEquals(testList, nonMaxLetterWords);
    }

    @Test
    public void one_word_less_then_6() {
        MockMultipartFile file = buildMockFileWithWords("test");
        List<String> convertedList = separateNonMaxLetterWords(file);
        List<String> mockList = List.of("test");
        assertEquals(mockList, convertedList);
    }

    @Test
    public void separate_non_6letters_with_empty_file() {
        MockMultipartFile file = buildMockFileWithWords("");
        List<String> convertedList = separateNonMaxLetterWords(file);
        assertEquals(0, convertedList.size());
    }

    @Test
    public void mapByLengthTest() {
        List<String> non6Letters = List.of("a","b","cc","dd","eee","ffff","ggggg");
        HashMap<Integer, List<String>> map = mapByLength(non6Letters);
        HashMap<Integer, List<String>> expectedMap = new HashMap<>();
        expectedMap.put(1, List.of("a", "b"));
        expectedMap.put(2, List.of("cc", "dd"));
        expectedMap.put(3, List.of("eee"));
        expectedMap.put(4, List.of("ffff"));
        expectedMap.put(5, List.of("ggggg"));
        assertEquals(expectedMap, map);
    }

    @Test
    public void mapByLength_emptyList() {
        List<String> emptyList = new ArrayList<>();
        HashMap<Integer, List<String>> map = mapByLength(emptyList);
        assertTrue(map.isEmpty());
    }

    @Test
    public void mapByLength_ListWithEmptyStrings(){
        List<String> emptyStringsList = List.of("", "", "", "", "", "");
        HashMap<Integer, List<String>> map = mapByLength(emptyStringsList);
        assertTrue(map.isEmpty());
    }

    @Test
    public void findValidCombinationsTest(){
        List<String> validList = Stream.of("abroad", "domain", "enable", "driver", "engage").sorted().toList();
        HashMap<Integer, List<String>> mapByLength = new HashMap<>();
        mapByLength.put(1, List.of("a", "d"));
        mapByLength.put(2,List.of("en", "dr"));
        mapByLength.put(3, List.of("eng", "age"));
        mapByLength.put(4, List.of("able", "iver"));
        mapByLength.put(5, List.of("broad", "omain"));
        List<String> computedList = findValidCombinations(validList, mapByLength);
        System.out.println(computedList);
        assertEquals(validList, computedList.stream().sorted().toList());
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
