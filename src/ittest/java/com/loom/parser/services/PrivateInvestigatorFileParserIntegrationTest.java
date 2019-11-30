package com.loom.parser.services;

import com.loom.parser.ParserApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParserApplication.class)
public class PrivateInvestigatorFileParserIntegrationTest {

    @Autowired
    private PrivateInvestigatorFileParser privateInvestigatorFileParser;

    @Before
    public void setUp() throws Exception {
        Files.deleteIfExists(Paths.get("output.txt"));
    }

    @Test
    public void testPrivateInvestigatorFileParser() throws IOException, URISyntaxException {
        privateInvestigatorFileParser.parsePrivateInvestigatorFile("sample.txt", "output.txt");
        Path path = Paths.get("output.txt");
        List<String> result = Files.readAllLines(path, UTF_8);
        assertEquals(12, result.size());
    }
}
