package com.ezangui.gleamer;

public interface IGame {
    boolean add(String alex);

    void roll(int dice);

    boolean wrongAnswer();

    boolean wasCorrectlyAnswered();
}
