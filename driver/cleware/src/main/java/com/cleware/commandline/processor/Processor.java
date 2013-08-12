package com.cleware.commandline.processor;

/**
 * @author zutherb
 */
public interface Processor {
    boolean isResponsible();
    void execute();
}
