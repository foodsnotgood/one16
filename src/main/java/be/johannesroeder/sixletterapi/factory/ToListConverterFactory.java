package be.johannesroeder.sixletterapi.factory;

import be.johannesroeder.sixletterapi.converter.ItoListConverter;
import be.johannesroeder.sixletterapi.converter.TxtFileToListConverter;
import be.johannesroeder.sixletterapi.converter.csvFileToListConverter;
import be.johannesroeder.sixletterapi.converter.TxtToListConverter;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;

public class ToListConverterFactory {
    public static ItoListConverter getConverter(String mediaType, InputWrapper input){
        return switch (mediaType) {
            case "text/plain" -> new TxtFileToListConverter(input);
            case "csv" -> new csvFileToListConverter(input);
            case "string" -> new TxtToListConverter(input);
            default -> throw new IllegalStateException("Unexpected value: " + mediaType);
        };
    }
}
