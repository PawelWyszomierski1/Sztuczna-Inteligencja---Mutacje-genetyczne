package com.company;

import java.util.Random;

public class BasicMutation extends Mutation {
    @Override
    public Individual mutate(Individual value) {
        Individual returnValue = new Individual(value);
        Random r = Main.raandomNumber;
        int randomNumber1 = r.nextInt(value.values.length);
        int randomNumber2 = r.nextInt(value.values.length);

        while (randomNumber2 == randomNumber1) {
            randomNumber2 = r.nextInt(value.values.length);
        }

        int temp = returnValue.values[randomNumber1];
        returnValue.values[randomNumber1] = returnValue.values[randomNumber2];
        returnValue.values[randomNumber2] = temp;
        return returnValue;
    }
}
