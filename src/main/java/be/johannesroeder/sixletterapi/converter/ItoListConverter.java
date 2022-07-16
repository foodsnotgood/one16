package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.wrapper.InputWrapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ItoListConverter<T> {
    List<String> convertToList(InputWrapper<T> input) throws IOException;
}
