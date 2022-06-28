package be.johannesroeder.sixletterapi.helpers;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileToListConverterTest {

    File file;

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
    public void firstTest() {
        System.out.println(file.length());
        assertTrue(file.exists());
       // List<String> list = FileToListConverter.convertToList(file);
    }
}
