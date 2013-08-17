package com.buildlight.application;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static com.buildlight.application.ConfigurationFile.CONFIGURATION_FILE;
import static org.apache.commons.io.FileUtils.readFileToString;


/**
 * @author zutherb
 */
public final class CommandlineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandlineRunner.class);
    public static final ClassPathResource JENKINS_EXAMPLE_PROPERTY_FILE = new ClassPathResource("/com/buildlight/buildlight.jenkins.properties");
    public static final ClassPathResource BAMBOO_EXAMPLE_PROPERTY_FILE = new ClassPathResource("/com/buildlight/buildlight.bamboo.properties");

    private CommandlineRunner() {/* NOOP */}

    public static void main(String[] args) throws IOException {

        //System.setProperty("jenkins.server.url", "http://h1994633.stratoserver.net:9090");
        //System.setProperty("jenkins.build.name", "Build-Light-Test-Build");
        // System.setProperty("spring.profiles.active", "test,jenkins");

        if (CONFIGURATION_FILE.exists()) {
            Properties properties = new Properties();
            properties.load(FileUtils.openInputStream(CONFIGURATION_FILE));

            System.setProperty("spring.profiles.active", properties.getProperty("build.server").toLowerCase());
            System.setProperty("buildlight.property.file", CONFIGURATION_FILE.getAbsolutePath());
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
            LOGGER.error(String.format("Please create %s", CONFIGURATION_FILE.getAbsoluteFile()));
            LOGGER.error("Jenkins example:");
            LOGGER.error(readFileToString(JENKINS_EXAMPLE_PROPERTY_FILE.getFile()));
            LOGGER.error("Bamboo example:");
            LOGGER.error(readFileToString(BAMBOO_EXAMPLE_PROPERTY_FILE.getFile()));
        }
    }
}
