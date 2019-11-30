package com.loom.parser.services;

import java.io.IOException;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
public interface PrivateInvestigatorFileParser {
    void parsePrivateInvestigatorFile(String inputFileName, String outputFileName) throws IOException;
}
