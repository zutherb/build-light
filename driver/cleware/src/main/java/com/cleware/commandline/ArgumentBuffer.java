package com.cleware.commandline;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author zutherb
 */
public class ArgumentBuffer {
    private static final int START_POSITION = -1;
    private String[] arguments;
    private int position;

    public ArgumentBuffer(String[] arguments) {
        this.arguments = arguments;
        this.position = START_POSITION;
    }

    public String peek() {
        if (position == START_POSITION) {
            return null;
        }
        return arguments[position];
    }

    public String next() {
        if (isFinished()) {
            return null;
        }
        return arguments[++position];
    }

    public boolean isEmpty() {
        return ArrayUtils.isEmpty(arguments);
    }

    public int position() {
        return position;
    }

    public boolean isFinished() {
        return arguments.length == 0 || position >= arguments.length - 1;
    }

    public String[] arguments() {
        return arguments;
    }
}
