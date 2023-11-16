package com.ezangui.gleamer.service.player;

import com.ezangui.gleamer.configuration.ConfigurationLoader;
import com.ezangui.gleamer.exception.GameInitializationException;
import com.ezangui.gleamer.service.SimpleGameBoardManager;
import com.ezangui.gleamer.utils.GameplayDisplay;
import com.ezangui.gleamer.utils.PrintStreamGameplayDisplay;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IPlayerServiceTest {
    GameplayDisplay gameplayDisplay = new PrintStreamGameplayDisplay(System.out);
    SimpleGameBoardManager simpleGameBoardManager = new SimpleGameBoardManager(ConfigurationLoader.getInstance().loadDefault());

    IPlayerServiceTest() throws GameInitializationException {
    }


    @Test
    public void whenNewPlayerAdded_currentPlayerHasCorrectInitValues() {
        PlayerService playerService = new PlayerService(simpleGameBoardManager, gameplayDisplay);
        playerService.addNewPlayer("alex");
        playerService.addNewPlayer("mehdi");
        assertEquals(2, playerService.numberOfPlayers());
        assertEquals("alex", playerService.getTurnHolder().getName());
        assertEquals(0, playerService.getTurnHolder().getPosition());
    }

    @Test
    public void whenNextPlayerRequest_ThenPLayerGetTurnsInRobinFashion() {
        PlayerService playerService = new PlayerService(simpleGameBoardManager, gameplayDisplay);
        playerService.addNewPlayer("alex");
        playerService.addNewPlayer("mehdi");
        playerService.addNewPlayer("jorge");

        assertEquals(3, playerService.numberOfPlayers());
        assertEquals("alex", playerService.getTurnHolder().getName());

        playerService.advancePlayerTurn();
        assertEquals("mehdi", playerService.getTurnHolder().getName());
        playerService.advancePlayerTurn();
        assertEquals("jorge", playerService.getTurnHolder().getName());
        playerService.advancePlayerTurn();
        assertEquals("alex", playerService.getTurnHolder().getName());

    }

    @Test
    public void whenGameRequestAMove_thenPlayerMoveWithinTheBoard() {
        PlayerService playerService = new PlayerService(simpleGameBoardManager, gameplayDisplay);
        playerService.addNewPlayer("alex");
        playerService.addNewPlayer("mehdi");

        playerService.moveCurrentPlayerBasedOnRoll(1);
        assertEquals(1, playerService.getTurnHolder().getPosition());
        playerService.moveCurrentPlayerBasedOnRoll(6);
        assertEquals(7, playerService.getTurnHolder().getPosition());
        playerService.moveCurrentPlayerBasedOnRoll(6);
        assertEquals(2, playerService.getTurnHolder().getPosition());

    }
}