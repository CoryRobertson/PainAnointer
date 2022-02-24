package com.github.coryrobertson.PainAnointer;

import com.github.coryrobertson.PainAnointer.effects.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.kwhat.jnativehook.*;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;


public class Main implements NativeKeyListener
{
    public static int maxEffectsCount = -1;
    public static int effectTotalCount;

    public static void main(String[] args)
    {

        Logger.setLevel(LogLevels.LOG);

        //Runtime run = Runtime.getRuntime();
        Logger.log("Pain Anointer began running...", LogLevels.LOG);
        Logger.log("Press F1 to end the program.", LogLevels.LOG);

        try { // we wait one second after opening to give the user some time to tab into their desired game/program
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        globalHookKeyboard();

//        RandomMovements rm1 = new RandomMovements();
//        rm1.EffectDuration = 5000;
//        rm1.RunEffect();
//        RandomMovements rm2 = new RandomMovements();
//        rm2.EffectDuration = 5000;

        ArrayList<Thread> threads = new ArrayList<>();
        //threads.add(RunEffectInNewThread(rm1));
        //threads.add(RunEffectInNewThread(rm2));

        //while loop variables
        long current;
        long timeSelectedTime = 0;
        boolean timeSelected = false;
        effectTotalCount = 0;

        //noinspection InfiniteLoopStatement
        while(true)
        {
            current = System.currentTimeMillis(); // update current time

            if(current > timeSelectedTime) // if we are past current time, we need to select another time
            {
                timeSelected = false;
            }

            if(!timeSelected) // if we have not selected a time, we do so
            {
                timeSelected = true;
                timeSelectedTime = current + randRange(1 * 1000, 5 * 1000);
                double calc = (double)(timeSelectedTime - current)/1000;
                Logger.log( calc+ "s till next effect", LogLevels.LOG);

            }

            /*
             when the time is the time we selected to make a new effect,
             we add a new one to the thread pool
             */
            if(current == timeSelectedTime)
            {
                Logger.log("Effect creation time!!!");
                //threads.add(RunEffectInNewThread(new TestEffect()));
                timeSelected = false;
                effectTotalCount++;
                //TODO: add system to add 1-3 COMPATIBLE effects to the thread pool at the same time.

                if (checkArgs(args,0) == -1 && effectTotalCount >= maxEffectsCount) // special case for test class
                {
                    break;
                }

            }

            // thread killer just in case anything is left behind, and we want to remove them, not sure if this is needed
            for(int i = 0; i < threads.size(); i++)
            {
                if(!threads.get(i).isAlive())
                {
                    threads.remove(i);
                    Logger.log("Thread Killed", LogLevels.WARN);
                    break;
                }
            }
        }

    }

    /**
     * A function that simply checks if there is an argument at each given position and return it if we can
     * This is used to offload the try catch cause those are ugly
     * @param args args given to main
     * @param i index to check
     * @return the thing at that index, cast to int, if there is nothing, than a 0 is given
     */
    public static int checkArgs(String[] args, int i)
    {
        try
        {
            return Integer.parseInt(args[i]);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            return 0;
        }
    }

    /**
     * Sets up the keyboard hook, this function simply serves to clean up the main method body
     */
    private static void globalHookKeyboard()
    {
        try { // setting up our keyboard listener
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new Main());
    }

    /**
     * Runs a given effect in a new thread asynchronously.
     * @param effect an effect object
     * @return a thread with the effect running in it
     */
    public static Thread RunEffectInNewThread(Effect effect)
    {
        Thread nt = new Thread(() ->
        {
            //code from effects gets run here
            //effect.init();
            effect.RunEffect();
        });
        nt.start();
        return nt;
    }

    /**
     * random magic for handling key presses
     * @param e native key event
     */
    public void nativeKeyPressed(NativeKeyEvent e)
    {
        if (e.getKeyCode() == NativeKeyEvent.VC_F1)
        {
            gracefulExit();
        }
    }

    /**
     * Gracefully exit the application
     */
    public static void gracefulExit()
    {
        try {
            GlobalScreen.unregisterNativeHook();
            Logger.log("Pain Anointer stopped running...", LogLevels.LOG);
            System.exit(0);
        } catch (NativeHookException e) {
            e.printStackTrace();
        }

    }

    /**
     * get a random integer in the range given
     * @param min minimum number inclusive
     * @param max maximum number inclusive
     * @return randomly generated number
     */
    public static int randRange(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }

}
