package com.comsysto.buildlight.application;

import com.comsysto.buildlight.arduino.driver.Arduino;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static com.comsysto.buildlight.application.gui.ConfigurationFile.*;


/**
 * @author zutherb
 */
public final class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private Runner() {/* NOOP */}

    public static void main(String[] args) throws IOException {
        LOGGER.info("Build Light - Build Watch Application, Maintained by B. Zuther.\n" +
                "Send bug reports using https://github.com/comsysto/build-light/issues\n");
        if (CONFIGURATION_FILE.exists()) {
            Properties properties = new Properties();
            properties.load(FileUtils.openInputStream(CONFIGURATION_FILE));

            String builderServer = properties.getProperty("build.server");
            System.setProperty("spring.profiles.active", builderServer.toLowerCase());
            System.setProperty("buildlight.property.file", CONFIGURATION_FILE.getAbsolutePath());

            LOGGER.info(String.format("Buildlight started in %s Mode", builderServer));

            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/com/comsysto/buildlight/application/spring-context.xml");

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
            LOGGER.error("Arduino Factory:\n");
            LOGGER.error("traffic.light.factory=com.comsysto.buildlight.application.factory.ArduinoDriverFactoryBean\n");
            LOGGER.error("Available Serial Port:\n");
            String[] portList = Arduino.list();
            LOGGER.error("");
            for (String port : portList) {
                LOGGER.error(port);
            }
        }
    }
}
