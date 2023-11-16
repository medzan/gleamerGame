package com.ezangui.gleamer;

import com.ezangui.gleamer.configuration.ConfigurationLoader;
import com.ezangui.gleamer.configuration.GameConfiguration;
import com.ezangui.gleamer.exception.GameInitializationException;
import com.ezangui.gleamer.service.GameBoardManager;
import com.ezangui.gleamer.service.GameService;
import com.ezangui.gleamer.service.IGameService;
import com.ezangui.gleamer.service.Question.IQuestionService;
import com.ezangui.gleamer.service.Question.QuestionGenerator;
import com.ezangui.gleamer.service.Question.QuestionService;
import com.ezangui.gleamer.service.SimpleGameBoardManager;
import com.ezangui.gleamer.service.player.PlayerService;
import com.ezangui.gleamer.utils.GameplayDisplay;
import com.ezangui.gleamer.utils.PrintStreamGameplayDisplay;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameRunner {

    public static void main(String[] args) throws GameInitializationException, InterruptedException {
        GameConfiguration gameConfiguration = ConfigurationLoader.getInstance().loadDefault();
        IQuestionService questionService = new QuestionService(new QuestionGenerator(), gameConfiguration);
        GameplayDisplay gameplayDisplay = new PrintStreamGameplayDisplay(System.out);
        IGameService gameService = new GameService(gameConfiguration);
        GameBoardManager simpleGameBoardManager = new SimpleGameBoardManager(gameConfiguration);
        PlayerService playerService = new PlayerService(simpleGameBoardManager, gameplayDisplay);

        GameInitializer gameInitializer = new GameInitializer(playerService, questionService, gameplayDisplay, gameService);

        run(gameInitializer, 1000L, Arrays.asList("Mehdi", "Alex"));
    }

    public static void run(GameInitializer gameInitializer, Long seed, List<String> playersNames) throws GameInitializationException, InterruptedException {
        for (String name : playersNames) {
            gameInitializer.add(name);
        }
        Random rand = new Random(seed);
        IGame game = gameInitializer.newGame();
        do {
            game.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) % 2 == 0) {
                game.wrongAnswer();
            } else {
                game.wasCorrectlyAnswered();
            }
        } while (game.inProgress());
    }
}