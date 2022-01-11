package bootstrap;

import domain.Student;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Driver {

    public static Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args){
        configureLogging("var/log/gitIntro/gitIntro.log","INFO");
        System.out.println("Hello World");
        logger.info("Hello World");

        Student student = new Student();
        student.setName("Laurel");
        student.setRollNumber(1019118L);
        student.setAddress("Mulund East");

        Student student1 = new Student();
        student1.setName("Laurel");
        student1.setRollNumber(-1019118L);
        student1.setAddress("Mulund East");
    }

    public static String configureLogging(String logFile,String logLevel){
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

        String logFilename="";
        switch(logLevel){
            case "DEBUG":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            }
            case "WARN":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.WARN_INT));
            }
            case "ERROR":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.ERROR_INT));
            }
            default:{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            }
            break;
        }
        System.out.println("Log files written out at " + logFile);
        dailyRollingFileAppender.setFile(logFile);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%d [%t] %-5p %c - %m%n"));

        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();
    }
}
