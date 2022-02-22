package com.github.coryrobertson.PainAnointer;

import java.awt.Robot;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("test 123");
        Runtime run = Runtime.getRuntime();
        log("this is a test1", LogLevels.LOG);
        log("this is a test2", LogLevels.ERROR);
        log("this is a test3", LogLevels.WARN);
    }

    /**
     * logs a message to a file, as well as prints it to the console
     * @param s message to log
     * @param l Log level
     */
    private static void log(String s, LogLevels l)
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
    private static void log(String s)
    {
        log(s, LogLevels.LOG);
    }
}
