package com.buildlight.mock.bamboo.runner;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zutherb
 */
public final class JettyStart {

    private static final Logger LOGGER = LoggerFactory.getLogger(JettyStart.class);

    private JettyStart() {/*NOOP*/}

    public static void main(final String[] args) {
        if (args.length < 1) {
            System.out.println("JettyStart <httpport>");
            return;
        }

        Server server = new Server(Integer.valueOf(args[0]));
        WebAppContext context = new WebAppContext();
        context.setContextPath("/build-server");
        context.setResourceBase("src/main/webapp/");
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        context.setParentLoaderPriority(true);
        server.setHandler(context);

        try {
            LOGGER.info("JETTY SERVER STARTING NOW ...");
            server.start();
            server.join();
        } catch (Exception e) {
            LOGGER.error("Jetty Server could not be started", e);
            System.exit(100);
        }
    }
}
