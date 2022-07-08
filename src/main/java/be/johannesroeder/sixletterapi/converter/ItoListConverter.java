package be.johannesroeder.sixletterapi.converter;

import java.io.IOException;
import java.util.List;

public interface ItoListConverter {
    List<String> convertToList() throws IOException;
}
