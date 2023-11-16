package com.ezangui.gleamer;

import com.ezangui.gleamer.configuration.ConfigurationLoader;
import com.ezangui.gleamer.exception.GameInitializationException;
import com.ezangui.gleamer.legacy.LegacyGameRunner;
import com.ezangui.gleamer.service.GameBoardManager;
import com.ezangui.gleamer.service.GameService;
import com.ezangui.gleamer.service.IGameService;
import com.ezangui.gleamer.service.Question.IQuestionGenerator;
import com.ezangui.gleamer.service.Question.IQuestionService;
import com.ezangui.gleamer.service.Question.QuestionGenerator;
import com.ezangui.gleamer.service.Question.QuestionService;
import com.ezangui.gleamer.service.SimpleGameBoardManager;
import com.ezangui.gleamer.service.player.IPlayerService;
import com.ezangui.gleamer.service.player.PlayerService;
import com.ezangui.gleamer.utils.GameplayDisplay;
import com.ezangui.gleamer.utils.PrintStreamGameplayDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class RegressionPreventionTest {

    private final Path legacyOutput = Path.of("src/test/resources/legacy");
    private final long seed = 1000;
    private final File currentVersionOutputFile = Path.of("src/test/resources/current/").resolve(seed + "_" + "current.output.txt").toFile();

    private final GameInitializer gameInitializer;

    public RegressionPreventionTest() throws GameInitializationException, FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(currentVersionOutputFile);
        GameplayDisplay gameplayDisplay = new PrintStreamGameplayDisplay(new PrintStream((fileOutputStream)));
        GameBoardManager simpleGameBoardManager = new SimpleGameBoardManager(ConfigurationLoader.getInstance().loadDefault());
        IPlayerService playerService = new PlayerService(simpleGameBoardManager, gameplayDisplay);
        IQuestionGenerator questionGenerator = new QuestionGenerator();
        IQuestionService questionService = new QuestionService(questionGenerator, ConfigurationLoader.getInstance().loadDefault());
        IGameService gameService = new GameService(ConfigurationLoader.getInstance().loadDefault());
        gameInitializer = new GameInitializer(playerService, questionService, gameplayDisplay, gameService);

    }


    @Test
    public void assertCurrentImplementationOutput_sameAsLegacyOutput() throws Exception {
        List<String> playersNames = Arrays.asList("Mehdi", "Alex", "jorge");
        String expectedContent = generateLegacyOutput(playersNames);
        String actualContent = generateOutput(playersNames);
        Assertions.assertEquals(expectedContent, actualContent);
    }

    private String generateLegacyOutput(List<String> playersNames) throws IOException {
        String fileName = seed + "_" + "legacy.output.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(legacyOutput.resolve(fileName).toFile());
        PrintStream filePrintStream = new PrintStream(fileOutputStream);
        System.setOut(new PrintStream(filePrintStream));
        LegacyGameRunner.run(playersNames, seed);
        return Files.readString(legacyOutput.resolve(fileName));
    }

    private String generateOutput(List<String> playersNames) throws IOException, GameInitializationException, InterruptedException {
        GameRunner.run(gameInitializer, seed, playersNames);
        return Files.readString(currentVersionOutputFile.toPath());
    }


}