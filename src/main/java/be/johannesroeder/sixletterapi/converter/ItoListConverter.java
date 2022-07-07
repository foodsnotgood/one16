package be.johannesroeder.sixletterapi.converter;

import be.johannesroeder.sixletterapi.wrapper.InputWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItoListConverter {
    List<String> convertToList();
}
