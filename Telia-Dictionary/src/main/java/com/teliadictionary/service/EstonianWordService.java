package com.teliadictionary.service;


import com.teliadictionary.DTO.Response;
import com.teliadictionary.DTO.Word;
import com.teliadictionary.entity.EnglishWord;
import com.teliadictionary.entity.EstonianWord;
import com.teliadictionary.repository.EnglishWordRepository;
import com.teliadictionary.repository.EstonianWordRepository;
import com.teliadictionary.util.FuzzyMatches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
public class EstonianWordService {

    @Autowired
    private EstonianWordRepository estonianWordRepository;

    @Autowired
    private EnglishWordRepository englishWordRepository;


    public Response addWord (Word word) {

        EstonianWord newEstonianWord = new EstonianWord();

        newEstonianWord.setWord(word.getWord().toLowerCase());
        Set<EnglishWord> translations = new HashSet<>();
        word.getTranslations().forEach(w->{

            if(alreadyExists(w.toLowerCase())){
                translations.add(englishWordRepository.findEnglishWordByWord(w.toLowerCase()));
            }
            else {
                translations.add(new EnglishWord(w.toLowerCase()));
            }

        });
        newEstonianWord.setTranslations(translations);

        estonianWordRepository.save(newEstonianWord);

        return new Response("Success");
    }




    public Set<Word> findWord(String word) {

        word = word.toLowerCase(Locale.ROOT);

        EstonianWord searchWord = estonianWordRepository.findEstonianWordByWord(word);

        if(searchWord != null) {
            Set<String> translations = FuzzyMatches.extractTranslationEstStrings(searchWord);
            return Set.of(new Word(searchWord.getWord(), translations));
        }

        List<EstonianWord> allWords = estonianWordRepository.findAll();


        return FuzzyMatches.computeEstMatches(word,allWords, new ArrayList<>());

    }



    private boolean alreadyExists(String word) {

        return englishWordRepository.existsByWord(word);
    }


}
