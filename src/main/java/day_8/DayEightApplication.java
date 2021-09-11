package day_8;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DayEightApplication {

    public static ArrayList<Integer> indexes = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> instructions = getInstructions();
        System.out.println(instructions);

        int accumulator = 0;

        for (String instruction : instructions) {
            ArrayList<String> instructionsToModify = new ArrayList<>(instructions);
            int indexOfInstruction = instructions.indexOf(instruction);

            changeJumpAndNopValues(instructionsToModify, instruction, indexOfInstruction);

            for (int i = 0; i <= instructionsToModify.size() - 1;) {
                String action = instructionsToModify.get(i).substring(0, 3);
                String direction = instructionsToModify.get(i).substring(4, 5);
                int distance = Integer.parseInt(instructionsToModify.get(i).substring(5));

                indexes.add(i);

                switch (action) {
                    case "acc":
                        if (direction.equals("-")) {
                            accumulator = accumulator - distance;
                        } else if (direction.equals("+")) {
                            accumulator = accumulator + distance;
                        }
                        i++;
                        break;
                    case "jmp":
                        if (direction.equals("-")) {
                            i = i - distance;
                        } else if (direction.equals("+")) {
                            i = i + distance;
                        }
                        break;
                    case "nop":
                        i++;
                        break;
                }

                if (i == instructionsToModify.size() - 1) {
                    System.out.println("ABSOLUTE FINAL accumulator is: " + accumulator);
                }
                if (indexes.contains(i)) {
                    indexes.clear();
                    break;
                }
            }
            accumulator = 0;
        }
    }

    private static void changeJumpAndNopValues(ArrayList<String> temporaryInstructions, String instruction, int indexOfInstruction) {
        switch (instruction.substring(0, 3)) {
            case "acc":
                break;
            case "jmp":
                temporaryInstructions.set(indexOfInstruction, "nop " + instruction.substring(4, 5) + Integer.parseInt(instruction.substring(5)));
                break;
            case "nop":
                temporaryInstructions.set(indexOfInstruction, "jmp " + instruction.substring(4, 5) + Integer.parseInt(instruction.substring(5)));
                break;
        }
    }

    private static ArrayList<String> getInstructions() throws FileNotFoundException {
        File input = new File("src/main/resources/day_8/challengeInput");
        Scanner fileReader = new Scanner(input);

        ArrayList<String> instructions = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String nextLine = fileReader.nextLine();
            instructions.add(nextLine);
        }
        fileReader.close();
        return instructions;
    }
}
