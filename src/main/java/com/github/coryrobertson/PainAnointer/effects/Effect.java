package com.github.coryrobertson.PainAnointer.effects;

import com.github.coryrobertson.PainAnointer.Logger;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A parent class used to run code, very abstract
 */
public abstract class Effect
{
    double EffectStartTime;
    double EffectEndTime;
    String EffectName = "DEFAULTNAME";
    public double EffectDuration = 1000;
    double deltaTime = 0;
    int EffectStepCount = 0;

    public Effect()
    {

    }

    /**
     * runs the effect
     * @return true if successfully run
     */
    public boolean RunEffect()
    {
        //initialize all the timing variables
        EffectStartTime = System.currentTimeMillis();
        EffectEndTime = EffectStartTime + EffectDuration;
        double currentTime = System.currentTimeMillis();

        effectStartMessage();

        while(currentTime < EffectEndTime) // run this while loop while the effect is supposed to be running
        {

            deltaTime = (System.currentTimeMillis() - currentTime) / 1000; // calculates deltatime for commmands
            currentTime = System.currentTimeMillis();
            EffectStep();
            EffectStepCount++;
            // delay the running of each step a small amount, so we don't get command compression issues
            try {
                Thread.sleep(0,1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        effectEndMessage();
        return true;
    }

    /**
     * The start message to log when the effect is beginning
     */
    public void effectStartMessage()
    {
        try {
            Logger.log(EffectName + " began with duration of " + EffectDuration + " milis.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The end message to log when the effect is over
     */
    public void effectEndMessage()
    {
        try {
            Logger.log(EffectName + " ended with " + EffectStepCount + " steps.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns a random int between min inclusive and max inclusive
     * @param min inclusive
     * @param max inclusive
     * @return number between min and max
     */
    public int randRange(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }

    /**
     * Called on a while loop often
     * Instance variables like deltaTime and EffectStepCount can be used to make desired effects
     */
    public abstract void EffectStep();

    /**
     * Called once at the beginning of the effect, maybe useful for an effect that should only do something one time at the start
     * Since this is run on a separate thread, this can even be delayed for example
     */
    public abstract void init();
}
