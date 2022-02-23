package com.github.coryrobertson.PainAnointer;

import com.github.coryrobertson.PainAnointer.effects.*;
import java.io.IOException;
import com.github.kwhat.jnativehook.*;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Main implements NativeKeyListener
{
    public static void main(String[] args) throws IOException
    {
        Runtime run = Runtime.getRuntime();
        Logger.log("Pain Anointer began running...");

        try { // we wait one second after opening to give the user some time to tab into their desired game/program
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new Main());



        TestEffect eff = new TestEffect();
        eff.RunEffect();
        RandomMovements rm1 = new RandomMovements();
        rm1.EffectDuration = 10000;
        rm1.RunEffect();


        Logger.log("Pain Anointer stopped running...");
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
                System.exit(0);
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }
}
