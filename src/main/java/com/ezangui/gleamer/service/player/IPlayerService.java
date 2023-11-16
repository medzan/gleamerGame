package com.ezangui.gleamer.service.player;

public interface IPlayerService {
    void moveCurrentPlayerBasedOnRoll(int roll);

    boolean addNewPlayer(String playerName);

    int numberOfPlayers();

    void addGoldCoinToCurrentPlayer();

    Player getTurnHolder();

    void advancePlayerTurn();

    void sendPlayerToPenaltyBox(Player player);

    void removeTemporaryPenaltyBoxExitPermission(Player player);

    void grantTemporaryPenaltyBoxExitPermission(Player player);
}
