package be.johannesroeder.sixletterapi.expeptions;

import java.io.IOException;

public class EmptyInputException extends IOException {
    public EmptyInputException(String message) {
        super(message);
    }
}
