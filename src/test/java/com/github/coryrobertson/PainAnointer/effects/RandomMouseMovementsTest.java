package com.github.coryrobertson.PainAnointer.effects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

class RandomMouseMovementsTest {

    @Test
    void runEffectTest()
    {
        Point mp = MouseInfo.getPointerInfo().getLocation();
        int startX = mp.x;
        int startY = mp.y;

        RandomMouseMovements rmm = new RandomMouseMovements();
        rmm.EffectDuration = 125;
        Assertions.assertTrue(rmm.RunEffect());

        mp = MouseInfo.getPointerInfo().getLocation();
        int endX = mp.x;
        int endY = mp.y;

        Assertions.assertTrue(startX != endX);
        Assertions.assertTrue(startY != endY);
    }
}