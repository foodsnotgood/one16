package be.johannesroeder.sixletterapi.wrapper;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class InputWrapper {
    private File inputFile;
    private String inputText;

    public InputWrapper(File inputFile) {
        this.inputFile = inputFile;
    }

    public InputWrapper(String inputText) {
        this.inputText = inputText;
    }

    public File getInputFile() {
        return inputFile;
    }

    public String getInputText() {
        return inputText == null ? "" : inputText;
    }
}
