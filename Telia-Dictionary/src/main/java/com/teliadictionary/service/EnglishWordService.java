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
public class EnglishWordService {


    @Autowired
    private EnglishWordRepository englishWordRepository;

    @Autowired
    private EstonianWordRepository estonianWordRepository;



    public Response addWord (Word word) {

        EnglishWord newEnglishWord = new EnglishWord();

        newEnglishWord.setWord(word.getWord().toLowerCase());
        Set<EstonianWord> translations = new HashSet<>();
        word.getTranslations().forEach(w->{

            if(alreadyExists(w.toLowerCase())){
                translations.add(estonianWordRepository.findEstonianWordByWord(w.toLowerCase()));
            }
            else {
                translations.add(new EstonianWord(w.toLowerCase()));
            }
        });
        newEnglishWord.setTranslations(translations);

        englishWordRepository.save(newEnglishWord);

        return new Response("Success");
    }


    public Set<Word> findWord(String word) {

        word = word.toLowerCase(Locale.ROOT);

        EnglishWord searchWord = englishWordRepository.findEnglishWordByWord(word);

        if(searchWord != null) {
            Set<String> translations = FuzzyMatches.extractTranslationEngStrings(searchWord);
            return Set.of(new Word(searchWord.getWord(), translations));
        }

        List<EnglishWord> allWords = englishWordRepository.findAll();

        return FuzzyMatches.computeEngMatches(word,allWords, new ArrayList<>());


    }



    private boolean alreadyExists (String word) {

        return estonianWordRepository.existsByWord(word);
    }

}
