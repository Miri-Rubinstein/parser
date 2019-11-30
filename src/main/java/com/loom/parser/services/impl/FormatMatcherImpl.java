package com.loom.parser.services.impl;

import com.loom.parser.services.FormatMatcher;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
@Service
public class FormatMatcherImpl implements FormatMatcher {

    private final static String DATE_FORMAT = "dd-MM-yyyy";
    private final static Pattern TIME_PATTERN = Pattern.compile("(?m)^(\\d\\d:\\d\\d:\\d\\d)");

    public boolean isDateOrTime(String word) {
        return isDate(word) || isTime(word);
    }

    private boolean isDate(String word){
        try {
            new SimpleDateFormat(DATE_FORMAT).parse(word);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private boolean isTime(String word){
        return TIME_PATTERN.matcher(word).matches();
    }
}
