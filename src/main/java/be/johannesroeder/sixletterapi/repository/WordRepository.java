package be.johannesroeder.sixletterapi.repository;

import be.johannesroeder.sixletterapi.model.Word;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WordRepository extends CrudRepository<Word, Long> {
    List<Word> findAll();
}
