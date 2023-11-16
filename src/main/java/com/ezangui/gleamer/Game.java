package com.ezangui.gleamer;

import com.ezangui.gleamer.service.IGameService;
import com.ezangui.gleamer.service.Question.IQuestionService;
import com.ezangui.gleamer.service.player.IPlayerService;
import com.ezangui.gleamer.service.player.Player;
import com.ezangui.gleamer.utils.GameplayDisplay;

import static com.ezangui.gleamer.Game.Status.*;

public class Game implements IGame {

    private final IPlayerService playerService;
    private final IQuestionService questionService;
    private final GameplayDisplay gameplayDisplay;
    private final IGameService gameService;
    private Status status;

    Game(IPlayerService playerService, IQuestionService questionService, GameplayDisplay gameplayDisplay, IGameService gameService) {
        this.playerService = playerService;
        this.gameplayDisplay = gameplayDisplay;
        this.questionService = questionService;
        this.gameService = gameService;
        this.status = INIT;
    }

    public Status setUp() {
        questionService.init();
        this.status = READY_TO_START;
        return status;
    }

    public boolean add(String playerName) {
        return playerService.addNewPlayer(playerName);
    }

    public void roll(int roll) {
        Player currentPlayer = playerService.getTurnHolder();
        gameplayDisplay.displayPostRollInformation(currentPlayer, roll);

        if (currentPlayer.occupiesPenaltyBox()) {
            handlePlayerInPenaltyBox(currentPlayer, roll);
        } else {
            movePlayerOnBoard(currentPlayer, roll);
        }

    }

    private void handlePlayerInPenaltyBox(Player player, int roll) {
        if (gameService.canPlayerEscapePenaltyBoxTemporarily(roll)) {
            playerService.grantTemporaryPenaltyBoxExitPermission(player);
            gameplayDisplay.displayPlayerExitFromPenaltyBox(player);

            movePlayerOnBoard(player, roll);
        } else {
            gameplayDisplay.displayPlayerStackInPenaltyBox(player);
            playerService.removeTemporaryPenaltyBoxExitPermission(player);
        }
    }

    private void movePlayerOnBoard(Player player, int roll) {
        playerService.moveCurrentPlayerBasedOnRoll(roll);
        gameplayDisplay.displayCurrentCategory(questionService.getQuestionCategoryForPlayer(player));
        gameplayDisplay.displayQuestion(questionService.getQuestionForPlayer(player));
    }

    public void wasCorrectlyAnswered() {
        Player currentPlayer = playerService.getTurnHolder();
        if (!currentPlayer.occupiesPenaltyBox() || currentPlayer.holdsTemporaryPenaltyBoxExitPermission()) {
            gameplayDisplay.displayCorrectAnswer();
            playerService.addGoldCoinToCurrentPlayer();
            if (gameService.isCurrentPlayerWinner(currentPlayer)) {
                this.status = FINISHED;
                return;
            }
        }
        playerService.advancePlayerTurn();
    }

    @Override
    public boolean inProgress() {
        return this.status == IN_PROGRESS;
    }

    @Override
    public void startGame() {
        status = IN_PROGRESS;
    }

    public void wrongAnswer() {
        Player player = playerService.getTurnHolder();

        gameplayDisplay.displayIncorrectAnswer();
        gameplayDisplay.displayPlayerEnteringPenaltyBox(player);

        playerService.sendPlayerToPenaltyBox(player);
        playerService.advancePlayerTurn();

    }

    public int howManyPlayers() {
        return playerService.numberOfPlayers();
    }

    public enum Status {
        INIT, READY_TO_START, IN_PROGRESS, FINISHED;
    }


}
