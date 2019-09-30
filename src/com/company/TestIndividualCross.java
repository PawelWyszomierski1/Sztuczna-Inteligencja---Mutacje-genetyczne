package com.company;

import java.util.Random;
import java.util.Stack;

public class TestIndividualCross extends IndividualCross {


    @Override
    public Individual[] cross(Individual i1, Individual i2) {
        Random r = Main.raandomNumber;
        int length = i1.values.length;
        Individual[] returnValue = new Individual[2];
        returnValue[0] = new Individual();
        returnValue[1] = new Individual();
        returnValue[0].values = new int[length];
        returnValue[1].values = new int[length];
        int start = r.nextInt(i1.values.length - 2) + 1;
        int stop = r.nextInt(i1.values.length - start - 1) + start + 1;

        boolean completed = false;

        for (int i = start; i <= stop; i++) {
            returnValue[0].values[i] = i1.values[i];
            returnValue[1].values[i] = i2.values[i];
        }
        int i = stop + 1;
        int j = stop + 1;
        while (!completed) {
            if (i >= i1.values.length) i = 0;
            if (j >= i1.values.length) j = 0;
            while (returnValue[0].values[i] == 0) {
                boolean contains = false;
                for (int x = 0; x < returnValue[0].values.length; x++) {
                    if (returnValue[0].values[x] == i2.values[j]) contains = true;
                }
                if (contains) {
                    j++;
                } else {
                    returnValue[0].values[i] = i2.values[j];
                }
                if (j >= returnValue[0].values.length) j = 0;
            }
            i++;
            if (i == start) completed = true;

        }
        i = stop + 1;
        j = stop + 1;
        completed = false;
        while (!completed) {
            if (i >= i1.values.length) i = 0;
            if (j >= i1.values.length) j = 0;
            while (returnValue[1].values[i] == 0) {
                boolean contains = false;
                for (int x = 0; x < returnValue[1].values.length; x++) {
                    if (returnValue[1].values[x] == i1.values[j]) contains = true;
                }
                if (contains) {
                    j++;
                } else {
                    returnValue[1].values[i] = i1.values[j];
                }
                if (j >= returnValue[1].values.length) j = 0;
            }
            i++;
            if (i == start) completed = true;

        }


        return returnValue;


    }
}
