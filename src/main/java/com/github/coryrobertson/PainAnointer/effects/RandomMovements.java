package com.github.coryrobertson.PainAnointer.effects;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

public class RandomMovements extends Effect
{
    Robot robot;
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

        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
    }
}
