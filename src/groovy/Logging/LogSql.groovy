package Logging
/**
 * Created by jitin on 25/2/16.
 */
import org.apache.log4j.Level
import org.apache.log4j.Logger

class LogSql {

    public static def executeWithBindings(Closure closure) {
        //Get the SQL and BasicBinder loggers
        Logger sqlLogger = Logger.getLogger("org.hibernate.SQL")
        Logger basicBinderLogger = Logger.getLogger("org.hibernate.type.descriptor.sql.BasicBinder")

        //Store their current levels
        Level currentLevel = sqlLogger.level
        Level basicBinderLevel = basicBinderLogger.level

        //Set their Levels to Trace for fined grained logging
        sqlLogger.setLevel(Level.TRACE)
        basicBinderLogger.setLevel(Level.TRACE)

        //Execute the closure
        def result = closure.call()

        //Reset Levels
        sqlLogger.setLevel(currentLevel)
        basicBinderLogger.setLevel(basicBinderLevel)

        return result
    }
}
