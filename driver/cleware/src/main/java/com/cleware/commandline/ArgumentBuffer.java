package com.cleware.commandline;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author zutherb
 */
public class ArgumentBuffer {
    private String[] arguments;
    private int position;

    public ArgumentBuffer(String[] arguments) {
        this.arguments = arguments;
        this.position = 0;
    }

    public String peek() {
        return arguments[position];
    }

    public String next() {
        position = ++position;
        if (isFinished()) {
            return arguments[position - 1];
        }
        return arguments[position];
    }

    public boolean isEmpty() {
        return ArrayUtils.isEmpty(arguments);
    }

    public int position() {
        return position;
    }

    public boolean isFinished() {
        return position >= arguments.length;
    }

    public String[] arguments() {
        return arguments;
    }
}