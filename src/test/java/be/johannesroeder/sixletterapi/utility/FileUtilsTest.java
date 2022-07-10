package be.johannesroeder.sixletterapi.utility;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    void multipartToFileTest() throws IOException {
        MockMultipartFile mockFile = buildMockFileWithStringContent("test");
        File convertedFile = FileUtils.multipartToFile(mockFile);
        assertTrue(convertedFile.isFile());
        convertedFile.delete();
    }

    @Test
    void multipartToFile_ContentTest() throws IOException {
        MockMultipartFile mockFile = buildMockFileWithStringContent("test with content");
        File convertedFile = FileUtils.multipartToFile(mockFile);
        BufferedReader reader = new BufferedReader(new FileReader(convertedFile));
        String content = reader.readLine();
        assertEquals("test with content", content);
        convertedFile.delete();
    }

    @Test
    void multipartToFile_emptyTest() throws IOException {
        MockMultipartFile mockFile = buildMockFileWithStringContent("");
        File convertedFile = FileUtils.multipartToFile(mockFile);
        BufferedReader reader = new BufferedReader(new FileReader(convertedFile));
        String content = reader.readLine();
        assertNull(content);
        convertedFile.delete();
    }

    private MockMultipartFile buildMockFileWithStringContent(String content){
        return new MockMultipartFile(
                "file",
                "mock.txt",
                MediaType.TEXT_PLAIN_VALUE,
                content.getBytes()
        );
    }
}