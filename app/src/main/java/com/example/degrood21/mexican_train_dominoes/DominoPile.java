package com.example.degrood21.mexican_train_dominoes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @authors Devin Smith, Dylan DeGrood, Callum Morham, Logan Crawford
 */

public class DominoPile implements Serializable{
    private static final long serialVersionUID = -4182018123456719L;

    public ArrayList<Domino> setOfDominoes = new ArrayList<Domino>();//creates an arraylist for the dominoes

    Domino d0_0 = new Domino(R.drawable.c0_0, 0, 0);//creating the domino and setting its sides to values
    Domino d1_1 = new Domino(R.drawable.c1_1, 1, 1);
    Domino d2_2 = new Domino(R.drawable.c2_2, 2, 2);
    Domino d3_3 = new Domino(R.drawable.c3_3, 3, 3);
    Domino d4_4 = new Domino(R.drawable.c4_4, 4, 4);
    Domino d5_5 = new Domino(R.drawable.c5_5, 5, 5);
    Domino d6_6 = new Domino(R.drawable.c6_6, 6, 6);
    Domino d7_7 = new Domino(R.drawable.c7_7, 7, 7);
    Domino d8_8 = new Domino(R.drawable.c8_8, 8, 8);
    Domino d9_9 = new Domino(R.drawable.c9_9, 9, 9);
    Domino d10_10 = new Domino(R.drawable.c10_10, 10, 10);
    Domino d11_11 = new Domino(R.drawable.c11_11, 11, 11);
    Domino d12_12 = new Domino(R.drawable.c12_12, 12, 12);

    Domino d0_1 = new Domino(R.drawable.d0_1, 0, 1);
    Domino d0_2 = new Domino(R.drawable.d0_2, 0, 2);
    Domino d0_3 = new Domino(R.drawable.d0_3, 0, 3);
    Domino d0_4 = new Domino(R.drawable.d0_4, 0, 4);
    Domino d0_5 = new Domino(R.drawable.d0_5, 0, 5);
    Domino d0_6 = new Domino(R.drawable.d0_6, 0, 6);
    Domino d0_7 = new Domino(R.drawable.d0_7, 0, 7);
    Domino d0_8 = new Domino(R.drawable.d0_8, 0, 8);
    Domino d0_9 = new Domino(R.drawable.d0_9, 0, 9);
    Domino d0_10 = new Domino(R.drawable.d0_10, 0, 10);
    Domino d0_11 = new Domino(R.drawable.d0_11, 0, 11);
    Domino d0_12 = new Domino(R.drawable.d0_12, 0, 12);

    Domino d1_2 = new Domino(R.drawable.d1_2, 1, 2);
    Domino d1_3 = new Domino(R.drawable.d1_3, 1, 3);
    Domino d1_4 = new Domino(R.drawable.d1_4, 1, 4);
    Domino d1_5 = new Domino(R.drawable.d1_5, 1, 5);
    Domino d1_6 = new Domino(R.drawable.d1_6, 1, 6);
    Domino d1_7 = new Domino(R.drawable.d1_7, 1, 7);
    Domino d1_8 = new Domino(R.drawable.d1_8, 1, 8);
    Domino d1_9 = new Domino(R.drawable.d1_9, 1, 9);
    Domino d1_10 = new Domino(R.drawable.d1_10, 1, 10);
    Domino d1_11 = new Domino(R.drawable.d1_11, 1, 11);
    Domino d1_12 = new Domino(R.drawable.d1_12, 1, 12);

    Domino d2_3 = new Domino(R.drawable.d2_3, 2, 3);
    Domino d2_4 = new Domino(R.drawable.d2_4, 2, 4);
    Domino d2_5 = new Domino(R.drawable.d2_5, 2, 5);
    Domino d2_6 = new Domino(R.drawable.d2_6, 2, 6);
    Domino d2_7 = new Domino(R.drawable.d2_7, 2, 7);
    Domino d2_8 = new Domino(R.drawable.d2_8, 2, 8);
    Domino d2_9 = new Domino(R.drawable.d2_9, 2, 9);
    Domino d2_10 = new Domino(R.drawable.d2_10, 2, 10);
    Domino d2_11 = new Domino(R.drawable.d2_11, 2, 11);
    Domino d2_12 = new Domino(R.drawable.d2_12, 2, 12);

    Domino d3_4 = new Domino(R.drawable.d3_4, 3, 4);
    Domino d3_5 = new Domino(R.drawable.d3_5, 3, 5);
    Domino d3_6 = new Domino(R.drawable.d3_6, 3, 6);
    Domino d3_7 = new Domino(R.drawable.d3_7, 3, 7);
    Domino d3_8 = new Domino(R.drawable.d3_8, 3, 8);
    Domino d3_9 = new Domino(R.drawable.d3_9, 3, 9);
    Domino d3_10 = new Domino(R.drawable.d3_10, 3, 10);
    Domino d3_11 = new Domino(R.drawable.d3_11, 3, 11);
    Domino d3_12 = new Domino(R.drawable.d3_12, 3, 12);

    Domino d4_5 = new Domino(R.drawable.d4_5, 4, 5);
    Domino d4_6 = new Domino(R.drawable.d4_6, 4, 6);
    Domino d4_7 = new Domino(R.drawable.d4_7, 4, 7);
    Domino d4_8 = new Domino(R.drawable.d4_8, 4, 8);
    Domino d4_9 = new Domino(R.drawable.d4_9, 4, 9);
    Domino d4_10 = new Domino(R.drawable.d4_10, 4, 10);
    Domino d4_11 = new Domino(R.drawable.d4_11, 4, 11);
    Domino d4_12 = new Domino(R.drawable.d4_12, 4, 12);

    Domino d5_6 = new Domino(R.drawable.d5_6, 5, 6);
    Domino d5_7 = new Domino(R.drawable.d5_7, 5, 7);
    Domino d5_8 = new Domino(R.drawable.d5_8, 5, 8);
    Domino d5_9 = new Domino(R.drawable.d5_9, 5, 9);
    Domino d5_10 = new Domino(R.drawable.d5_10, 5, 10);
    Domino d5_11 = new Domino(R.drawable.d5_11, 5, 11);
    Domino d5_12 = new Domino(R.drawable.d5_12, 5, 12);

    Domino d6_7 = new Domino(R.drawable.d6_7, 6, 7);
    Domino d6_8 = new Domino(R.drawable.d6_8, 6, 8);
    Domino d6_9 = new Domino(R.drawable.d6_9, 6, 9);
    Domino d6_10 = new Domino(R.drawable.d6_10, 6, 10);
    Domino d6_11 = new Domino(R.drawable.d6_11, 6, 11);
    Domino d6_12 = new Domino(R.drawable.d6_12, 6, 12);

    Domino d7_8 = new Domino(R.drawable.d7_8, 7, 8);
    Domino d7_9 = new Domino(R.drawable.d7_9, 7, 9);
    Domino d7_10 = new Domino(R.drawable.d7_10, 7, 10);
    Domino d7_11 = new Domino(R.drawable.d7_11, 7, 11);
    Domino d7_12 = new Domino(R.drawable.d7_12, 7, 12);

    Domino d8_9 = new Domino(R.drawable.d8_9, 8, 9);
    Domino d8_10 = new Domino(R.drawable.d8_10, 8, 10);
    Domino d8_11 = new Domino(R.drawable.d8_11, 8, 11);
    Domino d8_12 = new Domino(R.drawable.d8_12, 8, 12);

    Domino d9_10 = new Domino(R.drawable.d9_10, 9, 10);
    Domino d9_11 = new Domino(R.drawable.d9_11, 9, 11);
    Domino d9_12 = new Domino(R.drawable.d9_12, 9, 12);

    Domino d10_11 = new Domino(R.drawable.d10_11, 10, 11);
    Domino d10_12 = new Domino(R.drawable.d10_12, 10, 12);

    Domino d11_12 = new Domino(R.drawable.d11_12, 11, 12);


    public DominoPile() {//adding all the dominoes to the arraylist

        setOfDominoes.add(d0_0);
        setOfDominoes.add(d1_1);
        setOfDominoes.add(d2_2);
        setOfDominoes.add(d3_3);
        setOfDominoes.add(d4_4);
        setOfDominoes.add(d5_5);
        setOfDominoes.add(d6_6);
        setOfDominoes.add(d7_7);
        setOfDominoes.add(d8_8);
        setOfDominoes.add(d9_9);
        setOfDominoes.add(d10_10);
        setOfDominoes.add(d11_11);
        setOfDominoes.add(d12_12);

        setOfDominoes.add(d0_1);
        setOfDominoes.add(d0_2);
        setOfDominoes.add(d0_3);
        setOfDominoes.add(d0_4);
        setOfDominoes.add(d0_5);
        setOfDominoes.add(d0_6);
        setOfDominoes.add(d0_7);
        setOfDominoes.add(d0_8);
        setOfDominoes.add(d0_9);
        setOfDominoes.add(d0_10);
        setOfDominoes.add(d0_11);
        setOfDominoes.add(d0_12);

        setOfDominoes.add(d1_2);
        setOfDominoes.add(d1_3);
        setOfDominoes.add(d1_4);
        setOfDominoes.add(d1_5);
        setOfDominoes.add(d1_6);
        setOfDominoes.add(d1_7);
        setOfDominoes.add(d1_8);
        setOfDominoes.add(d1_9);
        setOfDominoes.add(d1_10);
        setOfDominoes.add(d1_11);
        setOfDominoes.add(d1_12);

        setOfDominoes.add(d2_3);
        setOfDominoes.add(d2_4);
        setOfDominoes.add(d2_5);
        setOfDominoes.add(d2_6);
        setOfDominoes.add(d2_7);
        setOfDominoes.add(d2_8);
        setOfDominoes.add(d2_9);
        setOfDominoes.add(d2_10);
        setOfDominoes.add(d2_11);
        setOfDominoes.add(d2_12);

        setOfDominoes.add(d3_4);
        setOfDominoes.add(d3_5);
        setOfDominoes.add(d3_6);
        setOfDominoes.add(d3_7);
        setOfDominoes.add(d3_8);
        setOfDominoes.add(d3_9);
        setOfDominoes.add(d3_10);
        setOfDominoes.add(d3_11);
        setOfDominoes.add(d3_12);

        setOfDominoes.add(d4_5);
        setOfDominoes.add(d4_6);
        setOfDominoes.add(d4_7);
        setOfDominoes.add(d4_8);
        setOfDominoes.add(d4_9);
        setOfDominoes.add(d4_10);
        setOfDominoes.add(d4_11);
        setOfDominoes.add(d4_12);

        setOfDominoes.add(d5_6);
        setOfDominoes.add(d5_7);
        setOfDominoes.add(d5_8);
        setOfDominoes.add(d5_9);
        setOfDominoes.add(d5_10);
        setOfDominoes.add(d5_11);
        setOfDominoes.add(d5_12);

        setOfDominoes.add(d6_7);
        setOfDominoes.add(d6_8);
        setOfDominoes.add(d6_9);
        setOfDominoes.add(d6_10);
        setOfDominoes.add(d6_11);
        setOfDominoes.add(d6_12);

        setOfDominoes.add(d7_8);
        setOfDominoes.add(d7_9);
        setOfDominoes.add(d7_10);
        setOfDominoes.add(d7_11);
        setOfDominoes.add(d7_12);

        setOfDominoes.add(d8_9);
        setOfDominoes.add(d8_10);
        setOfDominoes.add(d8_11);
        setOfDominoes.add(d8_12);

        setOfDominoes.add(d9_10);
        setOfDominoes.add(d9_11);
        setOfDominoes.add(d9_12);

        setOfDominoes.add(d10_11);
        setOfDominoes.add(d10_12);

        setOfDominoes.add(d11_12);

    }
}
