package com.ezangui.gleamer;

import com.ezangui.gleamer.exception.GameInitializationException;
import com.ezangui.gleamer.service.IGameService;
import com.ezangui.gleamer.service.Question.IQuestionService;
import com.ezangui.gleamer.service.player.IPlayerService;
import com.ezangui.gleamer.utils.GameplayDisplay;

public class GameInitializer {
    private final IPlayerService playerService;
    private final IQuestionService questionService;
    private final GameplayDisplay gameplayDisplay;
    private final IGameService gameService;

    GameInitializer(IPlayerService playerService, IQuestionService questionService, GameplayDisplay gameplayDisplay, IGameService gameService) {
        this.playerService = playerService;
        this.gameplayDisplay = gameplayDisplay;
        this.questionService = questionService;
        this.gameService = gameService;

    }

    public GameInitializer add(String playerName) {
        playerService.addNewPlayer(playerName);
        return this;
    }

    public Game newGame() throws GameInitializationException {
        gameService.checkInitializationRequirement(playerService.numberOfPlayers());
        Game game = new Game(playerService, questionService, gameplayDisplay, gameService);
        Game.Status status = game.setUp();
        if (status != Game.Status.READY_TO_START) {
            throw new IllegalStateException();
        }
        game.startGame();
        return game;
    }


}
