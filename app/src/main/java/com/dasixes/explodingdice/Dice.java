package com.dasixes.explodingdice;

import java.util.Random;

/**
 * Created by cdavis on 1/17/2015.
 */
public class Dice {
    public int Ones=0;
    public int Sixes=0;
    public int Hits=0;
    public int Sum=0;
    public int Number=0;

    public int rollDice(int Number, boolean Exploding) {
        return rollDice(Number, Exploding, -1);
    }

    public int rollDice(int Amount, boolean Exploding, int Limit) {
        Hits = 0;
        Sixes=0;
        Ones=0;
        Sum=0;
        Number=Amount;
        Random randomGenerator = new Random();
        for (int idx = 1; idx <= Amount; ++idx) {
            int randomInt = randomGenerator.nextInt(6) + 1;
            Sum+=randomInt;
            switch (randomInt) {
                case 1:
                    Ones++;
                    break;
                case 5:
                    Hits++;
                    break;
                case 6:
                    Hits++;
                    if(Exploding){--idx;}else{Sixes++;}
                    break;
            }
        }
        if(Limit>0 && Hits>Limit){Hits=Limit;}
//TODO: Glitches
        return Hits;
    }

    public int postEdge(){

        int oldHits=Hits;
        int oldSixes=Sixes;
        int oldNumber=Number;

        rollDice((Number-Hits), false);
        Hits+=oldHits;
        Number=oldNumber;
        Sixes+=oldSixes;

        return Hits;
    }
}
