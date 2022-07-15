package be.johannesroeder.sixletterapi.controllers;

import be.johannesroeder.sixletterapi.model.Word;
import be.johannesroeder.sixletterapi.repository.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class apiControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private WordRepository repository;

    private MockMvc mockMvc;

    @BeforeEach
    void mockMvcSetup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void apiFile_csv_test() throws Exception {
        MockMultipartFile mockFile = buildMockFileWithWords("1;test", "csv");
        mockMvc.perform(multipart("/api/file").file(mockFile)).andExpect(status().isOk());
    }

    @Test
    void apiFile_txt_test() throws Exception {
        MockMultipartFile mockFile = buildMockFileWithWords("test", "txt");
        mockMvc.perform(multipart("/api/file").file(mockFile)).andExpect(status().isOk());
    }

    @Test
    void apiString_test() throws Exception {
        mockMvc.perform(post("/api/string")
                        .contentType(MediaType.TEXT_PLAIN)
                        .characterEncoding("utf-8")
                        .content("tester"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void entities_saved_from_request_body_string() throws Exception {
        repository.deleteAll();
        mockMvc.perform(post("/api/string")
                        .contentType(MediaType.TEXT_PLAIN)
                        .characterEncoding("utf-8")
                        .content("tester\nlocker\njammer\nte\nster\nlock\ner\njam\nmer"))
                .andDo(print())
                .andExpect(status().isOk());

        List<String> correctList = List.of("tester", "locker", "jammer");

        repository.findAll().forEach(w -> {
            assertTrue(correctList.contains(w.getWord()));
        });

        assertEquals(correctList.size(), repository.findAll().size());
    }

    @Test
    void entities_saved_from_file_test() throws Exception {
        repository.deleteAll();
        MockMultipartFile mockFile = buildMockFileWithWords("tester\nlocker\njammer\nte\nster\nlock\ner\njam\nmer", "txt");
        mockMvc.perform(multipart("/api/file").file(mockFile)).andExpect(status().isOk());

        List<String> correctList = List.of("tester", "locker", "jammer");

        repository.findAll().forEach(w -> {
            assertTrue(correctList.contains(w.getWord()));
        });

        assertEquals(correctList.size(), repository.findAll().size());
    }

    private MockMultipartFile buildMockFileWithWords(String words, String extension) {
        return new MockMultipartFile(
                "file",
                "mock." + extension,
                MediaType.TEXT_PLAIN_VALUE,
                words.getBytes()
        );
    }

}