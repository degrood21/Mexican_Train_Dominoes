package com.example.degrood21.mexican_train_dominoes;

import java.util.ArrayList;

/**
 *
 * @authors Devin Smith, Dylan DeGrood, Callum Morham, Logan Crawford
 */

public class Hand {
    public ArrayList<Domino> dominoes;

    /**
     * Constructor, creating an empty Hand
     */
    public Hand(){
        dominoes = new ArrayList<Domino>();
    }

    /**
     * copy constructor, making an exact copy of a pile of dominoes
     * @param orig
     *      the hand from which the copy should be made from.
     */
    public Hand(Hand orig){
        //synchronize to ensure the original is not being modified as we iterate through,
        synchronized (orig.dominoes){
            dominoes = new ArrayList<Domino>();
            for(Domino d : orig.dominoes){
                dominoes.add(d);
            }
        }
    }
}
