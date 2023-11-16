package com.ezangui.gleamer.service;

public interface GameBoardManager {

    int getPlayerStartPosition();

    int computeNextPosition(int currentPosition, int roll);
}
