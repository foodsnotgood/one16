package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.expeptions.EmptyInputException;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TxtToListConverter implements ItoListConverter {

    private final String input;
    public TxtToListConverter(InputWrapper input) {
        this.input = input.getInputText();
    }

    @Override
    public List<String> convertToList() throws IOException {
        if (input.isEmpty()) throw new EmptyInputException("Input does not contain any text");
        return Arrays.stream(this.input.split("\n")).map(String::trim).toList();
    }
}
