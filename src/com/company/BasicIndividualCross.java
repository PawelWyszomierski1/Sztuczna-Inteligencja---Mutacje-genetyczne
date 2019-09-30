package com.company;

import java.util.Random;
import java.util.Stack;

public class BasicIndividualCross extends IndividualCross {

    int value;

    public BasicIndividualCross() {
    }

    @Override
    public Individual[] cross(Individual i1, Individual i2) {
        Random rr = Main.raandomNumber;

        value = rr.nextInt(i1.values.length);
        int length = i1.values.length;
        Individual[] returnValue = new Individual[2];
        returnValue[0] = new Individual();
        returnValue[1] = new Individual();
        returnValue[0].values = new int[length];
        returnValue[1].values = new int[length];
        for (int i = 0; i < value; i++) {
            returnValue[0].values[length - value + i] = i2.values[i];
            returnValue[1].values[length - value + i] = i1.values[i];
        }
        for (int i = 0; i < length - value; i++) {
            returnValue[0].values[i] = i1.values[i];
            returnValue[1].values[i] = i2.values[i];

        }
        int[] numberOfAppearances = new int[length];

        for (int j = 0; j < length; j++) {
            numberOfAppearances[returnValue[0].values[j] - 1]++;
        }
        for (int j = 0; j < length; j++) {
            if (numberOfAppearances[j] > 1) {
                int x = -1;
                for (int z = 0; z < length; z++) {
                    if (numberOfAppearances[z] == 0) x = z;
                }
                int a = -1;
                int b = -1;
                boolean flag = false;
                for (int g = 0; g < length; g++) {
                    if (returnValue[0].values[g] == j + 1) {
                        if (!flag) {
                            a = g;
                            flag = true;
                        } else {
                            b = g;
                        }
                    }
                }
                Random r = Main.raandomNumber;
                if (r.nextInt(2) == 0) {
                    returnValue[0].values[a] = x + 1;
                } else {
                    returnValue[0].values[b] = x + 1;
                }
                numberOfAppearances[j] -= 1;
                numberOfAppearances[x] += 1;
            }
        }


        numberOfAppearances = new int[length];

        for (int j = 0; j < length; j++) {
            numberOfAppearances[returnValue[1].values[j] - 1]++;
        }
        for (int j = 0; j < length; j++) {
            while (numberOfAppearances[j] > 1) {
                int x = -1;
                for (int z = 0; z < length; z++) {
                    if (numberOfAppearances[z] == 0) x = z;
                }
                int a = -1;
                int b = -1;
                boolean flag = false;
                for (int g = 0; g < length; g++) {
                    if (returnValue[1].values[g] == j + 1) {
                        if (!flag) {
                            a = g;
                            flag = true;
                        } else {
                            b = g;
                        }
                    }
                }
                Random r = Main.raandomNumber;
                if (r.nextInt(2) == 0) {
                    returnValue[1].values[a] = x + 1;
                } else {
                    returnValue[1].values[b] = x + 1;
                }
                numberOfAppearances[j] -= 1;
                numberOfAppearances[x] += 1;
            }
        }
        return returnValue;


    }
}
