package com.loom.parser.services.impl;

import com.loom.parser.model.Word;
import com.loom.parser.services.FormatMatcher;
import com.loom.parser.services.WordsDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
@Service
public class WordsDiffServiceImpl implements WordsDiffService {

    @Autowired
    private FormatMatcher formatMatcher;

    public boolean isWordsListDiffersInOnlyOneWord(List<Word> wordsA, List<Word> wordsB) {
        int diffCounter = 0;
        int i = 0;
        while (i < wordsA.size() && i < wordsB.size() && diffCounter <= 2) {
            Word wordA = wordsA.get(i);
            Word wordB = wordsB.get(i);
            if (!shouldSkipCurrent(wordA.getWordValue(), wordB.getWordValue()) && (!(wordA.getWordValue().equals(wordB.getWordValue())))) {
                diffCounter ++;
            }
            i++;
        }

        if (i < wordsA.size()){
            diffCounter += wordsA.size() - 1 - i;
        }

        else if (i < wordsB.size()) {
            diffCounter += wordsB.size() - 1 - i;
        }
        return diffCounter <= 1;
    }

    public int findDiffIndex(List<Word> wordsA, List<Word> wordsB) {
        int i = 0;
        while (i < wordsA.size() && i < wordsB.size()) {
            Word wordA = wordsA.get(i);
            Word wordB = wordsB.get(i);
            if (!shouldSkipCurrent(wordA.getWordValue(), wordB.getWordValue()) && (!(wordA.getWordValue().equals(wordB.getWordValue())))) {
                return i;
            }
            i++;
        }
        return i;
    }

    public String getWordAtDiffIndex(int index, List<Word> words) {
        if (index > words.size() -1) {
            return "<empty>";
        }
        else return words.get(index).getWordValue();
    }

    private boolean shouldSkipCurrent(String wordA, String wordB){
        return formatMatcher.isDateOrTime(wordA) && formatMatcher.isDateOrTime(wordB);
    }
}
