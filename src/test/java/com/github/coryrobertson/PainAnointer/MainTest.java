package com.github.coryrobertson.PainAnointer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void mainTest()
    {
        String[] args = {"-1"};
        int effectsTestCount = 3;
        try
        {
            Main.maxEffectsCount = effectsTestCount;
            Main.minDurationPick = 5000;
            Main.maxDurationPick = 8000;
            Main.minDurationEffect = 2000;
            Main.maxDurationEffect = 5000;

            Main.main(args);
            Assertions.assertTrue(true);
            Assertions.assertEquals(effectsTestCount, Main.effectTotalCount);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    void checkArgsTest()
    {
        //setting up some examples of what args might look like in a real use case
        String[] args1 = {"-1"};
        String[] args2 = {"0"};
        String[] args3 = {"1"};
        String[] args4 = {"1", "2"};

        // checking valid args inputs
        Assertions.assertEquals(Main.checkArgs(args1,0),-1);
        Assertions.assertEquals(Main.checkArgs(args2,0),0);
        Assertions.assertEquals(Main.checkArgs(args3,0),1);
        Assertions.assertEquals(Main.checkArgs(args4,0),1);
        Assertions.assertEquals(Main.checkArgs(args4,1),2);

        // checking invalid args inputs
        Assertions.assertEquals(Main.checkArgs(args1,1),0);
        Assertions.assertEquals(Main.checkArgs(args2,1),0);
        Assertions.assertEquals(Main.checkArgs(args3,-1),0);
    }
}