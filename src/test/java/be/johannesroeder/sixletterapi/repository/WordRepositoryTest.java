package be.johannesroeder.sixletterapi.repository;

import be.johannesroeder.sixletterapi.model.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WordRepositoryTest {

    @Autowired
    private WordRepository wordRepository;

    @Test
    public void repositoryTest(){
        Word savedWord = wordRepository.save(new Word("test"));
        Word foundWord = wordRepository.findById(savedWord.getId()).get();

        assertNotNull(foundWord);
        assertEquals(savedWord.getWord(), foundWord.getWord());
    }
}