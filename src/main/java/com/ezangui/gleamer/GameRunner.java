package com.ezangui.gleamer;

import java.util.Random;

public class GameRunner {

	public static void run(IGame game, Long seed){
		game.add("Alex");
		game.add("Mehdi");

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