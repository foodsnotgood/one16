package be.johannesroeder.sixletterapi.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileToListConverter {

    private static final int COMBINATION_LENGTH = 6;
    private static final Logger logger = LoggerFactory.getLogger(FileToListConverter.class);

    public static List<String> separateMaxLetterWords(List<String> input) {
        return input.stream().filter(word -> word.length() == COMBINATION_LENGTH).toList();
    }

    public static List<String> separateNonMaxLetterWords(List<String> input) {
        return input.stream().filter(word -> word.length() < COMBINATION_LENGTH && word.length() > 0).toList();
    }

    public static HashMap<Integer, List<String>> mapByLength(List<String> nonMaxLetters){
        var map = new HashMap<Integer, List<String>>();
        if (nonMaxLetters.isEmpty()) return map;
        for (int i = 1; i < COMBINATION_LENGTH; i++) {
            int finalI = i;
            map.put(i, nonMaxLetters.stream().filter(w -> w.length() == finalI).distinct().toList());
        }
        return map;
    }

    public static List<String> findValidCombinations(List<String> validList, HashMap<Integer, List<String>> mapByLength) {
        List<String> combinedWords = new ArrayList<>(validList.size());
        mapByLength.forEach((k, list) -> {
            int i = k;
            List<String> matchingList = mapByLength.get((Integer) COMBINATION_LENGTH - k);
            list.forEach(s1 -> matchingList.stream().filter(s2 -> validList.contains(s1 + s2)).map(s2 -> s1 + s2).forEach(combinedWords::add));
        });
        return combinedWords;
    }
}
