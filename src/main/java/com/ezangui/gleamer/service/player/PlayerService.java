package com.ezangui.gleamer.service.player;

import com.ezangui.gleamer.service.GameBoardManager;
import com.ezangui.gleamer.utils.GameplayDisplay;

import java.util.ArrayList;
import java.util.List;

public class PlayerService implements IPlayerService {
    private static final int NEW_PLAYER_INIT_GOLD_COLLECTION = 0;
    private final GameplayDisplay gameplayDisplay;
    private final List<Player> players;
    private final GameBoardManager gameBoardManager;
    private int currentPlayerIndex = 0;

    public PlayerService(GameBoardManager gameBoardManager, GameplayDisplay gameplayDisplay) {
        this.gameBoardManager = gameBoardManager;
        this.gameplayDisplay = gameplayDisplay;
        this.players = new ArrayList<>();
    }

    @Override
    public void moveCurrentPlayerBasedOnRoll(int roll) {
        getTurnHolder().setPosition(gameBoardManager.computeNextPosition(getTurnHolder().getPosition(), roll));
        gameplayDisplay.displayPlayerNewPositionOnBoard(getTurnHolder());
    }

    @Override
    public boolean addNewPlayer(String playerName) {
        Player newPlayer = new Player(numberOfPlayers(), playerName, gameBoardManager.getPlayerStartPosition(), NEW_PLAYER_INIT_GOLD_COLLECTION, false);
        players.add(newPlayer);
        gameplayDisplay.displayPlayerAddition(newPlayer, numberOfPlayers());
        return true;
    }

    @Override
    public int numberOfPlayers() {
        return players.size();
    }

    @Override
    public void addGoldCoinToCurrentPlayer() {
        getTurnHolder().setGoldCollected(getTurnHolder().getGoldCollected() + 1);
        gameplayDisplay.displayGoldCoinTotal(getTurnHolder());

    }

    @Override
    public Player getTurnHolder() {
        return players.get(currentPlayerIndex);
    }

    @Override
    public void advancePlayerTurn() {
        currentPlayerIndex = currentPlayerIndex == players.size() - 1 ? 0 : currentPlayerIndex + 1;
    }

    @Override
    public void sendPlayerToPenaltyBox(Player player) {
        player.setInPenaltyBox(true);
    }

    @Override
    public void removeTemporaryPenaltyBoxExitPermission(Player player) {
        // Encapsulating all operations in the player service makes it easier to add business rules to detect inconsistencies.
        if (!player.occupiesPenaltyBox()) {
            throw new IllegalStateException("Player cannot be inside the penalty box as he is not eligible for a penalty.");
        }
        player.setHasTemporaryPenaltyExitPermission(false);
    }

    @Override
    public void grantTemporaryPenaltyBoxExitPermission(Player player) {
        if (!player.occupiesPenaltyBox()) {
            throw new IllegalStateException("Player cannot be inside the penalty box as he is not eligible for a penalty.");
        }
        player.setHasTemporaryPenaltyExitPermission(true);
    }
}
