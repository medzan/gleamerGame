package com.ezangui.gleamer.service;

import com.ezangui.gleamer.configuration.GameConfiguration;
import com.ezangui.gleamer.exception.GameInitializationException;

public class SimpleGameBoardManager implements GameBoardManager {
    private static final int NEW_PLAYER_INIT_PLACE = 0;
    private final GameConfiguration gameConfiguration;

    public SimpleGameBoardManager(GameConfiguration gameConfiguration) throws GameInitializationException {
        this.gameConfiguration = gameConfiguration;
        checkBoardRequirements(gameConfiguration);

    }

    private void checkBoardRequirements(GameConfiguration gameConfiguration) throws GameInitializationException {
        if (gameConfiguration.getBoardSize() < gameConfiguration.getMinimumBoardSize()) {
            throw new GameInitializationException("Board size cannot be less or equal to " + gameConfiguration.getMinimumBoardSize());
        }
        if (gameConfiguration.getBoardSize() > gameConfiguration.getMaximumBoardSize()) {
            throw new GameInitializationException("Board size (" + gameConfiguration.getBoardSize() + ") not supported");
        }
    }

    @Override
    public int getPlayerStartPosition() {
        return NEW_PLAYER_INIT_PLACE;
    }

    @Override
    public int computeNextPosition(int currentPosition, int roll) {
        int boardSize = gameConfiguration.getBoardSize();

        int nextPosition = currentPosition + roll;
        if (nextPosition > (boardSize - 1)) {
            nextPosition -= boardSize;
        }
        return nextPosition;
    }

}
