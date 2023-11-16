package com.ezangui.gleamer.service;

import com.ezangui.gleamer.configuration.ConfigurationLoader;
import com.ezangui.gleamer.exception.GameInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameBoardManagerTest {

    @Test
    void whenNewPositionRequest_thenComputeNextPositionBasedOnTheBoardSize() throws GameInitializationException {
        GameBoardManager simpleGameBoardManager = new SimpleGameBoardManager(ConfigurationLoader.getInstance().loadDefault());
        int nextPosition = simpleGameBoardManager.computeNextPosition(3, 6);
        Assertions.assertEquals(9, nextPosition);
        nextPosition = simpleGameBoardManager.computeNextPosition(10, 6);
        Assertions.assertEquals(4, nextPosition);
    }
}