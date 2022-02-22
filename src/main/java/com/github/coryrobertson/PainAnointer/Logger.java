package com.github.coryrobertson.PainAnointer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A class that handles logging to a file, you can log messages to a file here, and give a log level such as a log, an error, or a warning
 */
public class Logger
{
    public Logger()
    {

    }

    /**
     * logs a message to a file, as well as prints it to the console
     * @param s message to log
     * @param l Log level
     */
    public static void log(String s, LogLevels l)
    {
        s = l.getText() + s;
        System.out.println(s);
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter out;
        try {
            fw = new FileWriter("log.txt", true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(s);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * logs a message to a file, as well as prints it to the console, also assumes LogLevels.LOG
     * @param s message to log
     */
    public static void log(String s)
    {
        log(s, LogLevels.LOG);
    }
}
