package Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trebuchet {

    public static int calibrationValues(String line) {
        int size = line.length();
        int sum = 0;
        for(int i = 0; i < size; i++) {
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                System.out.println(line.charAt(i));
                sum += line.charAt(i) - '0';
                break;
            }
        }
        for(int i = size-1; i >= 0; i--) {
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                System.out.println(line.charAt(i));
                sum = sum*10 + line.charAt(i) - '0';
                break;
            }
        }
        System.out.println("sum: " + sum);
        return sum;
    }
    
    public static void main(String[] args) {
        String fileName = "input01.txt";

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
