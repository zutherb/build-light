package com.comsysto.buildlight.blinkstick.commandline.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zutherb
 */
@Component
public class CommandLineParser {

    private List<ArgumentParser> parserChain;

    @Autowired
    public CommandLineParser(List<ArgumentParser> parserChain) {
        this.parserChain = parserChain;
    }

    public String execute(ArgumentBuffer buffer) {
        StringBuffer outputBuffer = new StringBuffer();
        while (!buffer.isFinished()) {
            buffer.next();
            for (ArgumentParser argumentParser : parserChain) {
                if (argumentParser.isResponsible(buffer)) {
                    argumentParser.execute(buffer, outputBuffer);
                }
            }
        }
        return outputBuffer.toString();
    }
}
