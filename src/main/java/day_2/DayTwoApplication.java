package day_2;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class DayTwoApplication {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> passwordRules = getPasswordRules();

        AtomicInteger validPasswords = getValidPasswordsOtherMethod(passwordRules);

        System.out.println(validPasswords.get());
    }

    private static ArrayList<String> getPasswordRules() throws FileNotFoundException {
        File input = new File("src/main/resources/day_2/challengeInput");
        Scanner fileReader = new Scanner(input);

        ArrayList<String> passwordRules = new ArrayList<String>();

        while (fileReader.hasNextLine()) {
            passwordRules.add(fileReader.nextLine());
        }
        fileReader.close();
        return passwordRules;
    }

    public static AtomicInteger getValidPasswords(ArrayList<String> passwordRules) {
        AtomicInteger validPasswords = new AtomicInteger();

        passwordRules.forEach(s -> {
            String[] strings = s.split(":", 2);

            String rule = strings[0];
            String abc = rule.split("-", 2)[1];
            String[] xyz = abc.split(" ");

            int min = Integer.parseInt(rule.split("-", 2)[0]);
            int max = Integer.parseInt(xyz[0]);
            String letter = rule.substring(rule.length() - 1);
            String password = strings[1];

            int letterCount = StringUtils.countMatches(password, letter);

            if (min <= letterCount && letterCount <= max) {
                validPasswords.getAndIncrement();
            }
        });
        return validPasswords;
    }

    public static AtomicInteger getValidPasswordsOtherMethod(ArrayList<String> passwordRules) {
        AtomicInteger validPasswords = new AtomicInteger();

        passwordRules.forEach(s -> {
            String[] strings = s.split(":", 2);

            String rule = strings[0];
            String abc = rule.split("-", 2)[1];
            String[] xyz = abc.split(" ");

            int firstLocation = Integer.parseInt(rule.split("-", 2)[0]);
            int secondLocation = Integer.parseInt(xyz[0]);
            String letter = rule.substring(rule.length() - 1);
            String password = strings[1];

            String[] passwordArray = password.split("");

            if (passwordArray[firstLocation].equals(letter) && !passwordArray[secondLocation].equals(letter)
            || !passwordArray[firstLocation].equals(letter) && passwordArray[secondLocation].equals(letter)) {
                validPasswords.getAndIncrement();
            }

        });
        return validPasswords;
    }
}
