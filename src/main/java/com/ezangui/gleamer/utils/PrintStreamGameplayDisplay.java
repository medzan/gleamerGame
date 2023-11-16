package com.ezangui.gleamer.utils;

import com.ezangui.gleamer.service.Question.Category;
import com.ezangui.gleamer.service.player.Player;

import java.io.PrintStream;

public class PrintStreamGameplayDisplay implements GameplayDisplay {
    private final PrintStream out;

    public PrintStreamGameplayDisplay(PrintStream out) {
        this.out = out;
    }

    @Override
    public void displayPostRollInformation(Player player, int roll) {
        out.println(player.getName() + " is the current player");
        out.println("They have rolled a " + roll);
    }

    @Override
    public void displayPlayerExitFromPenaltyBox(Player player) {
        out.println(player.getName() + " is getting out of the penalty box");

    }

    @Override
    public void displayPlayerStackInPenaltyBox(Player player) {
        out.println(player.getName() + " is not getting out of the penalty box");

    }

    @Override
    public void displayCurrentCategory(Category categoryType) {
        out.println("The category is " + categoryType.name());

    }

    @Override
    public void displayCorrectAnswer() {
        out.println("Answer was correct!!!!");
    }


    @Override
    public void displayIncorrectAnswer() {
        out.println("Question was incorrectly answered");
    }

    @Override
    public void displayPlayerEnteringPenaltyBox(Player player) {
        out.println(player.getName() + " was sent to the penalty box");

    }

    @Override
    public void displayQuestion(String question) {
        out.println(question);
    }

    @Override
    public void displayPlayerNewPositionOnBoard(Player player) {
        out.println(player.getName()
                + "'s new location is "
                + player.getPosition());
    }

    @Override
    public void displayPlayerAddition(Player player, int numberOfPlayers) {
        out.println(player.getName() + " was added");
        out.println("They are player number " + numberOfPlayers);
    }

    @Override
    public void displayGoldCoinTotal(Player player) {
        out.println(player.getName()
                + " now has "
                + player.getGoldCollected()
                + " Gold Coins.");
    }
}
