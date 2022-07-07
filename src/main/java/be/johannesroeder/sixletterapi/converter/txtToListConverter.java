package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.wrapper.InputWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class txtToListConverter implements ItoListConverter {

    private final String input;
    public txtToListConverter(InputWrapper input) {
        this.input = input.getInputText();
    }

    @Override
    public List<String> convertToList() {
        return Arrays.stream(this.input.split("\n")).toList();
    }
}
