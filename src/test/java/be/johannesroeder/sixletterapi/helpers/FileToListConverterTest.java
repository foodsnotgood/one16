package be.johannesroeder.sixletterapi.helpers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
            writeToFile();
        } catch (IOException e) {
            System.err.println("An Error happened creating a temporary test file in " + this.getClass().getSimpleName());
        }
    }

    private void writeToFile() throws IOException {
        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write("this\nis\na\nsimple\ntest\n\nto\nsee\nif\nthe\ntest\nworks");
        bufferedWriter.close();
    }
    @Test
    public void firstTest(){

       // List<String> list = FileToListConverter.convertToList(file);
    }
}
