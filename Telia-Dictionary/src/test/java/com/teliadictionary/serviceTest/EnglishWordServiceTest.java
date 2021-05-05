package com.teliadictionary.serviceTest;

import com.teliadictionary.DTO.Response;
import com.teliadictionary.DTO.Word;
import com.teliadictionary.entity.EnglishWord;
import com.teliadictionary.entity.EstonianWord;
import com.teliadictionary.repository.EnglishWordRepository;
import com.teliadictionary.repository.EstonianWordRepository;
import com.teliadictionary.service.EnglishWordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class EnglishWordServiceTest {

    @MockBean
    private EstonianWordRepository estRepository;

    @MockBean
    private EnglishWordRepository engRepository;

    @InjectMocks
    private EnglishWordService service;


    @Test
    public void addNewWordTest () {

        EnglishWord newEnglishWord = new EnglishWord("test", Set.of(new EstonianWord("test")));

        Mockito.when(engRepository.save(newEnglishWord)).thenReturn(newEnglishWord);

        Response response = service.addWord(new Word("test",Set.of("test")));

        assertThat(response.getMessage()).isEqualTo("Success");

    }

    @Test
    public void findWordExactMatch () {

        Mockito.when(engRepository.findEnglishWordByWord("dog"))
                .thenReturn(new EnglishWord("dog",Set.of(new EstonianWord("koer"))));

        assertThat(service.findWord("dog")).isEqualTo(Set.of(new Word("dog",Set.of("koer"))));
    }

    @Test
    public void findWordFuzzyMatch () {

        Mockito.when(engRepository.findAll()).thenReturn(List.of(new EnglishWord("fog")
                ,new EnglishWord("job"), new EnglishWord("dog"),new EnglishWord("frog")));

        assertThat(service.findWord("foc")).isEqualTo(Set.of(new Word("fog")
                ,new Word("job"), new Word("dog"),new Word("frog")));
    }
}
