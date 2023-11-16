package com.ezangui.gleamer.utils;

import com.ezangui.gleamer.service.Question.Category;
import com.ezangui.gleamer.service.player.Player;

public interface GameplayDisplay {
    void displayPostRollInformation(Player player, int roll);

    void displayPlayerExitFromPenaltyBox(Player player);

    void displayPlayerStackInPenaltyBox(Player player);

    void displayCurrentCategory(Category categoryType);

    void displayCorrectAnswer();

    void displayIncorrectAnswer();

    void displayPlayerEnteringPenaltyBox(Player player);

    void displayQuestion(String question);

    void displayPlayerNewPositionOnBoard(Player player);

    void displayPlayerAddition(Player player, int numberOfPlayers);

    void displayGoldCoinTotal(Player player);
}
