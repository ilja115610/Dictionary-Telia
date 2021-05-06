package com.teliadictionary.entity;

import com.fasterxml.jackson.annotation.*;
import com.teliadictionary.entity.EstonianWord;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "english_word")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EnglishWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word")
    private String word;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "estonian_english",joinColumns = @JoinColumn(name = "englishWord_id")
    ,inverseJoinColumns = @JoinColumn(name = "estonianWord_id"))
    private Set<EstonianWord> translations = new HashSet<>();

    public EnglishWord(String word) {
        this.word = word;
    }

    public EnglishWord() {
    }

    public EnglishWord(String word, Set<EstonianWord> translations) {
        this.word = word;
        this.translations = translations;
    }

    @Transient
    public void addTranslations (EstonianWord word) {

        this.translations.add(word);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<EstonianWord> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<EstonianWord> translations) {
        this.translations = translations;
    }
}
