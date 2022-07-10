package be.johannesroeder.sixletterapi.factory;

import be.johannesroeder.sixletterapi.converter.ItoListConverter;
import be.johannesroeder.sixletterapi.converter.TxtFileToListConverter;
import be.johannesroeder.sixletterapi.converter.CsvFileToListConverter;
import be.johannesroeder.sixletterapi.converter.TxtToListConverter;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;

import java.io.IOException;

public class ToListConverterFactory {
    public static ItoListConverter getConverter(String mediaType, InputWrapper input) throws IOException {
        return switch (mediaType) {
            case "txt" -> new TxtFileToListConverter(input);
            case "csv" -> new CsvFileToListConverter(input);
            case "string" -> new TxtToListConverter(input);
            default -> throw new IOException("Unexpected filetype: " + mediaType);
        };
    }
}
