package Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trebuchet2 {

    public static String[] targetDigitValue = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

    public static int convert(String word) {
        System.out.println("here");
        switch (word.toLowerCase()) {
            case "zero":
                return 0;
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            default:
                return -1;
        }
    }

    public static int firstOccurence(String line, int size) {
        for(int i = 0; i < size; i++) {
            if(i > 2) {
                String str = line.substring(0, i);
                System.out.println("....:  " + str);
                for(String target : targetDigitValue) {
                    if(str.contains(target)) {
                        System.out.println("yes");
                        int digitValue = convert(target);
                        System.out.println("digit: " + digitValue);
                        return digitValue;
                    }
                }
                
            }
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                System.out.println(line.charAt(i));
                return line.charAt(i) - '0';
            }
            
        }
        return 0;
    }

    public static int lastOccurence(String line, int size) {
        for(int i = size-1; i >= 0; i--) {
            if(i < size - 1) {
                String str = line.substring(i, size);
                System.out.println(":  " + str);
                for(String target : targetDigitValue) {
                    if(str.contains(target)) {
                        int digitValue = convert(target);
                        return digitValue;
                    }
                }
                
            }
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                System.out.println(line.charAt(i));
                return line.charAt(i) - '0';
            }
            
        }
        return 0;
    }

    public static int calibrationValues(String line) {
        int size = line.length();
        int sum = 0;
        int first = firstOccurence(line, size);
        int last = lastOccurence(line, size);
        sum = (first) * 10 + last; 
        System.out.println("sum: " + sum);
        return sum;
    }
    
    public static void main(String[] args) {
        String fileName = "Day01/input02.txt";

        try (Scanner scanner = new Scanner(new File(fileName))) {
            int res = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int value = calibrationValues(line);
                res += value;
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }
}
