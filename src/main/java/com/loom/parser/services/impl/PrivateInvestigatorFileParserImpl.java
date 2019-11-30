package com.loom.parser.services.impl;

import com.google.common.collect.Lists;
import com.loom.parser.model.Line;
import com.loom.parser.model.Word;
import com.loom.parser.services.FileHandler;
import com.loom.parser.services.PrivateInvestigatorFileParser;
import com.loom.parser.services.WordsDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
@Service
public class PrivateInvestigatorFileParserImpl implements PrivateInvestigatorFileParser {

    @Autowired
    private FileHandler fileHandler;

    @Autowired
    private WordsDiffService wordsDiffService;

    private HashMap<Line, List<Line>> linesPerDiffHashMap = new HashMap<>();

    @Override
    public void parsePrivateInvestigatorFile(String inputFileName, String outputFileName) throws IOException {
        List<String> linesStrList = fileHandler.readLinesFromFile(inputFileName);
        List<Line> lines = IntStream.range(0, linesStrList.size())
                .mapToObj(i -> Line.builder()
                        .index(i)
                        .words(buildWordListFromLine(linesStrList.get(i)))
                        .build())
                .collect(Collectors.toList());
        lines.forEach(this::buildDiffMap);
        fileHandler.writeOutputToFile(linesPerDiffHashMap, outputFileName);
    }

    private void buildDiffMap (Line currentLine){
        List<Word> currentLineWords = currentLine.getWords();
        linesPerDiffHashMap.forEach((k,v) -> {
            List<Word> entryWords = k.getWords();
            if (wordsDiffService.isWordsListDiffersInOnlyOneWord(currentLineWords, entryWords)) {
                v.add(currentLine);
            }
        });
        linesPerDiffHashMap.put(currentLine, Lists.newArrayList());
    }

    private List<Word> buildWordListFromLine(String line){
        List<String> wordsStrs = Arrays.asList(line.split(" "));
        return IntStream.range(0, wordsStrs.size())
                .mapToObj(i -> Word.builder()
                        .wordValue(wordsStrs.get(i))
                        .indexInLine(i)
                        .build())
                .collect(Collectors.toList());
    }
}
