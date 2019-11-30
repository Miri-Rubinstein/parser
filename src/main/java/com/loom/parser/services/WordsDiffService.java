package com.loom.parser.services;

import com.loom.parser.model.Word;

import java.util.List;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
public interface WordsDiffService {
    boolean isWordsListDiffersInOnlyOneWord(List<Word> wordsA, List<Word> wordsB);
    int findDiffIndex(List<Word> wordsA, List<Word> wordsB);
    String getWordAtDiffIndex(int index, List<Word> words);
}
