package day_7;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DaySevenApplication {

    private static ArrayList<String> bagsThatContainGoldFirst = new ArrayList<>();
    private static ArrayList<String> bagsThatContainGoldSecond = new ArrayList<>();
    private static ArrayList<String> bagsThatContainGoldThird = new ArrayList<>();
    private static ArrayList<String> bagsThatContainGoldFourth = new ArrayList<>();
    private static ArrayList<String> bagsThatContainGoldFifth = new ArrayList<>();
    private static ArrayList<String> bagsThatContainGoldSixth = new ArrayList<>();
    private static ArrayList<String> bagsThatContainGoldSeventh = new ArrayList<>();
    private static ArrayList<String> bagsThatContainGoldEighth = new ArrayList<>();
    private static ArrayList<String> bagsThatContainGoldNinth = new ArrayList<>();
    private static ArrayList<String> bagsThatContainGoldTenth = new ArrayList<>();

    private static ArrayList<String> allBags = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> baggageRules = getBaggageRules();
        //max length is 20 which contains max 5 colours

        //part A
        getAllShinyGoldHolders(baggageRules);

        //part B
        getAllBagsWithinShinyGold(baggageRules);

    }

    private static void getAllBagsWithinShinyGold(ArrayList<String> baggageRules) {
        int bagCount = 0;

        ArrayList<String> bagsListOne = new ArrayList<>();
        ArrayList<String> bagsListTwo = new ArrayList<>();
        ArrayList<String> bagsListThree = new ArrayList<>();
        ArrayList<String> bagsListFour = new ArrayList<>();
        ArrayList<String> bagsListFive = new ArrayList<>();
        ArrayList<String> bagsListSix = new ArrayList<>();
        ArrayList<String> bagsListSeven = new ArrayList<>();
        ArrayList<String> bagsListEight = new ArrayList<>();
        ArrayList<String> bagsListNine = new ArrayList<>();

        bagCount = getFirstBagCount(baggageRules, bagCount, bagsListOne);

        bagCount = getBagCount(baggageRules, bagCount, bagsListOne, bagsListTwo);
        bagCount = getBagCount(baggageRules, bagCount, bagsListTwo, bagsListThree);
        bagCount = getBagCount(baggageRules, bagCount, bagsListThree, bagsListFour);
        bagCount = getBagCount(baggageRules, bagCount, bagsListFour, bagsListFive);
        bagCount = getBagCount(baggageRules, bagCount, bagsListFive, bagsListSix);
        bagCount = getBagCount(baggageRules, bagCount, bagsListSix, bagsListSeven);
        bagCount = getBagCount(baggageRules, bagCount, bagsListSeven, bagsListEight);
        bagCount = getBagCount(baggageRules, bagCount, bagsListEight, bagsListNine);

        System.out.println(bagsListOne);
        System.out.println(bagsListTwo);
        System.out.println(bagsListThree);
        System.out.println(bagsListFour);
        System.out.println(bagsListFive);
        System.out.println(bagsListSix);
        System.out.println(bagsListSeven);
        System.out.println(bagsListEight);
        System.out.println(bagsListNine);

        System.out.println(bagCount);
    }

    private static int getFirstBagCount(ArrayList<String> baggageRules, int bagCount, ArrayList<String> bagsListOne) {
        for (String rule : baggageRules) {
            String[] ruleArray = rule.split(" ");

            if (ruleArray[0].equals("shiny") && ruleArray[1].equals("gold")) {
                if (ruleArray.length >= 8) {
                    bagsListOne.add(ruleArray[4] + " " + ruleArray[5] + " " + ruleArray[6]);
                    int count = Integer.parseInt(ruleArray[4]);
                    bagCount = bagCount + count;
                }
                if (ruleArray.length >= 12) {
                    bagsListOne.add(ruleArray[8] + " " + ruleArray[9] + " " + ruleArray[10]);
                    int count = Integer.parseInt(ruleArray[8]);
                    bagCount = bagCount + count;
                }
                if (ruleArray.length >= 16) {
                    bagsListOne.add(ruleArray[12] + " " + ruleArray[13] + " " + ruleArray[14]);
                    int count = Integer.parseInt(ruleArray[12]);
                    bagCount = bagCount + count;
                }
                if (ruleArray.length >= 20) {
                    bagsListOne.add(ruleArray[16] + " " + ruleArray[17] + " " + ruleArray[18]);
                    int count = Integer.parseInt(ruleArray[16]);
                    bagCount = bagCount + count;
                }
            }
        }
        System.out.println(bagCount);
        return bagCount;
    }

    private static int getBagCount(ArrayList<String> baggageRules, int bagCount, ArrayList<String> bagsListTwo, ArrayList<String> bagsListThree) {
        for (String rule : baggageRules) {
            String[] ruleArray = rule.split(" ");

            for (String bag : bagsListTwo) {
                String[] bagArray = bag.split(" ");
                int factor = Integer.parseInt(bagArray[0]);

                if (ruleArray[0].equals(bagArray[1]) && ruleArray[1].equals(bagArray[2])) {
                    if (ruleArray.length >= 8) {
                        int count = Integer.parseInt(ruleArray[4]);
                        int newFactor = count * factor;
                        bagsListThree.add(newFactor + " " + ruleArray[5] + " " + ruleArray[6]);
                        bagCount = bagCount + factor * count;
                    }
                    if (ruleArray.length >= 12) {
                        int count = Integer.parseInt(ruleArray[8]);
                        int newFactor = count * factor;
                        bagsListThree.add(newFactor + " " + ruleArray[9] + " " + ruleArray[10]);
                        bagCount = bagCount + factor * count;
                    }
                    if (ruleArray.length >= 16) {
                        int count = Integer.parseInt(ruleArray[12]);
                        int newFactor = count * factor;
                        bagsListThree.add(newFactor + " " + ruleArray[13] + " " + ruleArray[14]);
                        bagCount = bagCount + factor * count;
                    }
                    if (ruleArray.length >= 20) {
                        int count = Integer.parseInt(ruleArray[16]);
                        int newFactor = count * factor;
                        bagsListThree.add(newFactor + " " + ruleArray[17] + " " + ruleArray[18]);
                        bagCount = bagCount + factor * count;
                    }
                }
            }
        }
        System.out.println(bagCount);
        return bagCount;
    }

    private static void getAllShinyGoldHolders(ArrayList<String> baggageRules) {
        for (String rule : baggageRules) {
            String[] ruleArray = rule.split(" ");

            if (ruleArray[5].equals("shiny") && ruleArray[6].equals("gold")) {
                bagsThatContainGoldFirst.add(ruleArray[0] + " " + ruleArray[1]);
                allBags.add(ruleArray[0] + " " + ruleArray[1]);
            }
            if (ruleArray.length >= 12 && ruleArray[9].equals("shiny") && ruleArray[10].equals("gold")) {
                bagsThatContainGoldFirst.add(ruleArray[0] + " " + ruleArray[1]);
                allBags.add(ruleArray[0] + " " + ruleArray[1]);
            }
            if (ruleArray.length >= 16 && ruleArray[13].equals("shiny") && ruleArray[14].equals("gold")) {
                bagsThatContainGoldFirst.add(ruleArray[0] + " " + ruleArray[1]);
                allBags.add(ruleArray[0] + " " + ruleArray[1]);
            }
            if (ruleArray.length >= 20 && ruleArray[17].equals("shiny") && ruleArray[18].equals("gold")) {
                bagsThatContainGoldFirst.add(ruleArray[0] + " " + ruleArray[1]);
                allBags.add(ruleArray[0] + " " + ruleArray[1]);
            }
        }

        for (String rule : baggageRules) {
            getListOfBags(bagsThatContainGoldFirst, bagsThatContainGoldSecond, rule);
            bagsThatContainGoldSecond.removeIf(bag -> bagsThatContainGoldFirst.contains(bag));
            bagsThatContainGoldSecond = (ArrayList<String>) bagsThatContainGoldSecond.stream().distinct().collect(Collectors.toList());
        }

        for (String rule : baggageRules) {
            getListOfBags(bagsThatContainGoldSecond, bagsThatContainGoldThird, rule);
            bagsThatContainGoldThird.removeIf(bag -> bagsThatContainGoldFirst.contains(bag) ||
                    bagsThatContainGoldSecond.contains(bag));
            bagsThatContainGoldThird = (ArrayList<String>) bagsThatContainGoldThird.stream().distinct().collect(Collectors.toList());
        }

        for (String rule : baggageRules) {
            getListOfBags(bagsThatContainGoldThird, bagsThatContainGoldFourth, rule);
            bagsThatContainGoldFourth.removeIf(bag -> bagsThatContainGoldFirst.contains(bag) ||
                    bagsThatContainGoldSecond.contains(bag) ||
                    bagsThatContainGoldThird.contains(bag));
            bagsThatContainGoldFourth = (ArrayList<String>) bagsThatContainGoldFourth.stream().distinct().collect(Collectors.toList());

        }

        for (String rule : baggageRules) {
            getListOfBags(bagsThatContainGoldFourth, bagsThatContainGoldFifth, rule);
            bagsThatContainGoldFifth.removeIf(bag -> bagsThatContainGoldFirst.contains(bag) ||
                    bagsThatContainGoldSecond.contains(bag) ||
                    bagsThatContainGoldThird.contains(bag) ||
                    bagsThatContainGoldFourth.contains(bag));
            bagsThatContainGoldFifth = (ArrayList<String>) bagsThatContainGoldFifth.stream().distinct().collect(Collectors.toList());
        }

        for (String rule : baggageRules) {
            getListOfBags(bagsThatContainGoldFifth, bagsThatContainGoldSixth, rule);
            bagsThatContainGoldSixth.removeIf(bag -> bagsThatContainGoldFirst.contains(bag) ||
                    bagsThatContainGoldSecond.contains(bag) ||
                    bagsThatContainGoldThird.contains(bag) ||
                    bagsThatContainGoldFourth.contains(bag) ||
                    bagsThatContainGoldFifth.contains(bag));
            bagsThatContainGoldSixth = (ArrayList<String>) bagsThatContainGoldSixth.stream().distinct().collect(Collectors.toList());

        }

        for (String rule : baggageRules) {
            getListOfBags(bagsThatContainGoldSixth, bagsThatContainGoldSeventh, rule);
            bagsThatContainGoldSeventh.removeIf(bag -> bagsThatContainGoldFirst.contains(bag) ||
                    bagsThatContainGoldSecond.contains(bag) ||
                    bagsThatContainGoldThird.contains(bag) ||
                    bagsThatContainGoldFourth.contains(bag) ||
                    bagsThatContainGoldFifth.contains(bag) ||
                    bagsThatContainGoldSixth.contains(bag));
            bagsThatContainGoldSeventh = (ArrayList<String>) bagsThatContainGoldSeventh.stream().distinct().collect(Collectors.toList());
        }

        for (String rule : baggageRules) {
            getListOfBags(bagsThatContainGoldSeventh, bagsThatContainGoldEighth, rule);
            bagsThatContainGoldEighth.removeIf(bag -> bagsThatContainGoldFirst.contains(bag) ||
                    bagsThatContainGoldSecond.contains(bag) ||
                    bagsThatContainGoldThird.contains(bag) ||
                    bagsThatContainGoldFourth.contains(bag) ||
                    bagsThatContainGoldFifth.contains(bag) ||
                    bagsThatContainGoldSixth.contains(bag) ||
                    bagsThatContainGoldSeventh.contains(bag));
            bagsThatContainGoldEighth = (ArrayList<String>) bagsThatContainGoldEighth.stream().distinct().collect(Collectors.toList());
        }

        for (String rule : baggageRules) {
            getListOfBags(bagsThatContainGoldEighth, bagsThatContainGoldNinth, rule);
            bagsThatContainGoldNinth.removeIf(bag -> bagsThatContainGoldFirst.contains(bag) ||
                    bagsThatContainGoldSecond.contains(bag) ||
                    bagsThatContainGoldThird.contains(bag) ||
                    bagsThatContainGoldFourth.contains(bag) ||
                    bagsThatContainGoldFifth.contains(bag) ||
                    bagsThatContainGoldSixth.contains(bag) ||
                    bagsThatContainGoldSeventh.contains(bag) ||
                    bagsThatContainGoldEighth.contains(bag));
            bagsThatContainGoldNinth = (ArrayList<String>) bagsThatContainGoldNinth.stream().distinct().collect(Collectors.toList());
        }

        for (String rule : baggageRules) {
            getListOfBags(bagsThatContainGoldNinth, bagsThatContainGoldTenth, rule);
            bagsThatContainGoldTenth.removeIf(bag -> bagsThatContainGoldFirst.contains(bag) ||
                    bagsThatContainGoldSecond.contains(bag) ||
                    bagsThatContainGoldThird.contains(bag) ||
                    bagsThatContainGoldFourth.contains(bag) ||
                    bagsThatContainGoldFifth.contains(bag) ||
                    bagsThatContainGoldSixth.contains(bag) ||
                    bagsThatContainGoldSeventh.contains(bag) ||
                    bagsThatContainGoldEighth.contains(bag) ||
                    bagsThatContainGoldNinth.contains(bag));
            bagsThatContainGoldTenth = (ArrayList<String>) bagsThatContainGoldTenth.stream().distinct().collect(Collectors.toList());
        }

        System.out.println(bagsThatContainGoldFirst);
        System.out.println(bagsThatContainGoldSecond);
        System.out.println(bagsThatContainGoldThird);
        System.out.println(bagsThatContainGoldFourth);
        System.out.println(bagsThatContainGoldFifth);
        System.out.println(bagsThatContainGoldSixth);
        System.out.println(bagsThatContainGoldSeventh);
        System.out.println(bagsThatContainGoldEighth);
        System.out.println(bagsThatContainGoldNinth);
        System.out.println(bagsThatContainGoldTenth);

        System.out.println((int) allBags.stream().distinct().count());
    }

    private static void getListOfBags(ArrayList<String> bagListOne, ArrayList<String> bagListTwo, String rule) {
        String[] ruleArray = rule.split(" ");

        for (String bag : bagListOne) {
            String[] bagSplit = bag.split(" ");

            if (ruleArray[5].equals(bagSplit[0]) && ruleArray[6].equals(bagSplit[1])) {
                bagListTwo.add(ruleArray[0] + " " + ruleArray[1]);
                allBags.add(ruleArray[0] + " " + ruleArray[1]);
            }
            if (ruleArray.length >= 12 && ruleArray[9].equals(bagSplit[0]) && ruleArray[10].equals(bagSplit[1])) {
                bagListTwo.add(ruleArray[0] + " " + ruleArray[1]);
                allBags.add(ruleArray[0] + " " + ruleArray[1]);
            }
            if (ruleArray.length >= 16 && ruleArray[13].equals(bagSplit[0]) && ruleArray[14].equals(bagSplit[1])) {
                bagListTwo.add(ruleArray[0] + " " + ruleArray[1]);
                allBags.add(ruleArray[0] + " " + ruleArray[1]);
            }
            if (ruleArray.length >= 20 && ruleArray[17].equals(bagSplit[0]) && ruleArray[18].equals(bagSplit[1])) {
                bagListTwo.add(ruleArray[0] + " " + ruleArray[1]);
                allBags.add(ruleArray[0] + " " + ruleArray[1]);
            }
        }
    }

    private static ArrayList<String> getBaggageRules() throws FileNotFoundException {
        File input = new File("src/main/resources/day_7/challengeInput");
        Scanner fileReader = new Scanner(input);

        ArrayList<String> baggageRules = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String nextLine = fileReader.nextLine();
            baggageRules.add(nextLine);
        }
        fileReader.close();
        return baggageRules;
    }
}
