package be.johannesroeder.sixletterapi.expeptions;

import java.io.IOException;

public class WrongDelimiterException extends IOException {
    public WrongDelimiterException(String message) {
        super(message);
    }
}
