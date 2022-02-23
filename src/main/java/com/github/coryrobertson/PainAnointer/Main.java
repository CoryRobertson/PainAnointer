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
    public static void main(String[] args) throws IOException
    {
        Runtime run = Runtime.getRuntime();
        Logger.log("Pain Anointer began running...", LogLevels.LOG);
        Logger.log("Press F1 to end the program.", LogLevels.LOG);

        try { // we wait one second after opening to give the user some time to tab into their desired game/program
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try { // setting up our keyboard listener
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new Main());

//        RandomMovements rm1 = new RandomMovements();
//        rm1.EffectDuration = 1000;
//        RandomMovements rm2 = new RandomMovements();
//        rm2.EffectDuration = 5000;

        ArrayList<Thread> threads = new ArrayList<>();
        //threads.add(RunEffectInNewThread(rm1));
        //threads.add(RunEffectInNewThread(rm2));

        long current;
        long timeSelectedTime = 0;
        boolean timeSelected = false;
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
                double calc = (current - timeSelectedTime)/1000;
                Logger.log( calc+ "s till next effect");

            }

            /*
             when the time is the time we selected to make a new effect,
             we add a new one to the thread pool
             */
            if(current == timeSelectedTime)
            {
                Logger.log("Effect creation time!!!");
                threads.add(RunEffectInNewThread(new TestEffect()));
                timeSelected = false;

            }

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

    public static Thread RunEffectInNewThread(Effect effect)
    {
        Thread nt = new Thread(() -> {
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
        if (e.getKeyCode() == NativeKeyEvent.VC_F1) {
            try {
                GlobalScreen.unregisterNativeHook();
                Logger.log("Pain Anointer stopped running...", LogLevels.LOG);
                System.exit(0);

            } catch (NativeHookException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static int randRange(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }

}
