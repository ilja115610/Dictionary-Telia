package com.teliadictionary.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estonian_word")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EstonianWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word")
    private String word;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "estonian_english",joinColumns = @JoinColumn(name = "estonianWord_id")
            ,inverseJoinColumns = @JoinColumn(name = "englishWord_id"))
    private Set<EnglishWord> translations = new HashSet<>();

    public EstonianWord(String word) {
        this.word = word;
    }

    public EstonianWord() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Transient
    public void addTranslations (EnglishWord word) {

        this.translations.add(word);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<EnglishWord> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<EnglishWord> translations) {
        this.translations = translations;
    }
}
