package com.github.coryrobertson.PainAnointer;

/**
 * A categorization enumeration used to allow the program to know what an effect might do based on its category
 * This file might actually be useless, not sure yet
 */
public enum EffectTypes
{

    KEYBOARD_EFFECT,
    MOUSE_EFFECT,
    SOUND_EFFECT,
    TEST_EFFECT;

    EffectTypes() {}

    /**
     * Might not end up using this very much if at all, wanted to write it just in case
     * @return a random effect type
     */
    public EffectTypes getRandomType()
    {
        EffectTypes[] effectTypes = {EffectTypes.KEYBOARD_EFFECT, EffectTypes.MOUSE_EFFECT,EffectTypes.SOUND_EFFECT};

        return effectTypes[Main.randRange(0,effectTypes.length - 1)];
    }

    @Override
    public String toString()
    {
        switch(this)
        {
            case KEYBOARD_EFFECT:
                return "KeyboardEffect";
            case MOUSE_EFFECT:
                return "MouseEffect";
            case SOUND_EFFECT:
                return "SoundEffect";
            case TEST_EFFECT:
                return "TestEffect";
        }
        return null;
    }

}
