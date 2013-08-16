package com.buildlight.application;

import org.apache.commons.lang.Validate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zutherb
 */
public class Runner {
    public static void main(String[] args) {

        System.setProperty("spring.profiles.active", "test");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/com/buildlight/spring-context.xml");

        BuildChecker buildChecker = context.getBean(BuildChecker.class);
        Validate.notNull(buildChecker);
        while (true) {

        }
    }
}
