package com.comsysto.buildlight.cleware.commandline.parser;

/**
 * @author zutherb
 */
public class ParserException extends RuntimeException {
    public ParserException(Exception e) {
        super(e);
    }

    public ParserException(String msg) {
        super(msg);
    }
}
