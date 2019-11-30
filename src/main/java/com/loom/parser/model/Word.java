package com.loom.parser.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Miri Rubinstein on 2019-11-29
 */
@Builder
@Data
public class Word {
    private int indexInLine;
    private String wordValue;
}
