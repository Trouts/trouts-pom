package cn.trouts.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TroutsLogUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(TroutsLogUtils.class.getName());

    public static void printLog(Logger logger, String format, Object... arguments) {
        logger.info(format, arguments);
    }

    public static void printLog(String format, Object... arguments) {
        LOGGER.info(format, arguments);
    }

    public static Logger getLogger(Class clazz) {
        return LoggerFactory.getLogger(clazz.getName());
    }


    public static void printExcepiton(Exception e, String format, Object... arguments) {
        LOGGER.error(format, arguments);
        LOGGER.error("", e);
    }


}
