package be.johannesroeder.sixletterapi.controllers;

import be.johannesroeder.sixletterapi.converter.ItoListConverter;
import be.johannesroeder.sixletterapi.factory.ToListConverterFactory;
import be.johannesroeder.sixletterapi.model.Word;
import be.johannesroeder.sixletterapi.repository.WordRepository;
import be.johannesroeder.sixletterapi.utility.FileUtils;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static be.johannesroeder.sixletterapi.utility.TheFunPartUtils.*;

@RestController
public class apiController {

    @Autowired
    private WordRepository repository;

    @PostMapping(path = "/api/file")
    public List<String> apiFile(@RequestParam MultipartFile file){
        if (file.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The submitted file seems to be empty");
        List<String> convertedList;

        try {
            File tmpFile = FileUtils.multipartToFile(file);
            InputWrapper<File> input = new InputWrapper<>(tmpFile);
            String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
            var converter = ToListConverterFactory.getConverter(fileType);
            convertedList = converter.convertToList(input);
            tmpFile.delete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        List<String> validList = getValidCombinations(convertedList);
        validList.forEach(w -> repository.save(new Word(w)));

        return validList;
    }

    @PostMapping(value = "/api/string")
    public List<String> apiString(@RequestBody String inputText) {
        List<String> convertedList;
        try {
            InputWrapper<String> input = new InputWrapper<>(inputText);
            var converter = ToListConverterFactory.getConverter("string");
            convertedList = converter.convertToList(input);
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        List<String> validList = getValidCombinations(convertedList);
        validList.forEach(w -> repository.save(new Word(w)));

        return validList;
    }

    private List<String> getValidCombinations(List<String> list){
        var sixLetters = separateMaxLetterWords(list);
        var mapByLength = mapByLength(separateNonMaxLetterWords(list));
        return findValidCombinations(sixLetters, mapByLength);
    }
}
