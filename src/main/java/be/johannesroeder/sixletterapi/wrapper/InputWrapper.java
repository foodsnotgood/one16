package be.johannesroeder.sixletterapi.wrapper;

import org.springframework.web.multipart.MultipartFile;

public class InputWrapper {
    private MultipartFile inputFile;
    private String inputText;

    public InputWrapper(MultipartFile inputFile) {
        this.inputFile = inputFile;
    }

    public InputWrapper(String inputText) {
        this.inputText = inputText;
    }

    public MultipartFile getInputFile() {
        return inputFile;
    }

    public String getInputText() {
        return inputText;
    }
}
