package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.expeptions.EmptyInputException;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TxtToListConverter implements ItoListConverter<String> {


    @Override
    public List<String> convertToList(InputWrapper<String> input) throws IOException {
        if (input.input().isEmpty()) throw new EmptyInputException("Input does not contain any text");
        return Arrays.stream(input.input().split("\n")).map(String::trim).toList();
    }
}
