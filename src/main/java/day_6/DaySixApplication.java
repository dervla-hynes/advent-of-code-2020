package day_6;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DaySixApplication {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<ArrayList<String>> customForms = getCustomForms();
        int count = 0;

        for (ArrayList<String> formGroup : customForms) {
            int peopleInGroup = formGroup.size();
            System.out.println(peopleInGroup + " people in group, so needs to match this many times");
            ArrayList<String> allChars = new ArrayList<>();

            for (String form : formGroup) {
                Collections.addAll(allChars, form.split(""));
            }
            System.out.println(allChars);

            List<String> allAgreedChars = new ArrayList<>();

            for (String character : allChars) {
                if (Collections.frequency(allChars, character) == peopleInGroup) {
                    allAgreedChars.add(character);
                }
            }

            List<String> distinctChars = allAgreedChars.stream()
                    .distinct()
                    .collect(Collectors.toList());

            int individualCount = distinctChars.size();
            System.out.println("This one has " + individualCount);
            count = count + individualCount;
        }

        System.out.println(count);
    }

    private static ArrayList<ArrayList<String>> getCustomForms() throws FileNotFoundException {
        File input = new File("src/main/resources/day_6/challengeInput");
        Scanner fileReader = new Scanner(input);

        ArrayList<ArrayList<String>> customForms = new ArrayList<>();
        ArrayList<String> customForm = new ArrayList<>();

        while (fileReader.hasNextLine()) {
            String nextLine = fileReader.nextLine();

            if (nextLine.isEmpty()) {
                customForms.add(customForm);
                customForm = new ArrayList<>();
            } else {
                customForm.add(nextLine);
            }
        }
        fileReader.close();
        return customForms;
    }
}
