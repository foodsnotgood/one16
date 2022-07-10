package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TxtFileToListConverter implements ItoListConverter {
    private final File INPUT_TXT_FILE;

    public TxtFileToListConverter(InputWrapper input) {
        this.INPUT_TXT_FILE = input.getInputFile();
    }

    @Override
    public List<String> convertToList() throws IOException {
        List<String> list = new ArrayList<>();
            InputStream inputStream = new FileInputStream(this.INPUT_TXT_FILE);
            new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .forEach(list::add);
        return list;
    }
}
