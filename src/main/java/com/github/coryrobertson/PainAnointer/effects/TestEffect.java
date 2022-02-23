package com.github.coryrobertson.PainAnointer.effects;

public class TestEffect extends Effect
{

    public TestEffect()
    {
        super();
        EffectName = "TestEffect";
    }

    @Override
    public void EffectStep()
    {
        System.out.println("This is a test effect step: " + this);
    }

    @Override
    public void init() {

    }
}
