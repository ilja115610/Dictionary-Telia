package com.teliadictionary.repository;

import com.teliadictionary.entity.EstonianWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
public interface EstonianWordRepository extends JpaRepository<EstonianWord, Long> {



    @Query
    EstonianWord findEstonianWordByWord (String word);

    @Query
    boolean existsByWord (String word);
}
