package com.ezangui.gleamer.legacy;

import com.ezangui.gleamer.IGame;
import com.ezangui.gleamer.Player;
import com.ezangui.gleamer.service.PlayerService;

import java.util.LinkedList;

public class Game implements IGame {
    PlayerService playerService ;
    boolean[] inPenaltyBox  = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    boolean isGettingOutOfPenaltyBox;

    public  Game(int boardSize){
        playerService = new PlayerService(boardSize);
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index){
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {


        playerService.addNewPlayer(playerName);

        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + playerService.numberOfPlayers());
        return true;
    }

    public int howManyPlayers() {
        return playerService.numberOfPlayers();
    }

    public void roll(int roll) {
        Player currentPlayer = playerService.currentPlayer();
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer.getId()]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");

                playerService.moveCurrentPlayer(roll);
                System.out.println("The category is " + currentCategory());

                askQuestion();
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            playerService.moveCurrentPlayer(roll);

            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        Player currentPlayer = playerService.currentPlayer();
        if  (currentPlayer.getPlace()== 0) return "Pop";
        if (currentPlayer.getPlace()== 4) return "Pop";
        if (currentPlayer.getPlace()== 8) return "Pop";
        if (currentPlayer.getPlace()== 1) return "Science";
        if (currentPlayer.getPlace()== 5) return "Science";
        if (currentPlayer.getPlace()== 9) return "Science";
        if (currentPlayer.getPlace()== 2) return "Sports";
        if (currentPlayer.getPlace()== 6) return "Sports";
        if (currentPlayer.getPlace()== 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[playerService.currentPlayer().getId()]){
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                playerService.currentPlayerEarnGoldCoin();

                boolean winner = didPlayerWin();

                playerService.nextPlayer();

                return winner;
            } else {

                playerService.nextPlayer();
                return true;
            }



        } else {

            System.out.println("Answer was corrent!!!!");

            playerService.currentPlayerEarnGoldCoin();

            boolean winner = didPlayerWin();
        playerService.nextPlayer();
            return winner;
        }
    }

    public boolean wrongAnswer(){
        System.out.println("Question was incorrectly answered");
        System.out.println(playerService.currentPlayer().getName()+ " was sent to the penalty box");
        inPenaltyBox[playerService.currentPlayer().getId()] = true;
        playerService.nextPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(playerService.currentPlayer().getGoldCollected() == 6);
    }
}
