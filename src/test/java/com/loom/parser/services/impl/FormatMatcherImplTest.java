package com.loom.parser.services.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Miri Rubinstein on 2019-11-29
 */
@RunWith(MockitoJUnitRunner.class)
public class FormatMatcherImplTest {

    @InjectMocks
    private FormatMatcherImpl formatMatcher;

    @Test
    public void isDateOrTimeWithDate() {
        boolean result = formatMatcher.isDateOrTime("30-01-2020");
        assertTrue(result);
    }

    @Test
    public void isDateOrTimeWithTime() {
        boolean result = formatMatcher.isDateOrTime("10:14:00");
        assertTrue(result);
    }

    @Test
    public void isDateOrTimeWithWord() {
        boolean result = formatMatcher.isDateOrTime("hello");
        assertFalse(result);
    }
}