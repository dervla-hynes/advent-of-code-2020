package day_10;


import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public class DayTenApplication {

    private static ArrayList<Integer> checkedIntegers = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> allNumbers = getNumbers();
        Integer max = Collections.max(allNumbers);

        allNumbers.add(0);
        allNumbers.add(max + 3);

        Collections.sort(allNumbers);

        //part A
        System.out.println(generateJoltDifferences(allNumbers));

        //part B
//        List<Integer> tribonacciSequence = Arrays.asList(1, 1, 2, 4, 7, 13, 24);

        long runningTotal = 1L;
        ArrayList<ArrayList<Integer>> allArrays = generateArrayOfArrays(allNumbers);

        System.out.println(allArrays);

        for (ArrayList<Integer> array : allArrays) {
            int factor = 1;
            if (array.size() == 3) {
                factor = 2;
            } else if (array.size() == 4) {
                factor = 4;
            } else if (array.size() == 5) {
                factor = 7;
            } else if (array.size() == 6) {
                factor = 13;
            }
            runningTotal = runningTotal * factor;
        }
        System.out.println(runningTotal);
    }

    private static ArrayList<ArrayList<Integer>> generateArrayOfArrays(ArrayList<Integer> allNumbers) {
        ArrayList<ArrayList<Integer>> allArrays = new ArrayList<>();
        ArrayList<Integer> array = new ArrayList<>();

        for (int i = 0; i < allNumbers.size() - 1; i++) {

            Integer number = allNumbers.get(i);
            array.add(number);

            if (allNumbers.get(i + 1) == number + 3) {
                allArrays.add(array);
                array = new ArrayList<>();
            }
        }
        return allArrays;
    }

    private static int generateJoltDifferences(ArrayList<Integer> allNumbers) {
        int oneJoltDifferences = 0;
        int twoJoltDifferences = 0;
        int threeJoltDifferences = 0;

        for (int i = 0; i < allNumbers.size() - 1; i++) {
            int firstNumber = allNumbers.get(i);
            int secondNumber = allNumbers.get(i + 1);
            switch (secondNumber - firstNumber) {
                case 1:
                    oneJoltDifferences++;
                    break;
                case 2:
                    //none for my input data
                    twoJoltDifferences++;
                    break;
                case 3:
                    threeJoltDifferences++;
                    break;
            }
        }
        return oneJoltDifferences * threeJoltDifferences;
    }

    private static ArrayList<Integer> getNumbers() throws FileNotFoundException {
        File input = new File("src/main/resources/day_10/challengeInput");
        Scanner fileReader = new Scanner(input);

        ArrayList<Integer> numbers = new ArrayList<>();

        while (fileReader.hasNextInt()) {
            Integer nextLine = fileReader.nextInt();
            numbers.add(nextLine);
        }
        fileReader.close();
        return numbers;
    }
}
