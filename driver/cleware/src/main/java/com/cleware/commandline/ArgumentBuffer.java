package com.cleware.commandline;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author zutherb
 */
public class ArgumentBuffer {
    private String[] arguments;
    private int position;

    public ArgumentBuffer(String[] arguments) {
        Validate.notEmpty("Program arguments must not be null");
        this.arguments = ArrayUtils.clone(arguments);
        this.position = 0;
    }

    public String peek() {
        return arguments[position];
    }

    public String next() {
        return arguments[position++];
    }
}
