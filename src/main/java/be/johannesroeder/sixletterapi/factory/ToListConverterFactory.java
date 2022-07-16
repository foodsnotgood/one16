package be.johannesroeder.sixletterapi.factory;

import be.johannesroeder.sixletterapi.converter.*;

import java.io.IOException;

public class ToListConverterFactory {
    public static  ItoListConverter getConverter(String mediaType) throws IOException {
        return switch (mediaType) {
            case "txt" -> new TxtFileToListConverter();
            case "csv" -> new CsvFileToListConverter();
            case "string" -> new TxtToListConverter();
            default -> throw new IOException("Unexpected filetype: " + mediaType);
        };
    }
}
