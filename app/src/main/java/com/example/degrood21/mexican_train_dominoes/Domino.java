package com.example.degrood21.mexican_train_dominoes;

import java.io.Serializable;

/**
 * Domino Class
 * <p>
 * creates Dominos with the Drawable id, right side number, and left side number
 *
 * @authors Devin Smith, Dylan DeGrood, Callum Morham, Logan Crawford
 */

public class Domino implements Serializable {
    private static final long serialVersionUID = -4182018123456789L;

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
    public Domino(int pID, int rSide, int lSide) {//sets the dominoes Id, left and right side
        this.pictureID = pID;
        this.rightSide = rSide;
        this.leftSide = lSide;
    }

    /**
     * simple getter method for the pictureID of a domino, it then returns it
     *
     * @return PictureID
     */
    public int getPictureID() {//simple getter method for the Picture ID of a domino
        return this.pictureID;
    }

    /**
     * simple getter method for the RightSide of a domino, it then returns it
     *
     * @return RightSide
     */
    public int getRightSide() {//gets the value of the right side of a domino
        return this.rightSide;
    }

    /**
     * simple getter method for the LeftSide of a domino, it then returns it
     *
     * @return LeftSide
     */
    public int getLeftSide() {//gets the value of the left side of a domino
        return this.leftSide;
    }
}
