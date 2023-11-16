package com.ezangui.gleamer.legacy;

import java.util.List;
import java.util.Random;

public class LegacyGameRunner {
    public static void run(List<String> playersNames, long seed) {
        LegacyGame game = new LegacyGame();
        for (String name : playersNames) {
            game.add(name);
        }
        Random rand = new Random(seed);
        boolean notAWinner;
        do {
            game.roll(rand.nextInt(5) + 1);
            if (rand.nextInt(9) % 2 == 0) {
                notAWinner = game.wrongAnswer();
            } else {
                notAWinner = game.wasCorrectlyAnswered();
            }
        } while (notAWinner);

    }
}