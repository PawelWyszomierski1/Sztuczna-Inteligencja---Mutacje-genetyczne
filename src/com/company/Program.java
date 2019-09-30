package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Program {

    int matrixSize;
    int[][] distanceMatrix;
    int[][] flowMatrix;
    int numberOfGenerations;
    int numberOfPopulation;
    ParentSelector parentSelector;
    IndividualCross individualCross;
    Individual[] population;
    Mutation mutation;
    int mutationChance;
    Individual bestSolution;
    int crossigChance;

    /**
     * Metoda wczytuje dane z pliku do programu.
     * @param path  Scieżka do pliku z danymi
     * @throws FileNotFoundException
     */
    public void readFile(String path) throws FileNotFoundException {
        Scanner input = new Scanner(new File(path));
        matrixSize = input.nextInt();
        distanceMatrix = new int[matrixSize][matrixSize];
        flowMatrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                distanceMatrix[i][j] = input.nextInt();
            }
        }
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                flowMatrix[i][j] = input.nextInt();
            }
        }
    }

    /**
     * Metoda tworzy generację zerową.
     */
    public void generatePopulation() {
        population = new Individual[numberOfPopulation];
        for (int i = 0; i < numberOfPopulation; i++) {
            population[i] = new Individual(matrixSize);
        }
        evaluateCosts();
        bestSolution = population[bestCost()];

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < population.length; i++) {

            sb.append(population[i].toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Metoda oblicza jakość danego genotypu..
     * @param value Genotyp
     * @return Jakość genotypu.
     */
    public int cost(int[] value) {
        int result = 0;
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value.length; j++) {
                result += (distanceMatrix[i][j] * flowMatrix[value[i] - 1][value[j] - 1]);
            }

        }
        return result;
    }

    /**
     * Metoda oblicza i przypisuje kazdemu osobnikowmi z populacji jego jakość.
     */
    public void evaluateCosts() {
        for (int i = 0; i < numberOfPopulation; i++) {
            population[i].cost = cost(population[i].values);
        }
    }

    /**
     * Metoda generuje nową populację.
     */
    public void makeNextGeneration() {
        Individual[] newPopulation = new Individual[numberOfPopulation];
        Random r = Main.raandomNumber;
        int i = 0;
        while (i < numberOfPopulation) {
            int chosenParent1 = parentSelector.select(population);
            int chosenParent2 = parentSelector.select(population);

            if (r.nextInt(100) < crossigChance) {
                Individual[] kids = individualCross.cross(population[chosenParent1], population[chosenParent2]);
                newPopulation[i] = kids[0];
                i++;
                if (i < numberOfPopulation) {
                    newPopulation[i] = kids[1];
                    i++;
                }
            } else {
                newPopulation[i] = population[chosenParent1];
                i++;
                if (i < numberOfPopulation) {
                    newPopulation[i] = population[chosenParent2];
                    i++;
                }
            }

        }


        population = newPopulation;
        mutate();
        evaluateCosts();
        getBestSolution();
    }

    /**
     * Metoda wyszukuje z populacji najlepszego osobnika pod wzgledem jakości.
     * @return Indeks najlepszego osobnika z populacji.
     */
    public int bestCost() {
        int best = 0;
        for (int i = 1; i < numberOfPopulation; i++) {
            if (population[best].cost > population[i].cost) best = i;
        }
        return best;
    }

    /**
     * Metoda przeprowadza mutację na całej populacji.
     */
    public void mutate() {
        Random r = Main.raandomNumber;
        for (int i = 0; i < numberOfPopulation; i++) {
            if (r.nextInt(100) < mutationChance)
                population[i] = mutation.mutate(population[i]);
        }
    }

    /**
     * Metoda sprawdza czy rozwiązanie aktualnej generacji jest lepsze od najlepszego znalezionego i zapisuje jego referencje.
     */
    public void getBestSolution() {
        if (bestSolution.cost > population[bestCost()].cost) {
            bestSolution = new Individual((population[bestCost()]));
        }

    }
}
