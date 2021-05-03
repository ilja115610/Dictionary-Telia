package com.teliadictionary.util;

import com.teliadictionary.DTO.Word;
import com.teliadictionary.entity.EnglishWord;
import com.teliadictionary.entity.EstonianWord;
import java.util.*;
import java.util.stream.Collectors;

public class FuzzyMatches {

    public static int levenshteinDistance (CharSequence lhs, CharSequence rhs) {
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;

        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        for (int i = 0; i < len0; i++) cost[i] = i;


        for (int j = 1; j < len1; j++) {
            newcost[0] = j;

            for(int i = 1; i < len0; i++) {
                int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

                int cost_replace = cost[i - 1] + match;
                int cost_insert  = cost[i] + 1;
                int cost_delete  = newcost[i - 1] + 1;

                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            int[] swap = cost; cost = newcost; newcost = swap;
        }

        return cost[len0 - 1];
    }

    public static Set<Word> computeEstMatches (String word, List<EstonianWord> allWords, List<Word> matches) {

        allWords.forEach(w-> {
            if(FuzzyMatches.levenshteinDistance((word.replaceAll(" ",""))
                    ,w.getWord().replaceAll(" ",""))<3){
                matches.add(new Word(w.getWord(),extractTranslationEstStrings(w)));
            }
        });
        return getWords(word, matches);
    }

    private static Set<Word> getWords(String word, List<Word> matches) {
        if(matches.size()==0){
            return Set.of(new Word("Sorry ",Set.of("No matches found")));
        }
        return sortMatches(matches, (o1, o2) -> {
            if(levenshteinDistance(word,o1.getWord())<levenshteinDistance(word,o2.getWord())){
                return -1;
            }
            else if (levenshteinDistance(word,o1.getWord())>levenshteinDistance(word,o2.getWord())){
                return 1;
            }
            return 0;
        });
    }


    public static Set<Word> computeEngMatches (String word, List<EnglishWord> allWords, List<Word> matches) {

        allWords.forEach(w-> {
            if(FuzzyMatches.levenshteinDistance((word.replaceAll(" ",""))
                    ,w.getWord().replaceAll(" ",""))<3){
                matches.add(new Word(w.getWord(),extractTranslationEngStrings(w)));
            }
        });
        return getWords(word, matches);
    }


    private static Set<Word> sortMatches (List<Word> matches, Comparator<Word> comparator) {

        matches.sort(comparator);

        return new LinkedHashSet<>(matches);
    }



    public static Set<String> extractTranslationEstStrings (EstonianWord word) {

        return word.getTranslations().stream().map(w->" "+w.getWord()).collect(Collectors.toSet());
    }

    public static Set<String> extractTranslationEngStrings (EnglishWord word) {

        return word.getTranslations().stream().map(w->" "+w.getWord()).collect(Collectors.toSet());
    }
}
