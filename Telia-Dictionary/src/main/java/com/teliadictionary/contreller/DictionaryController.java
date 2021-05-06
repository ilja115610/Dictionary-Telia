package com.teliadictionary.contreller;

import com.teliadictionary.DTO.Response;
import com.teliadictionary.DTO.Word;
import com.teliadictionary.service.EnglishWordService;
import com.teliadictionary.service.EstonianWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin("https://peaceful-basin-27081.herokuapp.com")
@RequestMapping("/api")
public class DictionaryController {


    private final EstonianWordService estonianService;

    private final EnglishWordService englishService;

    @Autowired
    public DictionaryController(EstonianWordService estonianService, EnglishWordService englishService) {
        this.estonianService = estonianService;
        this.englishService = englishService;
    }

    @PostMapping("/eng")
    public Response addNewEnglishWord (@RequestBody Word word) {

        return englishService.addWord(word);
    }


    @PostMapping("/est")
    public Response addNewEstonianWord(@RequestBody  Word word) {


        return estonianService.addWord(word);
    }


    @GetMapping("/est")
    public Set<Word> searchEstonianWordMatches (@RequestParam String word) {

        return estonianService.findWord(word);
    }


    @GetMapping("eng")
    public Set<Word> searchEnglishWordMatches (@RequestParam String word) {

        return englishService.findWord(word);
    }

}
