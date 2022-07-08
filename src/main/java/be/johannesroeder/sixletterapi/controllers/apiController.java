package be.johannesroeder.sixletterapi.controllers;

import be.johannesroeder.sixletterapi.converter.ItoListConverter;
import be.johannesroeder.sixletterapi.factory.ToListConverterFactory;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.apache.tika.Tika;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static be.johannesroeder.sixletterapi.helpers.FileToListConverter.*;

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


    @PostMapping(path = "/api/file")
    public List<String> apiFile(@RequestParam MultipartFile file){
        if (file.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The submitted file seems to be empty");
        List<String> convertedList;
        try {
            Tika tika = new Tika();
            String fileType = tika.detect(file.getBytes());
            System.out.println(fileType);
            InputWrapper input = new InputWrapper(file);
            ItoListConverter converter = ToListConverterFactory.getConverter(fileType, input);
            convertedList = converter.convertToList();
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Something went wrong trying to reading the file"
            );
        }
        List<String> sixLetters = separateMaxLetterWords(convertedList);
        List<String> nonSixLetters = separateNonMaxLetterWords(convertedList);
        HashMap<Integer, List<String>> map = mapByLength(nonSixLetters);
        return findValidCombinations(sixLetters, map);
    }

//    @PostMapping(value = "/api/file")
//    public List<String> apiFile(@RequestParam String words){
//        return new ArrayList<>();
//    }
}
