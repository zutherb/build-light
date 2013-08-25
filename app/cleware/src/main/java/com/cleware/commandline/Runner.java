package com.cleware.commandline;

import com.cleware.commandline.parser.HelpArgumentParser;
import com.cleware.daemon.ClewareDaemon;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author zutherb
 */
public final class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private Runner() { /*NOOP*/ }

    public static void main(String[] args) throws Exception {
        if (!ArrayUtils.isEmpty(args)) {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/cleware/spring-cmd-context.xml");
            ClewareDaemon bean = applicationContext.getBean(ClewareDaemon.class);
            bean.execute(args);
            applicationContext.close();
        } else {
            LOGGER.info(HelpArgumentParser.MESSAGE);
        }
    }
}
