package com.teliadictionary.DTO;

import java.util.HashSet;
import java.util.Set;

public class Word {

    private String word;


    private Set<String> translations = new HashSet<>();

    public Word() {
    }

    public Word(String word) {
        this.word = word;
    }

    public Word(Set<String> translations) {
        this.translations = translations;
    }

    public Word(String word, Set<String> translations) {
        this.word = word;
        this.translations = translations;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<String> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<String> translations) {
        this.translations = translations;
    }


}
