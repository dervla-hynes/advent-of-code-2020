package day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFourApplication {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<String>> passports = getAllPassports();

        System.out.println(passports);
        int validPassports = 0;

        for(ArrayList<String> passport : passports) {
            boolean validByr = false;
            boolean validIyr = false;
            boolean validEyr = false;
            boolean validHgt = false;
            boolean validHcl = false;
            boolean validEcl = false;
            boolean validPid = false;

            for (String entry : passport) {
                switch (entry.substring(0, 3)) {
                    case "byr":
                        validByr = validateByr(validByr, entry);
                        break;
                    case "iyr":
                        validIyr = validateIyr(validIyr, entry);
                        break;
                    case "eyr":
                        validEyr = validateEyr(validEyr, entry);
                        break;
                    case "hgt":
                        validHgt = validateHgt(validHgt, entry);
                        break;
                    case "hcl":
                        validHcl = validateHcl(validHcl, entry);
                        break;
                    case "ecl":
                        validEcl = validateEcl(validEcl, entry);
                        break;
                    case "pid":
                        validPid = validatePid(validPid, entry);
                        break;
                }
            }
            if (validByr && validIyr && validEyr && validHgt && validHcl && validEcl && validPid) {
                validPassports++;
            }
        }

        System.out.println(validPassports);
        //179

    }

    private static boolean validateHgt(boolean validHgt, String entry) {
        //a number followed by either cm or in:
        //If cm, the number must be at least 150 and at most 193.
        //If in, the number must be at least 59 and at most 76.
        String heightWithMetric = entry.substring(4);
        String metric = heightWithMetric.substring(heightWithMetric.length() - 2);
        String heightAsString = heightWithMetric.substring(0, heightWithMetric.length() - 2);

        int height;

        if (!heightAsString.isEmpty()) {
            height = Integer.parseInt(heightAsString);
        } else {
            height = 0;
        }

        if (metric.equals("in")) {
            if (height >= 59 && height <= 76) {
                validHgt = true;
            }
        } else if (metric.equals("cm")) {
            if (height >= 150 && height <= 193) {
                validHgt = true;
            }
        }
        return validHgt;
    }

    private static boolean validatePid(boolean validPid, String entry) {
        //a nine-digit number, including leading zeroes.
        if (entry.substring(4).matches("^[0-9]{9}$")) {
            validPid = true;
        }
        return validPid;
    }

    private static boolean validateEcl(boolean validEcl, String entry) {
        //exactly one of: amb blu brn gry grn hzl oth.
        if (entry.substring(4).equals("amb")
                || entry.substring(4).equals("blu")
                || entry.substring(4).equals("brn")
                || entry.substring(4).equals("gry")
                || entry.substring(4).equals("grn")
                || entry.substring(4).equals("hzl")
                || entry.substring(4).equals("oth")) {
            validEcl = true;
        }
        return validEcl;
    }

    private static boolean validateHcl(boolean validHcl, String entry) {
        //a # followed by exactly six characters 0-9 or a-f.
        if (entry.substring(4).matches("#[0-9a-f]{6}")) {
            validHcl = true;
        }
        return validHcl;
    }

    private static boolean validateEyr(boolean validEyr, String entry) {
        //four digits; at least 2020 and at most 2030.
        if (entry.substring(4).matches("^[0-9]{4}$")
                && Integer.parseInt(entry.substring(4)) >= 2020
                && Integer.parseInt(entry.substring(4)) <= 2030) {
            validEyr = true;
        }
        return validEyr;
    }

    private static boolean validateIyr(boolean validIyr, String entry) {
        //four digits; at least 2010 and at most 2020.
        if (entry.substring(4).matches("^[0-9]{4}$")
                && Integer.parseInt(entry.substring(4)) >= 2010
                && Integer.parseInt(entry.substring(4)) <= 2020) {
            validIyr = true;
        }
        return validIyr;
    }

    private static boolean validateByr(boolean validByr, String entry) {
        //four digits; at least 1920 and at most 2002.
        if (entry.substring(4).matches("^[0-9]{4}$")
                && Integer.parseInt(entry.substring(4)) >= 1920
                && Integer.parseInt(entry.substring(4)) <= 2002) {
            validByr = true;
        }
        return validByr;
    }

    private static ArrayList<ArrayList<String>> getAllPassports() throws FileNotFoundException {
        File input = new File("src/main/resources/day_4/challengeInput");
        Scanner fileReader = new Scanner(input);

        ArrayList<ArrayList<String>> passports = new ArrayList<>();
        ArrayList<String> passport = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String nextLine = fileReader.nextLine();

            if (nextLine.isEmpty()) {
                passports.add(passport);
                passport = new ArrayList<>();
            }

            String[] strings = nextLine.split(" ");
            for (String s : strings) {
                if (!s.isEmpty()) {
                    passport.add(s);
                }
            }
        }
        fileReader.close();
        return passports;
    }
}
