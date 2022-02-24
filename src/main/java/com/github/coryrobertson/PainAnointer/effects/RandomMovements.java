package com.github.coryrobertson.PainAnointer.effects;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

/**
 * An effect object that randomly presses W, A, S, D at random intervals
 */
public class RandomMovements extends Effect
{
    Robot robot;
    final int max = 750;

    public RandomMovements()
    {
        super();
        EffectName = "Random Movements";
        EffectDuration = 1000;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void EffectStep()
    {
        switch(randRange(0,3))
        {
            case 0:
                robot.keyPress(KeyEvent.VK_W);
                try {
                    Thread.sleep(randRange(1,max));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyRelease(KeyEvent.VK_W);
                break;
            case 1:
                robot.keyPress(KeyEvent.VK_A);
                try {
                    Thread.sleep(randRange(1,max));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyRelease(KeyEvent.VK_A);
                break;
            case 2:
                robot.keyPress(KeyEvent.VK_S);
                try {
                    Thread.sleep(randRange(1,max));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyRelease(KeyEvent.VK_S);
                break;
            case 3:
                robot.keyPress(KeyEvent.VK_D);
                try {
                    Thread.sleep(randRange(1,max));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                robot.keyRelease(KeyEvent.VK_D);
                break;
        }

    }

    @Override
    public void init() {

    }
}
