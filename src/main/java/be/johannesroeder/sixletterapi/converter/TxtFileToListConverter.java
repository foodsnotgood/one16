package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TxtFileToListConverter implements ItoListConverter {
    private final MultipartFile input;

    public TxtFileToListConverter(InputWrapper input) {
        this.input = input.getInputFile();
    }

    @Override
    public List<String> convertToList() throws IOException {
        List<String> list = new ArrayList<>();
            InputStream inputStream = this.input.getInputStream();
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .forEach(list::add);
        return list;
    }
}
