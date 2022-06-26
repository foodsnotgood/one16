package be.johannesroeder.sixletterapi.helpers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileToListConverterTest {

    private File file;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUpFile() {
        try {
            file = folder.newFile("testfile.txt");
        } catch (IOException e) {
            System.err.println("An Error happened creating a temporary test file in " + this.getClass().getSimpleName());
        }
    }

    @Test
    public void firstTest(){
        List<String> list = FileToListConverter.convertToList(file);
    }
}
