package com.teliadictionary.serviceTest;

import com.teliadictionary.DTO.Response;
import com.teliadictionary.DTO.Word;
import com.teliadictionary.entity.EnglishWord;
import com.teliadictionary.entity.EstonianWord;
import com.teliadictionary.repository.EnglishWordRepository;
import com.teliadictionary.repository.EstonianWordRepository;
import com.teliadictionary.service.EstonianWordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class EstonianWordServiceTest {


    @MockBean
    private EstonianWordRepository estRepository;

    @MockBean
    private EnglishWordRepository engRepository;

    @InjectMocks
    private EstonianWordService service;


    @Test
    public void addNewWordTest () {

        EstonianWord newEstonianWord = new EstonianWord("test",Set.of(new EnglishWord("test")));

        Mockito.when(estRepository.save(newEstonianWord)).thenReturn(newEstonianWord);

        Response response = service.addWord(new Word("test",Set.of("test")));

        assertThat(response.getMessage()).isEqualTo("Success");

    }

    @Test
    public void findWordExactMatch () {

        Mockito.when(estRepository.findEstonianWordByWord("ilm"))
                .thenReturn(new EstonianWord("ilm",Set.of(new EnglishWord("weather"))));

          assertThat(service.findWord("ilm")).isEqualTo(Set.of(new Word("ilm",Set.of("weather"))));
    }

    @Test
    public void findWordFuzzyMatch () {

        Mockito.when(estRepository.findAll()).thenReturn(List.of(new EstonianWord("ainus"),new EstonianWord("ainult")));

        assertThat(service.findWord("ainu")).isEqualTo(Set.of(new Word("ainus"),new Word("ainult")));
    }
}
