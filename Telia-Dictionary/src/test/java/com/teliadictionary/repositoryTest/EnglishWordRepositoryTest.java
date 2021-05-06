package com.teliadictionary.repositoryTest;


import com.teliadictionary.entity.EnglishWord;
import com.teliadictionary.entity.EstonianWord;
import com.teliadictionary.repository.EnglishWordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EnglishWordRepositoryTest {


    @Autowired
    private EnglishWordRepository repository;


    @Test
    public void getAllWordsTest () {

        List<EnglishWord> list = repository.findAll();

        assertThat(list.size()).isGreaterThan(35);

    }

    @Test
    public void findEnglishWordByWord () {

        EnglishWord word = new EnglishWord("dog", Set.of(new EstonianWord("koer")));

        assertThat(repository.findEnglishWordByWord("dog").getWord()).isEqualTo(word.getWord());

        EstonianWord tx = repository.findEnglishWordByWord("dog").getTranslations().toArray(new EstonianWord [1])[0];

        assertThat(repository.findEnglishWordByWord("dog").getTranslations()).contains(tx);

    }
}
