package com.loom.parser.services.impl;

import com.google.common.collect.Lists;
import com.loom.parser.model.Line;
import com.loom.parser.model.Word;
import com.loom.parser.services.FileHandler;
import com.loom.parser.services.WordsDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
@Service
public class FileHandlerImpl implements FileHandler {

    @Autowired
    private WordsDiffService wordsDiffService;

    private static final String DIFF_LINE_FORMAT = "The changing word was: %s, %s";

    public List<String> readLinesFromFile(String inputFileName) throws IOException {
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(inputFileName)).getPath());
        return Files.readAllLines(path, UTF_8);
    }

    public void writeOutputToFile(HashMap<Line, List<Line>> linesPerDiffHashMap, String outputFileName) {
        linesPerDiffHashMap.forEach((keyLine, matchingLines) -> {
            List<Word> keyLineWords = keyLine.getWords();
            matchingLines.forEach(matchingLine ->
                    writeLinesToFile(buildOutputLinesPerDiff(keyLineWords, matchingLine.getWords()), outputFileName));
        });
    }

    private List<String> buildOutputLinesPerDiff(List<Word> wordsA, List<Word> wordsB){
        int diffIndex = wordsDiffService.findDiffIndex(wordsA, wordsB);
        String diffLine = buildDiffLine(wordsDiffService.getWordAtDiffIndex(diffIndex, wordsA),
                wordsDiffService.getWordAtDiffIndex(diffIndex, wordsB));
        return Lists.newArrayList(buildStrFromWords(wordsA), buildStrFromWords(wordsB), diffLine);
    }

    private void writeLinesToFile (List<String> lines, String outputFileName) {
        try {
            File file = new File(outputFileName);
            if (!file.exists() && !file.createNewFile()){
                throw new RuntimeException(format("Creating file %s has failed", outputFileName));
            }
            Files.write(file.toPath(), lines, UTF_8, APPEND);
            Files.write(file.toPath(), System.lineSeparator().getBytes(UTF_8), APPEND);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String buildDiffLine(String diffWordA, String diffWordB) {
        return format(DIFF_LINE_FORMAT, diffWordA, diffWordB);
    }

    private String buildStrFromWords(List<Word> words) {
        List<String> wordsStrs = words.stream().map(Word::getWordValue).collect(Collectors.toList());
        return String.join(" ", wordsStrs);
    }
}
