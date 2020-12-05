package day_1;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DayOneApplication {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(getTwoNumberAnswer());
        System.out.println(getThreeNumberAnswer());
    }

    public static Integer getTwoNumberAnswer() throws FileNotFoundException {
        ArrayList<Integer> numbers = generateNumberArray();
        int finalValue = 0;

        for (int i: numbers) {
            for (int j: numbers) {
                    if (i + j == 2020) {
                        finalValue = i * j;
                    }
            }
        }
        return finalValue;
    }

    public static Integer getThreeNumberAnswer() throws FileNotFoundException {
        ArrayList<Integer> numbers = generateNumberArray();
        int finalValue = 0;

        for (int i: numbers) {
            for (int j: numbers) {
                for (int k: numbers) {
                    if (i + j + k == 2020) {
                        finalValue = i * j * k;
                    }
                }
            }
        }
        return finalValue;
    }

    public static ArrayList<Integer> generateNumberArray() throws FileNotFoundException {
        IntegerFileReader integerFileReader = new IntegerFileReader();
        return integerFileReader.parseFile("src/main/resources/day_1/challengeInput");
    }
}
