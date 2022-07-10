package be.johannesroeder.sixletterapi.controllers;

import be.johannesroeder.sixletterapi.converter.ItoListConverter;
import be.johannesroeder.sixletterapi.factory.ToListConverterFactory;
import be.johannesroeder.sixletterapi.utility.FileUtils;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.apache.commons.io.FilenameUtils;
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

import static be.johannesroeder.sixletterapi.utility.TheFunPartUtils.*;

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
            InputWrapper input = new InputWrapper(FileUtils.multipartToFile(file));
            String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
            var converter = ToListConverterFactory.getConverter(fileType, input);
            convertedList = converter.convertToList();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return getValidCombinations(convertedList);
    }

    @PostMapping(value = "/api/string")
    public List<String> apiFile(@RequestParam String inputText) {
        List<String> convertedList;
        try {
            InputWrapper input = new InputWrapper(inputText);
            var converter = ToListConverterFactory.getConverter("string", input);
            convertedList = converter.convertToList();
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return getValidCombinations(convertedList);
    }

    private List<String> getValidCombinations(List<String> list){
        var sixLetters = separateMaxLetterWords(list);
        var mapByLength = mapByLength(separateNonMaxLetterWords(list));
        return findValidCombinations(sixLetters, mapByLength);
    }
}
