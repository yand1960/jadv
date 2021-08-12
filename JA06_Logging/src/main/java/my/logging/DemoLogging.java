package my.logging;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DemoLogging {
    public static void main(String[] args) throws IOException {
        LogManager
                .getLogManager()
                .readConfiguration(
                        DemoLogging.class.getResourceAsStream("../../logger.properties")
                );

        Logger logger = Logger.getLogger(DemoLogging.class.getName());
        logger.info("It is info");
        logger.severe("I am severe");
    }
}
