package com.teliadictionary.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teliadictionary.DTO.Response;
import com.teliadictionary.DTO.Word;
import com.teliadictionary.contreller.DictionaryController;
import com.teliadictionary.service.EnglishWordService;
import com.teliadictionary.service.EstonianWordService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import java.util.Set;

@WebMvcTest(DictionaryController.class)
public class DictionaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EstonianWordService estonianWordService;

    @MockBean
    private EnglishWordService englishWordService;


    @Test
    public void searchEstonianWordOneMatchTest () throws Exception {

        Word word = new Word("ilm", Set.of("weather"));

        Mockito.when(estonianWordService.findWord("ilm")).thenReturn(Set.of(word));

        mockMvc.perform(get("/api/est?word=ilm")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(Set.of(word))));
    }

    @Test
    public void searchEnglishWordOneMatchTest () throws Exception {

        Word word = new Word("dog", Set.of("koer"));

        Mockito.when(englishWordService.findWord("dog")).thenReturn(Set.of(word));

        mockMvc.perform(get("/api/eng?word=dog")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(Set.of(word))));
    }


    @Test
    public void addNewEnglishWordTest () throws Exception {

        Word word = new Word("test",Set.of("test"));

        Mockito.when(englishWordService.addWord(word)).thenReturn(new Response("Success"));

        mockMvc.perform(post("/api/eng").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(word))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new Response("Success"))));
    }

    @Test
    public void addNewEstonianWordTest () throws Exception {

        Word word = new Word("test",Set.of("test"));

        Mockito.when(estonianWordService.addWord(word)).thenReturn(new Response("Success"));

        mockMvc.perform(post("/api/est").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(word))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(new Response("Success"))));

    }
}
