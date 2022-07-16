package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TxtToListConverterTest {

    @Test
    public void throwIOErrorWhenInputIsEmpty() {
        var converter = new TxtToListConverter();
        assertThrows(IOException.class, () -> converter.convertToList(new InputWrapper<>("")));
    }

    @Test
    public void convertToList_sizeTest() throws IOException {
        var converter = new TxtToListConverter();
        var convertedList = converter.convertToList(new InputWrapper<>("test\nif\nconvert\nis\nworking"));
        assertEquals(5, convertedList.size());
    }

    @Test
    public void convertToList_contentTest() throws IOException {
        var converter = new TxtToListConverter();
        var convertedList = converter.convertToList(new InputWrapper<>("test\nif\nconvert\nis\nworking"));
        var testList = List.of("test", "if", "convert", "is", "working");

        convertedList.forEach(w -> assertTrue(testList.contains(w)));
    }
}