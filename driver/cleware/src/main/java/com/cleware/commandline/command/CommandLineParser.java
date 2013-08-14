package com.cleware.commandline.command;

import java.util.Arrays;
import java.util.List;

/**
 * @author zutherb
 */
public class CommandLineParser {
    private static final List<ArgumentParser> ARGUMENT_PARSER_CHAIN = Arrays.asList(
            new GuiArgumentParser(),
            new LedArgumentParser(),
            new WaitArgumentParser(),
            new KnightRiderArgumentParser(),
            HelpArgumentParser.instance(),
            new TestArgumentParser());

    private ArgumentBuffer buffer;

    public CommandLineParser(ArgumentBuffer buffer) {
        this.buffer = buffer;
    }

    public void execute() {
        printHelpMessageIfBufferIsEmpty();
        runArgumentParserChain();
    }

    private void runArgumentParserChain() {
        while (!buffer.isFinished()) {
            buffer.next();
            for (ArgumentParser argumentParser : getArgumentParserChain()) {
                if (argumentParser.isResponsible(buffer)) {
                    argumentParser.execute(buffer);
                }
            }
        }
    }

    private void printHelpMessageIfBufferIsEmpty() {
        if (buffer.isFinished()) {
            getInstance().execute(buffer);
        }
    }

    protected ArgumentParser getInstance() {
        return HelpArgumentParser.instance();
    }

    protected List<ArgumentParser> getArgumentParserChain() {
        return ARGUMENT_PARSER_CHAIN;
    }
}
