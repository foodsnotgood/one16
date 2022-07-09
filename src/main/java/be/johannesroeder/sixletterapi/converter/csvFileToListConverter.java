package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.wrapper.InputWrapper;

import java.io.IOException;
import java.util.List;

//An exmaple of what could be used to accept csv input. Implementation not het done.
public class csvFileToListConverter implements ItoListConverter {
    public csvFileToListConverter(InputWrapper input) {
    }

    @Override
    public List<String> convertToList() throws IOException {
        return null;
    }
}
