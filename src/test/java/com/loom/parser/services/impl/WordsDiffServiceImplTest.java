package com.loom.parser.services.impl;

import com.google.common.collect.Lists;
import com.loom.parser.model.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
@RunWith(MockitoJUnitRunner.class)
public class WordsDiffServiceImplTest {

    @InjectMocks
    private WordsDiffServiceImpl wordsDiffService;

    @Mock
    private FormatMatcherImpl formatMatcher;

    @Test
    public void isWordsListDiffersInOnlyOneWord() {
        List<Word> wordsA = buildWordsList(Lists.newArrayList("Hello", "I", "Am", "Your", "Father"));
        List<Word> wordsB = buildWordsList(Lists.newArrayList("Hello", "I", "Am", "Your", "Mother"));
        boolean result = wordsDiffService.isWordsListDiffersInOnlyOneWord(wordsA, wordsB);
        assertTrue(result);
    }

    @Test
    public void isWordsListDiffersInOnlyOneWordWhenFalse() {
        List<Word> wordsA = buildWordsList(Lists.newArrayList("Hello", "I", "Am", "Your", "Father"));
        List<Word> wordsB = buildWordsList(Lists.newArrayList("Hello", "She", "Is", "Your", "Mother"));
        boolean result = wordsDiffService.isWordsListDiffersInOnlyOneWord(wordsA, wordsB);
        assertFalse(result);
    }

    @Test
    public void findDiffIndex() {
        List<Word> wordsA = buildWordsList(Lists.newArrayList("Hello", "I", "Am", "Her", "Father"));
        List<Word> wordsB = buildWordsList(Lists.newArrayList("Hello", "I", "Am", "Your", "Father"));
        int result = wordsDiffService.findDiffIndex(wordsA, wordsB);
        assertEquals(result, 3);
    }

    private List<Word> buildWordsList(List<String> words){
        List<Word> wordsResult = new ArrayList<>();
        IntStream.range(0, words.size()).forEach(i -> wordsResult.add(Word.builder().indexInLine(i).wordValue(words.get(i)).build()));
        return wordsResult;
    }
}