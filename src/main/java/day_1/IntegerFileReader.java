package day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class IntegerFileReader {

    public ArrayList<Integer> parseFile(String pathname) throws FileNotFoundException {
        File input = new File(pathname);
        Scanner fileReader = new Scanner(input);

        ArrayList<Integer> integers = new ArrayList<Integer>();

        while (fileReader.hasNextInt()) {
            integers.add(fileReader.nextInt());
        }
        fileReader.close();
        return integers;
    }
}
