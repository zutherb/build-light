package com.comsysto.buildlight.cleware.commandline;

import com.comsysto.buildlight.cleware.commandline.parser.HelpArgumentParser;
import com.comsysto.buildlight.cleware.daemon.ClewareDaemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.apache.commons.lang.ArrayUtils.isEmpty;


/**
 * @author zutherb
 */
public final class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private Runner() { /*NOOP*/ }

    public static void main(String[] args) throws Exception {
        if (!isEmpty(args)) {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/comsysto/buildlight/cleware/spring-cmd-context.xml");
            ClewareDaemon bean = applicationContext.getBean(ClewareDaemon.class);
            LOGGER.info(bean.execute(args));
            applicationContext.close();
        } else {
            LOGGER.info(HelpArgumentParser.MESSAGE);
        }
    }
}
