package com.company;

import java.util.Random;

public class Individual {

    int[] values;
    int cost = -1;

    public Individual() {
    }

    public Individual(int[] values) {
        this.values = values;
    }

    /**
     * Konstruktor kopiujący osobnika
     * @param individual Osobnik do skopiowania
     */
    public Individual(Individual individual) {
        int[] src = individual.values;
        values = new int[src.length];
        for (int i = 0; i < src.length; i++) {
            this.values[i] = individual.values[i];
        }
        cost = individual.cost;
    }

    /**
     * Konstruktor nowego osobnika który odrazu nadaje mu losowy gentoyp.
     * @param value Ilość genów nowego osobnika.
     */
    public Individual(int value) {
        values = new int[value];
        for (int i = 0; i < value; i++) {
            values[i] = i + 1;
        }

        int index, temp;
        Random random = Main.raandomNumber;
        for (int i = values.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = values[index];
            values[index] = values[i];
            values[i] = temp;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i] + " ");
        }
        sb.append(cost);
        return sb.toString();
    }
}
