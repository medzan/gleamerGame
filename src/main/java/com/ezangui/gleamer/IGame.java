package com.ezangui.gleamer;

public interface IGame {
    boolean add(String playerName);

    void roll(int dice);

    void wrongAnswer();

    void wasCorrectlyAnswered();

    boolean inProgress();

    void startGame();
}
