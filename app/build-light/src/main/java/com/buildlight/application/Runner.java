package com.buildlight.application;

import org.apache.commons.lang.Validate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * @author zutherb
 */
public class Runner {
    public static void main(String[] args) {

        System.setProperty("spring.profiles.active", "test,bamboo");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/com/buildlight/spring-context.xml");

        LedSwitcher ledSwitcher = context.getBean(LedSwitcher.class);
        Validate.notNull(ledSwitcher);
        Scanner scanner = new Scanner(System.in);
        while (!"exit".equalsIgnoreCase(scanner.next())) {

        }
        context.destroy();
        scanner.close();
        System.exit(0);
    }
}
