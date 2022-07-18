package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.exceptions.EmptyInputException;
import be.johannesroeder.sixletterapi.exceptions.WrongDelimiterException;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileToListConverter implements ItoListConverter<File> {

    private final String DELIMITER = ";";

    @Override
    public List<String> convertToList(InputWrapper<File> input) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(input.input()));
        List<String> convertedList = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null){
            if (!line.contains(DELIMITER)) throw new WrongDelimiterException("File has wrong format. Expected delimiter: " + DELIMITER);
            convertedList.add(line.split(DELIMITER)[1].trim());
        }
        if (convertedList.isEmpty()) throw new EmptyInputException("Input is empty");
        return convertedList;
    }
}
