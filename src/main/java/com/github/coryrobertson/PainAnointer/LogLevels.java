package com.github.coryrobertson.PainAnointer;

import java.util.Date;

public enum LogLevels
{

    //if a log level is greater or equal to another, we show it
    ERROR(3), // used for actual scary errors, hopefully never have to use this
    WARN(2), // used for small stuff like not knowing a specific state, but being able to recover
    LOG(1), // used for logging basic messages
    NONE(4);

    private static Date date = new Date(); // not entirely sure i need to initialize this date, but im gonna leave it for a while
    private final int level; // this can be final

    /**
     * Constructor with a number for easy conditionals
     * @param level a number corresponding to this log level
     */
    LogLevels(int level)
    {
        this.level = level;
    }

    /**
     * gets the numerical value of the Log Level
     * @return the numerical value corresponding to this enum
     */
    public int value()
    {
        return this.level;
    }

    /**
     * Gets a formatted string for the given log enum
     * @return "[ERROR]" "[WARN]" "[LOG]" "[UNKNOWN LOG LEVEL]" are all possible strings
     */
    public String getText()
    {
        date = new Date();
        String retn = "";
        switch(this)
        {
            case ERROR:
                retn += "[ERROR]\t";
                break;
            case WARN:
                retn += "[WARN]\t";
                break;
            case LOG:
                retn += "[LOG]\t\t";
                break;
            default:
                retn += "[UNKNOWN LOG LEVEL]\t\t";
                break;
        }
        retn = "[" + date + "]" + retn;
        return retn;
    }
}
