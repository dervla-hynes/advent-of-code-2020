package day_11;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DayElevenApplication {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String[]> seatMap = getSeatMap();
        ArrayList<String[]> seatMapCopy = getSeatMap();

        //always need to check the clean copy
        //always need to amend the actual one

        //first go
        fillSeats(seatMap, seatMapCopy);
        countFilledSeats(seatMap);

        //second go
        //reset to new array
        //need to implement # logic

        System.out.println("DONE ONE ITERATION");
        seatMapCopy = seatMap;
        fillSeats(seatMap, seatMapCopy);
        countFilledSeats(seatMap);

        System.out.println("DONE TWO ITERATIONS");
        seatMapCopy = seatMap;
        fillSeats(seatMap, seatMapCopy);
        countFilledSeats(seatMap);

        System.out.println("DONE THREE ITERATIONS");
        seatMapCopy = seatMap;
        fillSeats(seatMap, seatMapCopy);
        countFilledSeats(seatMap);
    }

    private static void countFilledSeats(ArrayList<String[]> seatMap) {
        AtomicInteger countOfFilledSeats = new AtomicInteger();
        for (String[] array : seatMap) {
            Arrays.stream(array)
                    .forEach(s -> {
                        if (s.equals("#")){
                            countOfFilledSeats.addAndGet(1);
                        }
                    });
        }
        System.out.println("filled seats: " + countOfFilledSeats.get());
    }

    private static void fillSeats(ArrayList<String[]> seatMap, ArrayList<String[]> seatMapCopy) {
        for (int i = 0; i < seatMap.size(); i++) {
            String[] row = seatMapCopy.get(i);
            String[] rowToChange = row.clone();
            String[] rowAbove = new String[0];
            String[] rowBelow = new String[0];

            if (i - 1 >= 0) {
                rowAbove = seatMapCopy.get(i - 1);
            }
            if (i + 1 < seatMapCopy.size()) {
                rowBelow = seatMapCopy.get(i + 1);
            }

            for (int j = 0; j < row.length; j++) {
                String seat = row[j];
                switch (seat) {
                    case "L":
                        if (i == 0) {
                            fillFirstRow(row, rowToChange, rowBelow, j);
                        } else if (i == seatMap.size() - 1) {
                            fillLastRow(row, rowToChange, rowAbove, j);
                        } else {
                            fillMiddleRow(row, rowToChange, rowAbove, rowBelow, j);
                        }
                        break;
                    case "#":
                        if (i == 0) {
                            emptyFirstRow(row, rowToChange, rowBelow, j);
                        } else if (i == seatMap.size() - 1) {
                            emptyLastRow(row, rowToChange, rowAbove, j);
                        } else {
                            emptyMiddleRow(row, rowToChange, rowAbove, rowBelow, j);
                        }
                        break;
                    case ".":
                        break;
                }
            }

            seatMap.set(i, rowToChange);
        }
    }

    private static void fillFirstRow(String[] row, String[] rowToChange, String[] rowBelow, int j) {
        if (j == 0) {
            if (!row[j + 1].equals("#") && !rowBelow[j].equals("#") && !rowBelow[j + 1].equals("#")) {
                rowToChange[j] = "#";
            }
        } else if (j == row.length - 1) {
            if (!row[j - 1].equals("#") && !rowBelow[j].equals("#") && !rowBelow[j - 1].equals("#")) {
                rowToChange[j] = "#";
            }
        } else {
            if (!row[j - 1].equals("#") && !row[j + 1].equals("#")
                    && !rowBelow[j - 1].equals("#") && !rowBelow[j].equals("#") && !rowBelow[j + 1].equals("#")) {
                rowToChange[j] = "#";
            }
        }
    }

    private static void emptyFirstRow(String[] row, String[] rowToChange, String[] rowBelow, int j) {
        int count = 0;
        if (j > 0 && j < row.length - 1) {
            if (row[j - 1].equals("#")) {
                count++;
            }
            if (row[j + 1].equals("#")) {
                count++;
            }
            if (rowBelow[j - 1].equals("#")) {
                count++;
            }
            if (rowBelow[j].equals("#") ) {
                count++;
            }
            if (rowBelow[j + 1].equals("#")) {
                count++;
            }
            if (count >= 4) {
                rowToChange[j] = "L";
            }
        }
    }

    private static void fillMiddleRow(String[] row, String[] rowToChange, String[] rowAbove, String[] rowBelow, int j) {
        if (j == 0) {
            if (!row[j + 1].equals("#")
                    && !rowAbove[j].equals("#") && !rowAbove[j + 1].equals("#")
                    && !rowBelow[j].equals("#") && !rowBelow[j + 1].equals("#")) {
                rowToChange[j] = "#";
            }
        } else if (j == row.length - 1) {
            if (!row[j - 1].equals("#")
                    && !rowAbove[j].equals("#") && !rowAbove[j - 1].equals("#")
                    && !rowBelow[j].equals("#") && !rowBelow[j - 1].equals("#")) {
                rowToChange[j] = "#";
            }
        } else {
            if (!row[j - 1].equals("#") && !row[j + 1].equals("#")
                    && !rowAbove[j - 1].equals("#") && !rowAbove[j].equals("#") && !rowAbove[j + 1].equals("#")
                    && !rowBelow[j - 1].equals("#") && !rowBelow[j].equals("#") && !rowBelow[j + 1].equals("#")) {
                rowToChange[j] = "#";
            }
        }
    }

    private static void emptyMiddleRow(String[] row, String[] rowToChange, String[] rowAbove, String[] rowBelow, int j) {
        int count = 0;
        if (j == 0) {
            if (row[j + 1].equals("#")) {
                count++;
            }
            if (rowAbove[j].equals("#")) {
                count++;
            }
            if (rowAbove[j + 1].equals("#")) {
                count++;
            }
            if (rowBelow[j].equals("#")) {
                count++;
            }
            if (rowBelow[j + 1].equals("#")) {
                count++;
            }
        } else if (j == row.length - 1) {
            if (row[j - 1].equals("#")) {
                count++;
            }
            if (rowAbove[j].equals("#")) {
                count++;
            }
            if (rowAbove[j - 1].equals("#")) {
                count++;
            }
            if (rowBelow[j].equals("#")) {
                count++;
            }
            if (rowBelow[j - 1].equals("#")) {
                count++;
            }
        } else {
            if (row[j - 1].equals("#")) {
                count++;
            }
            if (row[j + 1].equals("#")) {
                count++;
            }
            if (rowAbove[j - 1].equals("#")) {
                count++;
            }
            if (rowAbove[j].equals("#")) {
                count++;
            }
            if (rowAbove[j + 1].equals("#")) {
                count++;
            }
            if (rowBelow[j - 1].equals("#")) {
                count++;
            }
            if (rowBelow[j].equals("#")) {
                count++;
            }
            if (rowBelow[j + 1].equals("#")) {
                count++;
            }
        }
        if (count >= 4) {
            rowToChange[j] = "L";
        }
    }

    private static void fillLastRow(String[] row, String[] rowToChange, String[] rowAbove, int j) {
        if (j == 0) {
            if (!row[j + 1].equals("#") && !rowAbove[j].equals("#") && !rowAbove[j + 1].equals("#")) {
                rowToChange[j] = "#";
            }
        } else if (j == row.length - 1) {
            if (!row[j - 1].equals("#") && !rowAbove[j].equals("#") && !rowAbove[j - 1].equals("#")) {
                rowToChange[j] = "#";
            }
        } else {
            if (!row[j - 1].equals("#") && !row[j + 1].equals("#")
                    && !rowAbove[j - 1].equals("#") && !rowAbove[j].equals("#") && !rowAbove[j + 1].equals("#")) {
                rowToChange[j] = "#";
            }
        }
    }

    private static void emptyLastRow(String[] row, String[] rowToChange, String[] rowAbove, int j) {
        int count = 0;
        if (j > 0 && j < row.length - 1) {
            if (row[j - 1].equals("#")) {
                count++;
            }
            if (row[j + 1].equals("#")) {
                count++;
            }
            if (rowAbove[j - 1].equals("#")) {
                count++;
            }
            if (rowAbove[j].equals("#")) {
                count++;
            }
            if (rowAbove[j + 1].equals("#")) {
                count++;
            }
            if (count >= 4) {
                rowToChange[j] = "L";
            }
        }
    }

    private static ArrayList<String[]> getSeatMap() throws FileNotFoundException {
        File input = new File("src/main/resources/day_11/challengeInput");
        Scanner fileReader = new Scanner(input);

        ArrayList<String[]> seatMap = new ArrayList<>();

        while (fileReader.hasNext()) {
            String[] seatRow = fileReader.next().split("");
            seatMap.add(seatRow);
        }
        fileReader.close();
        return seatMap;
    }
}
