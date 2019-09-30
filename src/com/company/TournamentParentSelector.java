package com.company;

import java.sql.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class TournamentParentSelector extends ParentSelector {

    int amount;

    public TournamentParentSelector(int value) {
        amount = value;
    }

    @Override
    int select(Individual[] value) {
        int bestIndex;
        Random r = Main.raandomNumber;
        bestIndex = r.nextInt(value.length);
        for (int i = 1; i < amount; i++) {
            int randomIndex = r.nextInt(value.length);
            if (value[randomIndex].cost < value[bestIndex].cost) {
                bestIndex = randomIndex;
            }
        }


        return bestIndex;
    }
}
