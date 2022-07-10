package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TxtToListConverterTest {

    @Test
    public void throwIOErrorWhenInputIsEmpty(){
        TxtToListConverter converter = new TxtToListConverter(new InputWrapper(""));
        assertThrows(IOException.class, converter::convertToList);
    }
}