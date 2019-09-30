package com.company;


import java.io.FileNotFoundException;
import java.util.Random;

public class Main {
    static Random raandomNumber = new Random(5);

    public static void main(String[] args) throws FileNotFoundException {

        /**
         * Ustawienie parametrów
         */
        String path = "had20.txt";
        int numberOfGenerations = 100;
        int numberOfPopulation = 500;
        int mutationChance = 2;
        int crossingChance = 70;
        int tournamentAmount = 5;

        ParentSelector parentSelector = new TournamentParentSelector(tournamentAmount);
        IndividualCross individualCross = new TestIndividualCross();
        Mutation mutation = new BasicMutation();

        /**
         * Przypisanie parametrów do programu
         */
        Program program = new Program();
        program.numberOfGenerations = numberOfGenerations;
        program.numberOfPopulation = numberOfPopulation;
        program.crossigChance = crossingChance;
        program.parentSelector = parentSelector;
        program.individualCross = individualCross;
        program.mutation = mutation;
        program.mutationChance = mutationChance;


        program.readFile(path);
        program.generatePopulation();
        System.out.println("BEST COST FOR THIS POPULATION: " + program.population[program.bestCost()]);

        for (int i = 0; i < numberOfGenerations; i++) {
            program.makeNextGeneration();
            System.out.println("BEST COST FOR THIS POPULATION: " + program.population[program.bestCost()]);
        }

        System.out.println("BEST SOLUTION EVER "+ program.bestSolution.toString());

    }

}