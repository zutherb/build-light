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
        if (!isFinished()) {
            return arguments[++position];
        }
        return peek();
    }

    public boolean isEmpty() {
        return ArrayUtils.isEmpty(arguments);
    }

    public int position() {
        return position;
    }

    public boolean isFinished() {
        return position == arguments.length - 1;
    }

    public String[] arguments() {
        return arguments;
    }
}
