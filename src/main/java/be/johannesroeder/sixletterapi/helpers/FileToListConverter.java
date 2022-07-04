package be.johannesroeder.sixletterapi.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileToListConverter {

    private static final int MAX_LENGTH = 6;
    private static final Logger logger = LoggerFactory.getLogger(FileToListConverter.class);
    public static List<String> convertToList(MultipartFile file) {
        List<String> list = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .forEach(list::add);
        } catch (IOException e) {
            logger.error("Something went wrong while reading the file. Details: " + e.getMessage());
        }
        return list;
    }

    public static List<String> separateMaxLetterWords(MultipartFile file) {
        return convertToList(file).stream().filter(word -> word.length() == MAX_LENGTH).toList();
    }

    public static List<String> separateNonMaxLetterWords(MultipartFile file) {
        return convertToList(file).stream().filter(word -> word.length() < MAX_LENGTH).toList();
    }

    public static HashMap<Integer, List<String>> mapByLength(List<String> nonMaxLetters){
        var map = new HashMap<Integer, List<String>>();
        if (nonMaxLetters.isEmpty()) return map;
        int longestAmountChar = nonMaxLetters.stream().sorted().toList().get(nonMaxLetters.size()-1).length();
        for (int i = 1; i <= longestAmountChar; i++) {
            int finalI = i;
            map.put(i, nonMaxLetters.stream().filter(w -> w.length() == finalI).toList());
        }
        return map;
    }

    public static List<String> findValidCombinations(List<String> validList, HashMap<Integer, List<String>> mapByLength) {
        List<String> combinedWords = new ArrayList<>(validList.size());
        mapByLength.forEach((k, list) -> {
            List<String> matchingList = mapByLength.get(MAX_LENGTH - k);
            list.forEach(s1 -> {
                matchingList.forEach(s2 -> {
                    if (validList.contains(s1 + s2)) combinedWords.add(s1+s2);
                });
            });
        });
        return combinedWords;
    }
}
