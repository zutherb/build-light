package com.comsysto.buildlight.cleware.commandline.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zutherb
 */
@Component
public class CommandLineParser {

    private List<ArgumentParser> parserChain;
    private ArgumentParser helpParser;

    @Autowired
    public CommandLineParser(List<ArgumentParser> parserChain, @Qualifier("helpArgumentParser") ArgumentParser helpParser) {
        this.parserChain = parserChain;
        this.helpParser = helpParser;
    }

    public void execute(ArgumentBuffer buffer) {
        printHelpMessageIfBufferIsEmpty(buffer);
        runArgumentParserChain(buffer);
    }

    private void runArgumentParserChain(ArgumentBuffer buffer) {
        while (!buffer.isFinished()) {
            buffer.next();
            for (ArgumentParser argumentParser : parserChain) {
                if (argumentParser.isResponsible(buffer)) {
                    argumentParser.execute(buffer);
                }
            }
        }
    }

    private void printHelpMessageIfBufferIsEmpty(ArgumentBuffer buffer) {
        if (buffer.isFinished()) {
            helpParser.execute(buffer);
        }
    }
}
