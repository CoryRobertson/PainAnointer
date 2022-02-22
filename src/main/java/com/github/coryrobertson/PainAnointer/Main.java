package com.github.coryrobertson.PainAnointer;

import com.github.coryrobertson.PainAnointer.effects.Effect;
import com.github.coryrobertson.PainAnointer.effects.RandomMovements;
import com.github.coryrobertson.PainAnointer.effects.TestEffect;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Runtime run = Runtime.getRuntime();
        Logger.log("Pain Anointer began running...");
        try { // we wait one second after opening to give the user some time to tab into their desired game/program
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        TestEffect eff = new TestEffect();
        eff.RunEffect();
//        RandomMovements rm1 = new RandomMovements();
//        rm1.RunEffect();




        Logger.log("Pain Anointer stopped running...");
    }


}
