package Day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scratchcards {

    public static void printArray(String[] arr) {
        for(String a: arr) {
            System.out.println(a);
        }
    }

    public static int countOfCommonValues(String[] array1, String[] array2) {
        int count = 0;
        for (String value1 : array1) {
            for (String value2 : array2) {
                if (value1.length() != 0 &&value1.equals(value2)) {
                    System.out.println("matches: " + value1);
                    count++;
                    break; 
                }
            }
        }
        return count;
    }

    private static int getScratchcardPoint(String line) {
        String[] gameVsNumber = line.split(": ");
        // printArray(gameVsNumber);
        String[] numbersSet = gameVsNumber[1].split("\\| ");
        // printArray(numbersSet);
        String winningNumbers = numbersSet[0];
        String yourNumbers = numbersSet[1];

        String[] winningNumberArray = winningNumbers.split("\\s+");
        String[] yourNumberArray = yourNumbers.split("\\s+");

        int count = countOfCommonValues(winningNumberArray, yourNumberArray);
        if(count == 0) {
            System.out.println("0");
            return 0;}
            System.out.println("count: " + count);
        int result = (int) Math.pow(2, count - 1);
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        String fileName = "Day04/input02.txt";

        try (Scanner scanner = new Scanner(new File(fileName))) {
            int res = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int value = getScratchcardPoint(line);
                res += value;
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }
}