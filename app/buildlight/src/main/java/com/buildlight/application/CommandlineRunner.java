package com.buildlight.application;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static com.buildlight.application.ConfigurationFile.*;


/**
 * @author zutherb
 */
public final class CommandlineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandlineRunner.class);

    private CommandlineRunner() {/* NOOP */}

    public static void main(String[] args) throws IOException {
        if (CONFIGURATION_FILE.exists()) {
            Properties properties = new Properties();
            properties.load(FileUtils.openInputStream(CONFIGURATION_FILE));

            String builderServer = properties.getProperty("build.server");
            System.setProperty("spring.profiles.active", builderServer.toLowerCase());
            System.setProperty("buildlight.property.file", CONFIGURATION_FILE.getAbsolutePath());

            LOGGER.info(String.format("Buildlight started in %s Mode", builderServer));

            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/com/buildlight/spring-context.xml");

            Scanner scanner = new Scanner(System.in);
            String next = null;
            while (!"exit".equalsIgnoreCase(next)) {
                LOGGER.info("Type 'exit' and press enter to exit the application:");
                next = scanner.next();
            }
            context.destroy();
            scanner.close();
            System.exit(0);
        } else {
            LOGGER.error(String.format("Please create %s\n", CONFIGURATION_FILE.getAbsoluteFile()));
            LOGGER.error("Jenkins example:\n");
            LOGGER.error(JENKINS_DEFAULT_CONFIGURATION);
            LOGGER.error("Bamboo example:\n");
            LOGGER.error(BAMBOO_DEFAULT_CONFIGURATION);
        }
    }
}
