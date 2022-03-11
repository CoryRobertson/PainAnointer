package com.github.coryrobertson.PainAnointer.effects;

import javafx.application.Platform;
import javafx.scene.media.Media;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoundEffectTest {

    @Test
    void init()
    {
        SoundEffect se = new SoundEffect();

        se.RunEffect();
        Assertions.assertTrue(true); // not much to test here since we just play a sound effect, if no exceptions, we pass the test
    }


}