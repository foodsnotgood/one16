package be.johannesroeder.sixletterapi.controllers;

import static be.johannesroeder.sixletterapi.helpers.FileToListConverter.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

@RestController
public class apiController {

    @GetMapping("test")
    public String test(){
        return "it is working";
    }

    @PostMapping(value = "/api")
    public List<String> api(@RequestParam MultipartFile file) throws IOException {
        List<String> list = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .forEach(list::add);
        return list;
    }

    @PostMapping(value = "/api/file")
    public List<String> apiFile(@RequestParam MultipartFile file){
        List<String> sixLetters = separateMaxLetterWords(file);
        List<String> nonSixLetters = separateNonMaxLetterWords(file);
        HashMap<Integer, List<String>> map = mapByLength(nonSixLetters);
        return findValidCombinations(sixLetters, map);
    }
}
