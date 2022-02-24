package com.github.coryrobertson.PainAnointer.effects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestEffectTest {

    @Test
    void runEffect()
    {
        TestEffect te = new TestEffect();
        te.EffectDuration = 1000;
        Assertions.assertTrue(te.RunEffect());
    }
}