package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.wrapper.InputWrapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TxtFileToListConverter implements ItoListConverter<File>{

    @Override
    public List<String> convertToList(InputWrapper<File> input) throws IOException {
        List<String> list = new ArrayList<>();
            InputStream inputStream = new FileInputStream(input.input());
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .forEach(list::add);
        return list;
    }
}
