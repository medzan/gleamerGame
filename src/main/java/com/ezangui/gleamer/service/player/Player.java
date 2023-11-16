package com.ezangui.gleamer.service.player;

public class Player {
    private final int id;
    private final String name;
    private int position;
    private int goldCollected;
    private boolean inPenaltyBox;
    private boolean hasTemporaryPenaltyExitPermission;

    Player(int id, String name, int position, int goldCollected, boolean inPenaltyBox) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.goldCollected = goldCollected;
        this.inPenaltyBox = inPenaltyBox;
        this.hasTemporaryPenaltyExitPermission = !inPenaltyBox;
    }


    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }

    public int getGoldCollected() {
        return goldCollected;
    }

    void setGoldCollected(int goldCollected) {
        this.goldCollected = goldCollected;
    }

    public int getId() {
        return id;
    }

    public boolean occupiesPenaltyBox() {
        return inPenaltyBox;
    }

    void setInPenaltyBox(boolean inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    public boolean holdsTemporaryPenaltyBoxExitPermission() {
        return hasTemporaryPenaltyExitPermission;
    }

    void setHasTemporaryPenaltyExitPermission(boolean hasTemporaryPenaltyExitPermission) {
        this.hasTemporaryPenaltyExitPermission = hasTemporaryPenaltyExitPermission;
    }
}
