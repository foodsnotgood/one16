package be.johannesroeder.sixletterapi.factory;

import be.johannesroeder.sixletterapi.converter.ItoListConverter;
import be.johannesroeder.sixletterapi.converter.TxtFileToListConverter;
import be.johannesroeder.sixletterapi.converter.csvFileToListConverter;
import be.johannesroeder.sixletterapi.converter.txtToListConverter;
import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.springframework.web.multipart.MultipartFile;

public class ToListConverterFactory {
    public static ItoListConverter getToListConverter(String mediaType, InputWrapper input){
        return switch (mediaType) {
            case "text/plain" -> new TxtFileToListConverter(input);
            case "csv" -> new csvFileToListConverter(input);
            case "string" -> new txtToListConverter(input);
            default -> throw new IllegalStateException("Unexpected value: " + mediaType);
        };
    }
}
