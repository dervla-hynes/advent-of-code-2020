package day_5;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayFiveApplication {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> seatCodes = getSeatCodes();

        int maxSeatId = 0;
        int minSeatId = 999;
        int mySeatId = 0;

        ArrayList<Integer> allSeatIds = new ArrayList<>();

        for (String code : seatCodes) {
            int row = 0;
            int column = 0;
            int seatId;

            String[] rowCode = code.substring(0, 7).split("");
            String[] columnCode = code.substring(7, 10).split("");

            List<Integer> sequenceColumn = generateSequence(7);
            List<Integer> sequenceRow = generateSequence(127);

            for (String letter : rowCode) {
                if (letter.equals("B")) {
                     sequenceRow = sequenceRow.subList(sequenceRow.size() / 2, sequenceRow.size());
                } else if (letter.equals("F")) {
                    sequenceRow = sequenceRow.subList(0, (sequenceRow.size()) / 2);
                }
                if (sequenceRow.size() == 1) {
                    row = sequenceRow.get(0);
                    System.out.println("row is: " + row);
                }
            }

            for (String letter : columnCode) {
                if (letter.equals("R")) {
                    sequenceColumn = sequenceColumn.subList(sequenceColumn.size() / 2, sequenceColumn.size());
                } else if (letter.equals("L")) {
                    sequenceColumn = sequenceColumn.subList(0, (sequenceColumn.size()) / 2);
                }
                if (sequenceColumn.size() == 1) {
                    column = sequenceColumn.get(0);
                    System.out.println("column is: " + column);
                }
            }

            seatId =  (row * 8) + column;
            allSeatIds.add(seatId);

            if (seatId > maxSeatId) {
                maxSeatId = seatId;
            }
            if (seatId < minSeatId) {
                minSeatId = seatId;
            }

        }

        for (int i = minSeatId; i <= maxSeatId; i++) {
            if (!allSeatIds.contains(i)) {
                mySeatId = i;
            }
        }

        System.out.println("my seat id is: " + mySeatId);
    }

    private static ArrayList<String> getSeatCodes() throws FileNotFoundException {
        File input = new File("src/main/resources/day_5/challengeInput");
        Scanner fileReader = new Scanner(input);

        ArrayList<String> seatCodes = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String nextLine = fileReader.nextLine();
            seatCodes.add(nextLine);
        }
        fileReader.close();
        return seatCodes;
    }

    private static List<Integer> generateSequence(int end) {
        List<Integer> sequenceColumn = new ArrayList<>();
        for (int i = 0; i <= end; i++) {
            sequenceColumn.add(i);
        }
        return sequenceColumn;
    }
}
