package com.github.coryrobertson.PainAnointer;

import com.github.coryrobertson.PainAnointer.effects.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.kwhat.jnativehook.*;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

/**
 * The main class that handles running the application
 */
public class Main implements NativeKeyListener
{

    public static int maxEffectsCount = -1; // how many effects to run before the program auto closes, -1 for infinite only works in test mode cause main(args) = [0] = "-1"
    public static int effectTotalCount; // the running count of how many effects have been generated
    public static int minDurationPick = 5000; // minimum duration of wait time to pick a new effect in milis
    public static int maxDurationPick = 8000; // maximum duration of wait time to pick a new effect in milis
    public static int minDurationEffect = 1000; // minimum duration of effect in milis
    public static int maxDurationEffect = 5000; // maximum duration of effect in milis

    public static boolean pauseEffects = false;

    public static void main(String[] args)
    {

        Logger.setLevel(LogLevels.LOG);

        //Runtime run = Runtime.getRuntime();
        Logger.log("Pain Anointer began running...", LogLevels.LOG);
        Logger.log("Press F1 to end the program.", LogLevels.LOG);

        waitTime(1000);

        globalHookKeyboard();

        ArrayList<Thread> threads = new ArrayList<>(); // thread arraylist just in case ;)

        //while loop variables
        long current;
        long timeSelectedTime = 0;
        boolean timeSelected = false;
        effectTotalCount = 0;

        while(true)
        {
            while(pauseEffects) // this pause is super hacky and barely works, throws a ton of exceptions, basically can't make it work lmao, ill try later
            {
                for (Thread t: threads)
                {
                 t.interrupt(); // "whale they'er is ur problem!"
                }
                threads.clear();
                waitTime(1000);
                Logger.log("Pain Anointer paused, press F3 to unpause", LogLevels.LOG);
            }

            current = System.currentTimeMillis(); // update current time

            if(current > timeSelectedTime) // if we are past current time, we need to select another time
            {
                timeSelected = false;
            }

            if(!timeSelected) // if we have not selected a time, we do so
            {
                timeSelected = true;
                timeSelectedTime = current + randRange(minDurationPick,maxDurationPick);
                double calc = (double)(timeSelectedTime - current)/1000;
                Logger.log( calc+ "s till next effect", LogLevels.LOG);

            }

            /*
             when the time is the time we selected to make a new effect,
             we add a new one to the thread pool
             */
            if(current == timeSelectedTime)
            {
                //this is where we add new effects to the thread pool
                Logger.log("Effect creation time!!!",LogLevels.LOG);
                Effect eff = getRandomEffect();
                eff.setEffectDurationRandom(minDurationEffect,maxDurationEffect);
                threads.add(RunEffectInNewThread(eff));
                timeSelected = false;
                effectTotalCount++;

                Logger.log("Total effects created this session: " + effectTotalCount, LogLevels.LOG);

                if (checkArgs(args,0) == -1 && effectTotalCount >= maxEffectsCount) // special case for test class
                {
                    break;
                }
            }
            for(int i = 0; i < threads.size(); i++) // thread killer just in case anything is left behind, and we want to remove them, not sure if this is needed
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
     * macro for waiting without having to make a try catch block cause typing those out annoy me
     * @param milis time in milis to wait
     */
    private static void waitTime(int milis)
    {
        try
        {
            Thread.sleep(milis);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
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
        if (e.getKeyCode() == NativeKeyEvent.VC_F3)
        {
            pauseEffects = !pauseEffects;
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

    /**
     * Returns a random effect object
     * @return a random effect object
     */
    public static Effect getRandomEffect()
    {
        int effectCount = 2; // RandomKeyboardMovements, RandomMouseMovements
        EffectTypes[] effectTypes = {EffectTypes.MouseEffect, EffectTypes.KeyboardEffect};

        switch(randRange(0,effectCount-1))
        {
            case 0:
                return new RandomKeyboardMovements();
            case 1:
                return new RandomMouseMovements();
        }

        return null; // hopefully this doesn't happen!
    }

}
