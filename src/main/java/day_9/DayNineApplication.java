package day_9;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class DayNineApplication {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Long> allNumbers = getNumbers();
        System.out.println(allNumbers);

        ArrayList<Long> arrayToCheck = new ArrayList<>(allNumbers.subList(0, 25));
        ArrayList<Long> remainingArray = new ArrayList<>(allNumbers.subList(25, 1000));

        System.out.println(arrayToCheck);
        System.out.println(remainingArray);

        //part A
        Long invalidNumber = findInvalidNumber(arrayToCheck, remainingArray);
        System.out.println(invalidNumber);

        //part B
        Long runningTotal = 0L;
        ArrayList<Long> listOfNumbers = new ArrayList<>();
        Long max = 0L;
        Long min = 0L;

        for (int i = 0; i < allNumbers.size(); i++) {
            Long startingNumber = allNumbers.get(i);

            for (int j = i; j < allNumbers.size(); j++) {
                Long value = allNumbers.get(j);
                runningTotal = Long.sum(runningTotal, value);
                listOfNumbers.add(value);

                if (runningTotal.equals(invalidNumber) && listOfNumbers.size() != 1) {
                    System.out.println("found it!");
                    System.out.println(listOfNumbers);
                    min = Collections.min(listOfNumbers);
                    max = Collections.max(listOfNumbers);
                    System.out.println(max + min);
                    break;
                }
                if (runningTotal.compareTo(invalidNumber) > 0) {
                    runningTotal = 0L;
                    listOfNumbers.clear();
                }
            }
        }
    }

    private static Long findInvalidNumber(ArrayList<Long> arrayToCheck, ArrayList<Long> remainingArray) {
        Long numberToReturn = 0L;
        for (Long number : remainingArray) {

            boolean matchFound = false;

            for (var i = 0; i < arrayToCheck.size(); i++) {
                for (var j = i + 1; j < arrayToCheck.size(); j++) {
                    Long iValue = arrayToCheck.get(i);
                    Long jValue = arrayToCheck.get(j);
                    Long value = Long.sum(iValue, jValue);
                    if (value.equals(number) && !iValue.equals(jValue)) {
                        matchFound = true;
                        break;
                    }
                }
            }
            if (!matchFound) {
                numberToReturn = number;
            }
            arrayToCheck.remove(0);
            arrayToCheck.add(number);
        }
        return numberToReturn;
    }

    private static ArrayList<Long> getNumbers() throws FileNotFoundException {
        File input = new File("src/main/resources/day_9/challengeInput");
        Scanner fileReader = new Scanner(input);

        ArrayList<Long> numbers = new ArrayList<>();

        while (fileReader.hasNextLong()) {
            Long nextLine = fileReader.nextLong();
            numbers.add(nextLine);
        }
        fileReader.close();
        return numbers;
    }
}
