package com.ezangui.gleamer.configuration;

final public class DefaultGameConfiguration implements GameConfiguration {
    DefaultGameConfiguration() {
    }

    @Override
    public int getBoardSize() {
        return 12;
    }

    @Override
    public int getMinimumNumberOfPlayers() {
        return 2;
    }

    @Override
    public int getMinimumGoldCoinScoreToWin() {
        return 6;
    }

    @Override
    public int getMinimumBoardSize() {
        return 1;
    }

    @Override
    public int getMaximumBoardSize() {
        return 12;
    }

    @Override
    public int getTotalNumberOfQuestions() {
        return 50;
    }


}
