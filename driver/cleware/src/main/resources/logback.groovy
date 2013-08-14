import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.INFO

def defaultPattern = "%msg%n"

appender("stdout", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = defaultPattern
    }
}

root(INFO, ["stdout"])

