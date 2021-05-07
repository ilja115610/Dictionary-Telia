## Estonian - English dictionary

### Full-stack web application using Spring Boot(REST API) & Angular.

### Functional specification:

-User can translate estonian and english words. There are 2 options: 
1. Exact match - when search word is exactly matching the one in database.
One word can have several translations all of them will be displayed.
2. Fuzzy match - e.g. user makes typo or search word is different up to 2 characters from the available options in database.
In this case suggestions will be displayed - all words which are not more than 2 characters different + their translations.
   Suggestions will be sorted in 'most relevant first' manner. (Levenshtein algorithm used)
   
-User can add new words and their translations to the dictionary.

All form inputs are validated.

**See all available words in 'list_of_words' directory**

#### Used Technologies and Tools:

## Front-end

* TypeScript

* Angular 11

* Bootstrap 4

## Back-end

* Java 11

* Spring-Boot 2.4.5 (Spring REST/ Spring Data Jpa)

* Hibernate

* H2 Database

## @Testing

**Persistence**

@DataJpaTest/JUnit 5/AssertJ

**Web Layer**

@WebMvcTest/@Mockito/JUnit 5/AssertJ

**Service Layer**

@ExtendWith/JUnit 5/AssertJ

## Live Demo - Heroku

Link: `https://peaceful-basin-27081.herokuapp.com/`

