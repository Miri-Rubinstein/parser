package com.loom.parser.services;

import com.loom.parser.model.Line;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
public interface FileHandler {
    List<String> readLinesFromFile(String inputFileName) throws IOException;
    void writeOutputToFile(HashMap<Line, List<Line>> linesPerDiffHashMap, String outputFileName);
}
