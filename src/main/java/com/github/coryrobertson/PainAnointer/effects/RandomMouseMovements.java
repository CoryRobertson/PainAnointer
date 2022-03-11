package com.github.coryrobertson.PainAnointer.effects;
import com.github.coryrobertson.PainAnointer.EffectTypes;

import java.awt.*;

/**
 * An effect object that randomly pans the mouse around, hopefully convincingly or atleast random
 */
public class RandomMouseMovements extends Effect
{

    //public "option" instance variables for effect functionality
    public double jitterMax = 50;
    public double interval = 500;

    //private instance variables for the effect functionality
    private Robot robot;
    private double jitter = jitterMax;
    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private final int xMax = (int) size.getWidth();
    private final int yMax = (int) size.getHeight();

    public RandomMouseMovements()
    {
        super();

        this.EffectName = "Random Mouse Movements";
        this.effectTypes  = EffectTypes.MOUSE_EFFECT;

        try // robot init
        {
            robot = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void EffectStep()
    {

        Point mp = MouseInfo.getPointerInfo().getLocation();

        int x = rangeClamp(0+100,xMax-100,randRange(mp.x-(int)jitter,mp.x+(int)jitter));
        int y = rangeClamp(0+100,yMax-100,randRange(mp.y-(int) jitter,mp.y+(int)jitter));

        robot.mouseMove(x,y);
        try
        {
            Thread.sleep((long)interval);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        //jitter = Math.abs(Math.sin((double)this.EffectStepCount/250)*jitterMax);
    }

    /**
     * returns a number clamped to a min and maxHoldLength input
     * @param min smallest possible value
     * @param max largest possible value
     * @param input input number
     * @return the clamped result
     */
    private int rangeClamp(int min, int max, int input)
    {
        if(input <= min) return min;
        if(input >= max) return max;
        if(input > min && input < max) return input;
        return -1;
    }

    @Override
    public void init()
    {

    }
}
