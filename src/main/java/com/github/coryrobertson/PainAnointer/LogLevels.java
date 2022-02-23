package com.github.coryrobertson.PainAnointer;

import java.util.Date;

public enum LogLevels
{

    ERROR, // used for actual scary errors, hopefully never have to use this
    WARN, // used for small stuff like not knowing a specific state, but being able to recover
    LOG; // used for logging basic messages

    private static Date date = new Date();

    public String getText()
    {
        Date date = new Date();
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
