package com.ezangui.gleamer.service;

import com.ezangui.gleamer.exception.GameInitializationException;
import com.ezangui.gleamer.service.player.Player;

public interface IGameService {
    boolean canPlayerEscapePenaltyBoxTemporarily(int roll);

    boolean isCurrentPlayerWinner(Player player);

    boolean checkInitializationRequirement(int numberOfPlayers) throws GameInitializationException;
}
