package day_3;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThreeApplication {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String[]> mapOfTrees = getMapOfTrees();

        int trees = 0;
        int counter = 0;

        trees = getTreesFor1DownAndAcrossValue(1, mapOfTrees, trees, counter) *
                getTreesFor1DownAndAcrossValue(3, mapOfTrees, trees, counter) *
                getTreesFor1DownAndAcrossValue(5, mapOfTrees, trees, counter) *
                getTreesFor1DownAndAcrossValue(7, mapOfTrees, trees, counter) *
                getTreesFor2DownAndAcrossValue(1, mapOfTrees, trees, counter);

        System.out.println(trees);
    }

    private static ArrayList<String[]> getMapOfTrees() throws FileNotFoundException {
        File input = new File("src/main/resources/day_3/challengeInput");
        Scanner fileReader = new Scanner(input);

        ArrayList<String[]> mapOfTrees = new ArrayList<String[]>();

        while (fileReader.hasNextLine()) {
            String[] line = fileReader.nextLine().split("");
            mapOfTrees.add(line);
        }
        fileReader.close();
        return mapOfTrees;
    }

    private static int getTreesFor1DownAndAcrossValue(int acrossValue, ArrayList<String[]> mapOfTrees, int trees, int counter) {
        for (String[] line : mapOfTrees) {
            if (line[counter].equals("#")) {
                trees++;
            }

            counter = counter + acrossValue;

            if (counter >= 31) {
                counter = counter - 31;
            }
        }
        return trees;
    }

    private static int getTreesFor2DownAndAcrossValue(int acrossValue, ArrayList<String[]> mapOfTrees, int trees, int counter) {
        for (String[] line : mapOfTrees) {
            if (mapOfTrees.indexOf(line) % 2 == 0) {
                if (line[counter].equals("#")) {
                    trees++;
                }

                counter = counter + acrossValue;

                if (counter == 31) {
                    counter = 0;
                }
            }
        }
        return trees;
    }
}
