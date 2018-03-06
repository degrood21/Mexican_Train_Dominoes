package com.example.degrood21.mexican_train_dominoes;

/**
 * Domino Class
 * <p>
 * creates Dominos with the Drawable id, right side number, and left side number
 *
 * @authors Devin Smith, Dylan DeGrood, Callum Morham, Logan Crawford
 */

public class Domino {

    int pictureID;
    int rightSide;
    int leftSide;

    /**
     * Domino cstor
     * <p>
     * creates the domino according to parameters
     *
     * @param pID   Drawable id
     * @param rSide right side value of domino
     * @param lSide left side value of domino
     */
    public Domino(int pID, int rSide, int lSide) {

        this.pictureID = pID;
        this.rightSide = rSide;
        this.leftSide = lSide;

    }

    public int getPictureID() {
        return this.pictureID;
    }

    public int getRightSide() {
        return this.rightSide;
    }

    public int getLeftSide() {
        return this.leftSide;
    }
}
