package com.teliadictionary.repository;

import com.teliadictionary.entity.EnglishWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnglishWordRepository extends JpaRepository<EnglishWord, Long> {


    @Query
    EnglishWord findEnglishWordByWord (String word);

    @Query
    boolean existsByWord (String word);

}
