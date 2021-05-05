package com.teliadictionary.repositoryTest;

import com.teliadictionary.entity.EnglishWord;
import com.teliadictionary.entity.EstonianWord;
import com.teliadictionary.repository.EstonianWordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EstonianWordRepositoryTest {

    @Autowired
    private EstonianWordRepository repository;


    @Test
    public void getAllWordsTest () {

        List<EstonianWord> list = repository.findAll();

        assertThat(list.size()).isGreaterThan(35);

    }

    @Test
    public void findEstonianWordByWord () {

        EstonianWord word = new EstonianWord("ilm", Set.of(new EnglishWord("weather")));

        assertThat(repository.findEstonianWordByWord("ilm").getWord()).isEqualTo(word.getWord());

        EnglishWord tx = repository.findEstonianWordByWord("ilm").getTranslations().toArray(new EnglishWord [1])[0];

        assertThat(repository.findEstonianWordByWord("ilm").getTranslations()).contains(tx);

    }

}
