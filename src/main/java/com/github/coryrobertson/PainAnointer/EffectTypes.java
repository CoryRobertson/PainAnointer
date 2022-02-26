package com.github.coryrobertson.PainAnointer;

/**
 * A categorization enumeration used to allow the program to know what an effect might do based on its category
 * This file might actually be useless, not sure yet
 */
public enum EffectTypes
{

    KeyboardEffect,
    MouseEffect,
    TestEffect;

    EffectTypes() {}

    /**
     * Might not end up using this very much if at all, wanted to write it just in case
     * @return a random effect type
     */
    public EffectTypes getRandomType()
    {
        EffectTypes[] effectTypes = {EffectTypes.KeyboardEffect, EffectTypes.MouseEffect};

        return effectTypes[Main.randRange(0,effectTypes.length - 1)];
    }

    @Override
    public String toString()
    {
        switch(this)
        {
            case KeyboardEffect:
                return "KeyboardEffect";
            case MouseEffect:
                return "MouseEffect";
            case TestEffect:
                return "TestEffect";
        }
        return null;
    }

}
