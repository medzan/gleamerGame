package com.ezangui.gleamer.service;

import com.ezangui.gleamer.configuration.GameConfiguration;
import com.ezangui.gleamer.exception.GameInitializationException;
import com.ezangui.gleamer.service.player.Player;

public class GameService implements IGameService {

    private final GameConfiguration gameConfiguration;

    public GameService(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
    }

    @Override
    public boolean canPlayerEscapePenaltyBoxTemporarily(int roll) {
        return roll % 2 != 0;
    }

    @Override
    public boolean isCurrentPlayerWinner(Player player) {
        return player.getGoldCollected() >= gameConfiguration.getMinimumGoldCoinScoreToWin();
    }

    @Override
    public boolean checkInitializationRequirement(int numberOfPlayers) throws GameInitializationException {
        if (numberOfPlayers < gameConfiguration.getMinimumNumberOfPlayers()) {
            throw new GameInitializationException("Game initialization failed due to numbers of players less then the minimum required.");
        }
        return true;
    }

}
