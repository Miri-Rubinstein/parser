package com.loom.parser.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
@Builder
@Data
public class Line {
    private int index;
    List<Word> words;
}
