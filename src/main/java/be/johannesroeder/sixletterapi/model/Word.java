package be.johannesroeder.sixletterapi.model;

import be.johannesroeder.sixletterapi.utility.TheFunPartUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = TheFunPartUtils.COMBINATION_LENGTH)
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public Word() {
    }
}
